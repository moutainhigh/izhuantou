package com.izhuantou.admin.ims.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import com.izhuantou.admin.ims.LogThread;
import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.utils.CookieUtil;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.system.Log;
import com.izhuantou.service.api.system.LogService;

/**
 * 系统日志切点类
 * 
 * @author fucheng
 * @date 2018-06-13
 *
 */
@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");
    private static final ThreadLocal<Log> logThreadLocal = new NamedThreadLocal<Log>("ThreadLocal log");
    private static final ThreadLocal<String> currentUser = new NamedThreadLocal<>("ThreadLocal userName");

    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private LogService logService;

    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.izhuantou.admin.ims.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 方法规则拦截
     */
    @Pointcut("execution(* com.izhuantou.admin.controller..*.*(..))")
    public void controllerPointerCut() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     * 
     * @param joinPoint
     *            切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
	Date beginTime = new Date();
	beginTimeThreadLocal.set(beginTime);
	// debug模式下 显式打印开始时间用于调试
	if (logger.isDebugEnabled()) {
	    logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(beginTime),
		    request.getRequestURI());
	}
	Cookie cookie = CookieUtil.getCookieByName(request, "ims_user");
	if (cookie != null) {
	    String userName = cookie.getValue();
	    currentUser.set(userName);
	}

    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     * 
     * @param joinPoint
     *            切点
     * @author fucheng
     */
    @SuppressWarnings("unchecked")
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
	String userName = currentUser.get();
	// 登入login操作 前置通知时用户未校验 所以session中不存在用户信息
	if (StringUtil.isEmpty(userName)) {
	    Cookie cookie = CookieUtil.getCookieByName(request, "ims_user");
	    if (cookie == null) {
		return;
	    } else {
		userName = cookie.getValue();
	    }
	}
	Object[] args = joinPoint.getArgs();
	String title = "";
	String type = "info"; // 日志类型(info:入库,error:错误)
	String remoteAddr = request.getRemoteAddr();// 请求的IP
	String requestUri = request.getRequestURI();// 请求的Uri
	String method = request.getMethod(); // 请求的方法类型(post/get)
	Map<String, String[]> params = request.getParameterMap(); // 请求提交的参数
	try {
	    title = getControllerMethodDescription2(joinPoint);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// debu模式下打印JVM信息。
	long beginTime = beginTimeThreadLocal.get().getTime();// 得到线程绑定的局部变量（开始时间）
	long endTime = System.currentTimeMillis(); // 2、结束时间
	if (logger.isDebugEnabled()) {
	    logger.debug("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
		    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime), request.getRequestURI(),
		    DateUtils.formatDateTime(endTime - beginTime), Runtime.getRuntime().maxMemory() / 1024 / 1024,
		    Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
		    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
			    + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
	}

	Log log = new Log();
	log.setLogId(StringUtil.getUUID());
	log.setTitle(title);
	log.setType(type);
	log.setRemoteAddr(remoteAddr);
	log.setRequestUrl(requestUri);
	log.setMethod(method);
	log.setMapToParams(params);
	log.setUsername(userName);
	Date operateDate = beginTimeThreadLocal.get();
	log.setOperateDate(DateUtils.formatDate(operateDate, null));
	log.setTimeOut(DateUtils.formatDateTime(endTime - beginTime));
	logger.info("{}保存用户操作信息", log);
	new LogThread(log, this.logService).run();

    }

    /**
     * 异常通知
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
	Log log = logThreadLocal.get();
	if (log != null) {
	    log.setType("error");
	    log.setException(e.toString());
	    logger.info("{}保存用户操作异常信息信息", log);
	    new LogThread(log, this.logService).run();

	}
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint
     *            切点
     * @return 方法描述
     */
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	Method method = signature.getMethod();
	SystemControllerLog controllerLog = method.getAnnotation(SystemControllerLog.class);
	String discription = controllerLog.description();
	return discription;
    }
}
