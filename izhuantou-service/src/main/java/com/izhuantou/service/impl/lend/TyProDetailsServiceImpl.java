package com.izhuantou.service.impl.lend;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.lend.TyPro;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomerTyj;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.dao.PropPrivilegeMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerTyjMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.lend.TyProDetailsService;

/**
 * 用户体验金是否能使用判断
 * 
 * @author Administrator
 *
 */

@Service("tyProDetailsServiceImpl")
public class TyProDetailsServiceImpl implements TyProDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(TyProDetailsServiceImpl.class);

	@Autowired
	private PayCustomerTyjMapper customertyjMapper;

	@Autowired
	private MemberMemberMapper userDao;

	@Autowired
	private PayDebitCreditMapper debitCreditMapper;

	@Autowired
	private PayCashPoolMapper payCashPoolDao;

	@Autowired
	private PayTransferReturnMapper transferReturnMapper;
	@Autowired
	private PropPrivilegeMapper propPrivilegeMapper;

	@Override
	public TyPro findByCondition(String memberOID) {
		TyPro ty = new TyPro();
		List<PayDebitCredit> debitCreditList = new ArrayList<PayDebitCredit>();
		List<PayCashPool> payCashPool = new ArrayList<PayCashPool>();
		List<PayTransferReturn> transferReturnList = new ArrayList<PayTransferReturn>();
		List<CustomerTyjDTO> customerTyjList = new ArrayList<CustomerTyjDTO>();
		MemberMember member = new MemberMember();
		try {
			int count = customertyjMapper.findTyjRowCount();
			if (memberOID != null) {
				member = userDao.findUserByOID(memberOID);
				Date zcsjTime = member.getAddDateTime();

				Map<String, String> propMap = propPrivilegeMapper.findPrivilege();
				String sdate = propMap.get("TYJSXTime");
				// 对用户注册时间进行比较
				// 10.16之后用户可以体验体验标
				boolean flag = zcsjTime.after(DateUtils.getJustDate(sdate));
				if (flag) {
					// 查询是否过其他产品 0：未出借 1：出借
					debitCreditList = debitCreditMapper.findByOutMember(memberOID);
					payCashPool = payCashPoolDao.gainByMemberOID(memberOID);
					transferReturnList = transferReturnMapper.findByCondition(memberOID);
					// 查询三个表有没有出借的记录
					int n = 0;
					int n1 = debitCreditList.size();
					int n2 = payCashPool.size();
					int n3 = transferReturnList.size();
					if (n1 == 0 && n2 == 0 && n3 == 0) {
						// 如果查询出来的记录都是0，说明从未出借过
						n = 0;
					} else {
						n = 1;
					}
					ty.setYfs(n);
					// 是否体验过
					String runTrue = "0";
					// 查询是否体验体验标
					customerTyjList = customertyjMapper.findByMemberOID(memberOID);
					if (customerTyjList.size() > 0) {
						runTrue = "1";
					}
					ty.setRunTrue(runTrue);
				} else {
					ty.setRunTrue("0");
				}
			} else {
				ty.setRunTrue("0");
			}
			ty.setCount(count);

			return ty;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("findResult()", e.getMessage());
			return null;
		}

	}

}
