/* Generated by Together */
package com.izhuantou.common.tool;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Date工具
 * 
 * @author zhangchi
 * @version 1.0
 */
public abstract class ToolDateTime {
    /**
     * 把java.util.Date类型的数据转换成java.sql.Date类型的数据。
     * 
     * @param utilDate
     *            java.util.Date类型的日期。
     * @return 返回转换后的java.sql.Date类型的日期。
     */
    public static Date gainDate(java.util.Date dOne) {
	try {
	    return new Date(dOne.getTime());
	} catch (Throwable cause) {
	    return null;
	}

    }

    /**
     * 把java.util.Date类型的数据转换成java.sql.Time类型的数据。
     * 
     * @param utilDate
     *            java.util.Date类型的日期。
     * @return 返回转换后的java.sql.Time类型的日期。
     */
    public static Time gainTime(java.util.Date dOne) {
	try {
	    return new Time(dOne.getTime());
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 把java.util.Date类型的数据转换成java.sql.Timestamp类型的数据。
     * 
     * @param utilDate
     *            java.util.Date类型的日期。
     * @return 返回转换后的java.sql.Timestamp类型的日期。
     */
    public static Timestamp gainDateTime(java.util.Date dOne) {
	try {
	    return new Timestamp(dOne.getTime());
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取当前日期
     * 
     * @return 返回java.sql.Date类型的日期。
     */
    public static Date gainDate() {
	try {
	    return ToolDateTime.gainDate(new java.util.Date());
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取当前时间
     * 
     * @return 返回java.sql.Time类型的时间。
     */
    public static Time gainTime() {
	try {
	    return ToolDateTime.gainTime(new java.util.Date());
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取当前日期
     * 
     * @return 返回java.sql.Timestamp类型的时间。
     */
    public static Timestamp gainDateTime() {
	try {
	    return ToolDateTime.gainDateTime(new java.util.Date());
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取当前日期中的指定部分或全部
     * 
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回格式化java.sql.Date的字符串。
     */
    public static String formatDate(String strFormat) {
	try {
	    return ToolDateTime.formatDateTime(ToolDateTime.gainDate(), strFormat);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取当前时间中的指定部分或全部
     * 
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回格式化java.sql.Time的字符串。
     */
    public static String formatTime(String strFormat) {
	try {
	    return ToolDateTime.formatDateTime(ToolDateTime.gainTime(), strFormat);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取当前日期中的指定部分或全部
     * 
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回格式化java.sql.Timestamp的字符串。
     */
    public static String formatDateTime(String strFormat) {
	try {
	    return ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(), strFormat);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取日期中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。 如：
     * "yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回制定的日期字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    private static String formatDateTime(java.util.Date dOne, String strFormat) {
	try {
	    SimpleDateFormat sdfDatetimeFormat = (SimpleDateFormat) ToolDateTime.datetimeFormat.get(strFormat);
	    if (sdfDatetimeFormat == null) {
		sdfDatetimeFormat = new SimpleDateFormat(strFormat);
		datetimeFormat.put(strFormat, sdfDatetimeFormat);
	    }
	    return sdfDatetimeFormat.format(dOne);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取日期中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。 如：
     * "yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回制定的日期字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static String formatDate(Date dOne, String strFormat) {
	try {
	    return ToolDateTime.formatDateTime(dOne, strFormat);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取时间中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。 如：
     * "yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回制定的日期字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static String formatTime(Time dOne, String strFormat) {
	try {
	    return ToolDateTime.formatDateTime(dOne, strFormat);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取日期中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。<br>
     * 如："yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回制定的日期字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static String formatDateTime(Timestamp dOne, String strFormat) {
	try {
	    return ToolDateTime.formatDateTime((java.util.Date) dOne, strFormat);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取日期中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。 如：
     * "yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回指定的日期字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    @SuppressWarnings("unchecked")
    private static java.util.Date parseString(String strOne, String strFormat) {
	try {
	    SimpleDateFormat sdfDatetimeFormat = (SimpleDateFormat) ToolDateTime.datetimeFormat.get(strFormat);
	    if (sdfDatetimeFormat == null) {
		sdfDatetimeFormat = new SimpleDateFormat(strFormat);
		datetimeFormat.put(strFormat, sdfDatetimeFormat);
	    }
	    return sdfDatetimeFormat.parse(strOne);
	} catch (Throwable cause) {
	    return null;
	}

    }

    /**
     * 获取日期中的指定部分或全部。< 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。 如：
     * "yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回指定的日期字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static Date parseDate(String strOne, String strFormat) {
	try {

	    return ToolDateTime.gainDate(ToolDateTime.parseString(strOne, strFormat));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取时间中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。<br>
     * 如："yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回指定的时间字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static Time parseTime(String strOne, String strFormat) {
	try {

	    return ToolDateTime.gainTime(ToolDateTime.parseString(strOne, strFormat));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 获取时间中的指定部分或全部。 日期中的部分是如下格式的任一种：
     * *yyyy*、*MM*、*dd*、*yyyy*MM*、*MM*dd*、*yyyy*dd*
     * *yyyy*MM*dd*——这种格式中yyyy、MM、dd之间可以任意调换位置。
     * 注意上面各个格式的日期字符串中的*号表示你可以任意输入内容或不写，比如你可以替换为 年、月、日、时、分、秒等等。 如：
     * "yyyy-MM-dd HH:mm:ss SSS"
     * 
     * @param dOne
     *            日期。
     * @param strFormat
     *            日期的格式化指定。
     * @return 返回指定的时间字符串。
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static Timestamp parseDateTime(String strOne, String strFormat) {
	try {

	    return ToolDateTime.gainDateTime(ToolDateTime.parseString(strOne, strFormat));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iMonth
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static java.util.Date addMonth(java.util.Date dOne, int iMonth) {
	try {
	    Calendar calOne = Calendar.getInstance();
	    calOne.setTime(dOne);
	    calOne.add(Calendar.MONTH, iMonth);
	    return calOne.getTime();
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iMonth
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static Date addMonth(Date dOne, int iMonth) {
	try {

	    return ToolDateTime.gainDate(ToolDateTime.addMonth((java.util.Date) dOne, iMonth));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的时间。
     * 
     * @param dOne
     *            要处理的日期
     * @param iMonth
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的时间。
     */
    public static Timestamp addMonth(Timestamp dOne, int iMonth) {
	try {

	    return ToolDateTime.gainDateTime(ToolDateTime.addMonth((java.util.Date) dOne, iMonth));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static java.util.Date addDay(java.util.Date dOne, int iDay) {
	try {
	    Calendar calOne = Calendar.getInstance();
	    calOne.setTime(dOne);
	    calOne.add(Calendar.DATE, iDay);
	    return calOne.getTime();
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static Date addDay(Date dOne, int iDay) {
	try {

	    return ToolDateTime.gainDate(ToolDateTime.addDay((java.util.Date) dOne, iDay));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的时间。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的时间。
     */
    public static Timestamp addDay(Timestamp dOne, int iDay) {
	try {

	    return ToolDateTime.gainDateTime(ToolDateTime.addDay((java.util.Date) dOne, iDay));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回两个日期之间的间隔分钟
     * 
     * @param dOne
     *            日期1
     * @param dTwo
     *            日期2
     * @return 返回间隔分钟
     */
    private static Long gainDaySpace(java.util.Date dOne, java.util.Date dTwo) {
	try {
	    long lOne = dOne.getTime();
	    long lTwo = dTwo.getTime();

	    return (lTwo - lOne) / 1000 / 60 / 60 / 24;
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回两个日期之间的间隔分钟
     * 
     * @param dOne
     *            日期1
     * @param dTwo
     *            日期2
     * @return 返回间隔分钟
     */
    public static Long gainDaySpace(Date dOne, Date dTwo) {
	try {

	    return ToolDateTime.gainDaySpace((java.util.Date) dOne, (java.util.Date) dTwo);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回两个日期之间的间隔分钟
     * 
     * @param dOne
     *            时间1
     * @param dTwo
     *            时间2
     * @return 返回间隔分钟
     */
    public static long gainDaySpace(Timestamp dOne, Timestamp dTwo) {
	try {

	    return ToolDateTime.gainDaySpace((java.util.Date) dOne, (java.util.Date) dTwo);
	} catch (Throwable cause) {
	    return 0;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static java.util.Date addMinute(java.util.Date dOne, int iMinute) {
	try {
	    Calendar calOne = Calendar.getInstance();
	    calOne.setTime(dOne);
	    calOne.add(Calendar.MINUTE, iMinute);
	    return calOne.getTime();
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static Date addMinute(Date dOne, int iMinute) {
	try {
	    return ToolDateTime.gainDate(ToolDateTime.addMinute((java.util.Date) dOne, iMinute));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回给定的日期加指定天数后所得到的时间。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的时间。
     */
    public static Timestamp addMinute(Timestamp dOne, int iMinute) {
	try {
	    return ToolDateTime.gainDateTime(ToolDateTime.addMinute((java.util.Date) dOne, iMinute));
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回两个日期之间的间隔分钟
     * 
     * @param dOne
     *            日期1
     * @param dTwo
     *            日期2
     * @return 返回间隔分钟
     */
    private static Long gainMinusSpace(java.util.Date dOne, java.util.Date dTwo) {
	try {
	    long lOne = dOne.getTime();
	    long lTwo = dTwo.getTime();

	    return (lTwo - lOne) / 1000 / 60;
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回两个日期之间的间隔分钟
     * 
     * @param dOne
     *            日期1
     * @param dTwo
     *            日期2
     * @return 返回间隔分钟
     */
    public static Long gainMinusSpace(Date dOne, Date dTwo) {
	try {

	    return ToolDateTime.gainMinusSpace((java.util.Date) dOne, (java.util.Date) dTwo);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * 返回两个日期之间的间隔分钟
     * 
     * @param dOne
     *            时间1
     * @param dTwo
     *            时间2
     * @return 返回间隔分钟
     */
    public static Long gainMinusSpace(Timestamp dOne, Timestamp dTwo) {
	try {

	    return ToolDateTime.gainMinusSpace((java.util.Date) dOne, (java.util.Date) dTwo);
	} catch (Throwable cause) {
	    return null;
	}
    }

    /**
     * @param 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static Map formatMillisecond(long millisecond) {
	try {
	    long days = millisecond / (1000 * 60 * 60 * 24);
	    long hours = (millisecond % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	    long minutes = (millisecond % (1000 * 60 * 60)) / (1000 * 60);
	    long seconds = (millisecond % (1000 * 60)) / 1000;
	    HashMap hm = new HashMap();
	    hm.put("day", days);
	    hm.put("hour", hours);
	    hm.put("minute", minutes);
	    hm.put("second", seconds);

	    return hm;
	} catch (Throwable cause) {
	    return null;
	}
    }

    // 获取当年的第一天
    public static Timestamp getYearFirst(int year) {
	Calendar calendar = Calendar.getInstance();
	calendar.clear();
	calendar.set(Calendar.YEAR, year);
	java.util.Date currYearFirst = calendar.getTime();
	return new Timestamp(currYearFirst.getTime());
    }

    // 获取当年的最后一天
    public static Timestamp getYearLast(int year) {
	Calendar calendar = Calendar.getInstance();
	calendar.clear();
	calendar.set(Calendar.YEAR, year);
	calendar.roll(Calendar.DAY_OF_YEAR, -1);
	java.util.Date currYearLast = calendar.getTime();
	return new Timestamp(currYearLast.getTime());
    }

    /**
     * 时间格式
     */
    private static Map datetimeFormat = new HashMap();
}
