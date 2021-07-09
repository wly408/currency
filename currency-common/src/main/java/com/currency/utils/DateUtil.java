package com.currency.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * 获取当年年月日字符串
	 *
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	public static Date addDays(Date former, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 把日期对象格式化成字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatDate2Date(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return formatStr(result,format);
	}

	/**
	 * 把日期对象格式化成字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception
     */
    public static String formatDate2String(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	public static String formatDate2TimeStamp(Date date) {
		return formatDate2String(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurrentTimeStamp() {
		return formatDate2TimeStamp(new Date());
	}

	/**
	 * 把日期对象格式化成字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatStr(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取相差天数
	 *
	 * @param begin
	 * @return
	 */
	public static int getDifferDays(Date begin, Date end) {
		return (int) ((end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000));
	}


	/**
	 * 当前时间是否在区间内
	 *
	 * @param end
	 * @return
	 */
	public static Boolean isBeforeDate(Date end) {
		Date now=new Date();
		Boolean result=false;
		if(now.before(end)){
			result=true;
		}
		return result;
	}


	public static Date getCurrentDate() {
		Date now = new Date();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(now);
		// 将时分秒,毫秒域清零
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		return cal1.getTime();
	}




}
