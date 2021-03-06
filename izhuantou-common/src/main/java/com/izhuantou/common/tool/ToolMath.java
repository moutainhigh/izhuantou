/* Generated by Together */
package com.izhuantou.common.tool;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ToolMath {
	/**
	 * 获取随机整数
	 * 
	 * @return 随机随机整数
	 * @param iBeginNumber
	 *            一个数值
	 * @param iEndNumber
	 *            一个数值
	 * @exception ExceptionOperateToolFail
	 *                使用工具失败异常
	 */
	public static int gainRandomInt(int iBeginNumber, int iEndNumber) {

		return iBeginNumber + random.nextInt(iEndNumber - iBeginNumber);

	}

	/**
	 * 四舍五入
	 * 
	 * @return 四舍五入结果
	 * @param dNumber
	 *            一个数值
	 * @param iPlace
	 *            小数点位数
	 * @exception ExceptionOperateToolFail
	 *                使用工具失败异常
	 */
	public static double gainRoundNumber(double dNumber, int iPlace) {

		double dTemp = Math.pow(10, iPlace);
		return Math.round(dNumber * dTemp) / dTemp;

	}

	/**
	 * 转换大写金额
	 * 
	 * @return 大写结果
	 * @param value
	 *            一个数值
	 * @exception ExceptionOperateToolFail
	 *                使用工具失败异常
	 */
	public static String toBigMoney(double value) {

		char[] hunit = { '拾', '佰', '仟' };
		// 段内位置表示
		char[] vunit = { '万', '亿' };
		// 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示

		long midVal = (long) (value * 100);
		// 转化成整形
		String valStr = String.valueOf(midVal);
		// 转化成字符串
		String head = valStr.substring(0, valStr.length() - 2);
		// 取整数部分
		String rail = valStr.substring(valStr.length() - 2);
		// 取小数部分
		String prefix = "";
		// 整数部分转化的结果
		String suffix = "";
		// 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) {
			// 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分";
			// 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray();
		// 把整数部分转化成字符数组
		char zero = '0';
		// 标志'0'表示出现过0
		byte zeroSerNum = 0;
		// 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) {
			// 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4;
			// 取段内位置
			int vidx = (chDig.length - i - 1) / 4;
			// 取段位置
			if (chDig[i] == '0') {
				// 如果当前字符是0
				zeroSerNum++;
				// 连续0次数递增
				if (zero == '0') {
					// 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0;
			// 连续0次数清零
			if (zero != '0') {
				// 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0'];
			// 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1];
				// 段结束位置应该加上段名如万,亿
			}
		}
		if (prefix.length() > 0)
			prefix += '圆';
		// 如果整数部分存在,则有圆的字样
		return prefix + suffix;

	}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 */
	public static double gainDistance(double lon1, double lat1, double lon2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 距离转换成经度
	 * 
	 * @param distance
	 * @return
	 */
	public static double gainLonDiff(double distance, double lat) {
		// double lngDegree = 2 * Math.asin(Math.sin((double) distance / 12742)
		// /
		// Math.cos(lat));
		// // 转换弧度
		// lngDegree = lngDegree * (180 / Math.PI);
		// return lngDegree;
		double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(lat));
		return Math.toDegrees(dlng);
	}

	/**
	 * 距离转换成纬度
	 * 
	 * @param distance
	 * @return
	 */
	public static double gainLatDiff(double distance) {
		// double latDegrees = (double) distance / 6371;
		// // 转换弧度
		// latDegrees = latDegrees * (180 / Math.PI);
		// return latDegrees;

		double dlat = distance / EARTH_RADIUS;
		return Math.toDegrees(dlat); // # 弧度转换成角度
	}

	// public double getLongt(double longt1, double lat1, double distance)
	// {
	// double a = (180 * distance) / (Math.PI * EARTH_RADIUS * Math.cos(lat1 *
	// Math.PI / 180));
	// return a;
	// }
	//
	// public double getLat(double longt1, double lat1, double distance)
	// {
	// double a = (180 * distance) / (Math.PI * EARTH_RADIUS * Math.cos(lat1 *
	// Math.PI / 180));
	// return a;
	// }

	/**
	 * 获取日期中的指定部分或全部。<br>
	 * 日期中的部分是如下格式的任一种：<br>
	 * ####.000<br>
	 * ####,000<br>
	 * 
	 * @param one
	 *            日期。
	 * @param strFormat
	 *            日期的格式化指定。
	 * @return 返回制定的日期字符串。
	 * @exception ExceptionOperateToolFail
	 *                使用工具失败异常
	 */
	public static String format(Object one, String strFormat) {

		DecimalFormat dfDecimalFormat = (DecimalFormat) ToolMath.decimalFormat.get(strFormat);
		if (dfDecimalFormat == null) {
			dfDecimalFormat = new DecimalFormat(strFormat);
			ToolMath.decimalFormat.put(strFormat, dfDecimalFormat);
		}
		return dfDecimalFormat.format(one);

	}

	/**
	 * 随机数
	 */
	private static Random random = new Random();
	/**
	 * 数字格式
	 */
	private static Map decimalFormat = new HashMap();

	/**
	 * 处理数字，3位加逗号分隔
	 */
	public static String formatNumber(BigDecimal bigdecimal) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(bigdecimal);
	}

}
