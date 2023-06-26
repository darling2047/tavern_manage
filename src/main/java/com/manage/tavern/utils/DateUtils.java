package com.manage.tavern.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtils {

	public static final int YEAR = 1;
	public static final int MONTH = 2;
	public static final int DAY = 3;
	public static final int HOUR = 4;
	public static final int MINUTE = 5;
	public static final int SECOND = 6;

	/**
	 * 得到今天的日期
	 * 
	 * @return
	 */
	public static String getToday() {
		return getToday("yyyy-MM-dd");
	}

	public static Date getThisDayBeggin() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(getToday() + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getThisDayEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(getToday() + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到今天的指定格式的日期
	 * 
	 * @return
	 */
	public static String getToday(String sFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		return sdf.format(new Date());
	}

	/**
	 * 得到两个指定格式日期间的天数
	 * 
	 * @param begindate
	 * @param enddate
	 * @param formatString
	 * @return
	 */
	public static int getIntervalDays(String begindate, String enddate, String formatString) {
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTimeInMillis(stringToDateLong(begindate, formatString));
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTimeInMillis(stringToDateLong(enddate, formatString));
		int interval = (int) ((endCalendar.getTimeInMillis() - beginCalendar.getTimeInMillis())
				/ Integer.valueOf(24 * 60 * 60 * 1000).longValue());
		return interval;
	}

	private static long stringToDateLong(String adate, String formatString) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		try {
			return formatter.parse(adate).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}

	/**
	 * 指定格式的日期 增/减 日期天数
	 * 
	 * @param adate
	 * @param formatString
	 * @param dayCount
	 * @return
	 */
	public static String addDate(String adate, String formatString, int dayCount) {
		Calendar calendarInstan = Calendar.getInstance();
		calendarInstan.setTime(String2Date(adate, formatString));
		calendarInstan.add(5, dayCount);

		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(calendarInstan.getTime());
	}

	/**
	 * String类型日期 转化成 Date类型
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static Date String2Date(String date, String formatString) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			return new Date();
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 转化日期的格式显示
	 * 
	 * @param date
	 * @param formatString
	 * @param toformatString
	 * @return
	 */
	public static String changFormat(String date, String formatString, String toformatString) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString.replaceAll("Y", "y").replaceAll("D", "d"));

		SimpleDateFormat sdf = new SimpleDateFormat(toformatString.replaceAll("Y", "y").replaceAll("D", "d"));

		try {
			return sdf.format(formatter.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return date;
		}
	}

	/**
	 * 得到指定格式日期间的 月份数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param formatString
	 * @return
	 */
	public static int getIntervalMonths(String beginDate, String endDate, String formatString) {
		// TODO Auto-generated method stub
		Calendar beg = Calendar.getInstance();
		beg.setTime(String2Date(beginDate, formatString));

		Calendar end = Calendar.getInstance();
		end.setTime(String2Date(endDate, formatString));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String b = sdf.format(beg.getTime());
		String e = sdf.format(end.getTime());

		int iYb = Integer.parseInt(b.substring(0, 4));
		int iYe = Integer.parseInt(e.substring(0, 4));
		int iMb = Integer.parseInt(b.substring(4, 6));
		int iMe = Integer.parseInt(e.substring(4, 6));

		return (iYe - iYb) * 12 + (iMe - iMb);
	}

	/**
	 * 指定格式的日期 增/减 月份数
	 * 
	 * @param beginDate
	 * @param formatString
	 * @param i
	 * @return
	 */
	public static String addMonth(String beginDate, String formatString, int i) {
		// TODO Auto-generated method stub
		Calendar calendarInstan = Calendar.getInstance();
		calendarInstan.setTime(String2Date(beginDate, formatString));
		calendarInstan.add(Calendar.MONTH, i);

		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(calendarInstan.getTime());
	}

	public static String addYear(String beginDate, String formatString, int i) {
		// TODO Auto-generated method stub
		Calendar calendarInstan = Calendar.getInstance();
		calendarInstan.setTime(String2Date(beginDate, formatString));
		calendarInstan.add(Calendar.YEAR, i);

		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(calendarInstan.getTime());
	}

	/**
	 * 得到当前日期 2008-03-25
	 * 
	 * @return
	 */
	public static String getCurrDate() {

		return getCurrDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前时间 20:12:10
	 * 
	 * @return
	 */
	public static String getCurrTime() {
		return getCurrDate("HH:mm:ss");
	}

	/**
	 * 得到当前日期及时间 2008-03-25 20:12:10
	 * 
	 * @return
	 */
	public static String getCurrDateTime() {
		return getCurrDate("yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurrDateNoSign() {
		return getCurrDate("yyyyMMdd");
	}

	public static String getCurrDateChinese() {
		String currDate = getCurrDateNoSign();
		if (currDate != null && !currDate.equals("")) {
			currDate = currDate.substring(0, 4) + "年" + currDate.substring(4, 6) + "月" + currDate.substring(6, 8) + "日";
		}
		return currDate;
	}

	public static String getCurrTimeNoSign() {
		return getCurrDate("HHmmss");
	}

	public static String getCurrDateTimeNoSign() {
		return getCurrDate("yyyyMMddHHmmss");
	}

	/**
	 * 得到格式化后的日期字符串,默认为格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getFormatDate(String strDate) {
		if (strDate == null || strDate.equals(""))
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			return "";
		}

		if (date == null)
			return "";
		else
			return sdf.format(date);
	}

	/**
	 * 得到当前日期字符串,默认为格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrDate(String dateFormat) {
		if (dateFormat == null)
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar now = Calendar.getInstance();
		return sdf.format(now.getTime());
	}

	/**
	 * 获取昨天的dateFormat格式日期
	 */
	public static String getYesterDay(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat(dateFormat).format(cal.getTime());

	}

	/**
	 * 获取n天前的日期
	 */
	public static String getThityDay(int day) {
		String dateFormat = "yyyyMMdd";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		return new SimpleDateFormat(dateFormat).format(cal.getTime());

	}

	/**
	 * 获取上个月的dateFormat格式日期
	 */
	public static String getLastMonth(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat(dateFormat).format(cal.getTime());

	}

	/**
	 * 获取指定月的第一天
	 * @param amount   负数表示前几个月,整数表示后几个月
	 * @return
	 */
	public static String getMonthFirstDay(int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, amount);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()).substring(0, 7) + "-01";
	}

	/**
	 * 获取指定月的第一天
	 * @param amount   负数表示前几个月,整数表示后几个月
	 * @return
	 */
	public static String getMonthFirstDay(int amount,String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, amount);
		return new SimpleDateFormat(format).format(cal.getTime()).substring(0, 7) + "-01";
	}

	/**
	 * 得到指定日期N天后的日期字符串 DateUtil.getAddDate(DateUtil.DAY,"2008-04-10",1)
	 * 
	 * @param addType
	 * @param dateStr
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(int addType, String dateStr, int n) throws ParseException {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
		now.add(dateType, n);
		return (new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
	}

	/**
	 * 得到指定日期N天后的日期字符串 DateUtil.getAddDate(DateUtil.DAY,"2008-04-10",1)
	 * 
	 * @param addType
	 * @param dateStr
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(int addType, String dateStr, String dateFormat, int n) throws ParseException {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.setTime(new SimpleDateFormat(dateFormat).parse(dateStr));
		now.add(dateType, n);
		return (new SimpleDateFormat(dateFormat).format(now.getTime()));
	}

	/**
	 * 得到当前日期N天后的日期字符串
	 * 
	 * @param addType
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String getAddDateOnCurr(int addType, int n) throws Exception {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.add(dateType, n);
		return (new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
	}

	/**
	 * 得到当前月N月后的日期字符串（年也可）----仅限月或年
	 * 
	 * @param addType
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String getAddMonthOnCurr(int addType, int n) throws Exception {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.add(dateType, n);
		return (new SimpleDateFormat("yyyyMM").format(now.getTime()));
	}

	/**
	 * 得到指定日期时间N单位后的日期时间字符串
	 * 
	 * @param dateType
	 * @param dateTimeStr
	 * @param amount
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDateTime(int addType, String dateTimeStr, int n) throws ParseException {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (addType == HOUR)
			dateType = Calendar.HOUR;
		if (addType == MINUTE)
			dateType = Calendar.MINUTE;
		if (addType == SECOND)
			dateType = Calendar.SECOND;
		if (dateType == 0)
			return "";
		String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
		Calendar now = Calendar.getInstance();
		now.setTime(new SimpleDateFormat(dateFormatStr).parse(dateTimeStr));
		now.add(dateType, n);
		return (new SimpleDateFormat(dateFormatStr).format(now.getTime()));
	}

	/**
	 * 判断日期时间dateTime1是否在期时间dateTime2之前,日期时间格式 2008-4-10 16:16:34
	 * 
	 * @param dateTime1
	 * @param dateTime2
	 * @return
	 * @throws Exception
	 */
	public static boolean isDateTimeBefore(String dateTime1, String dateTime2) throws Exception {
		DateFormat df = DateFormat.getDateTimeInstance();
		return df.parse(dateTime1).before(df.parse(dateTime2));
	}

	/**
	 * 判断日期date1是否在时间date2之前,日期格式 2008-4-10
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static boolean isDateBefore(String date1, String date2) throws Exception {
		DateFormat df = DateFormat.getDateInstance();
		return df.parse(date1).before(df.parse(date2));
	}

	/**
	 * 判断pTime日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 按指定格式转换输出指定的日期
	 * 
	 * @param date
	 *            要输出的日期
	 * @param datePattern
	 *            要输出的时间格式
	 * @return 格式化后的字符串
	 */
	public static String getTime(String date, String datePattern) throws Exception {
		String retValue = null;
		SimpleDateFormat sf = new SimpleDateFormat(datePattern);
		Date fromDate = sf.parse(date);
		retValue = sf.format(fromDate);
		return retValue;
	}

	/**
	 * 按指定格式转换输出指定的日期
	 * 
	 * @param date
	 *            要输出的日期
	 * @param datePattern
	 *            要输出的时间格式
	 * @return 格式化后的字符串
	 */
	public static String getTime(Date date, String datePattern) {
		String retValue = null;
		SimpleDateFormat sf = new SimpleDateFormat(datePattern);
		retValue = sf.format(date);
		return retValue;
	}

	/**
	 * 返回当前时间的10位long型
	 * 
	 * @return
	 */
	public static String time() {
		return StringUtils.substring(String.valueOf(new Date().getTime()), 0, 10);
	}

	public static String time(int day) {
		Calendar calendarInstan = Calendar.getInstance();
		calendarInstan.setTime(new Date());
		calendarInstan.add(5, day);

		return StringUtils.substring(String.valueOf(calendarInstan.getTime().getTime()), 0, 10);
	}

	public static Long strToTime(Date date, String datePattern) {
		long time = stringToDateLong(getTime(date, datePattern), datePattern);
		return time / 1000;
	}

	public static String getFirstDayOfMonth(String date, String format) {
		int year = Integer.parseInt(date.substring(0, 4));
		int beginIndex = 4, endIndex = 6;
		if (date.contains("-") || date.contains("/")) {
			beginIndex = beginIndex + 1;
			endIndex = endIndex + 1;
		}
		int month = Integer.parseInt(date.substring(beginIndex, endIndex));
		return getFirstDayOfMonth(year, month, format);
	}

	public static String getFirstDayOfMonth(int year, int month, String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static String getLastDayOfMonth(String date, String format) {
		int year = Integer.parseInt(date.substring(0, 4));
		int beginIndex = 4, endIndex = 6;
		if (date.contains("-") || date.contains("/")) {
			beginIndex = beginIndex + 1;
			endIndex = endIndex + 1;
		}
		int month = Integer.parseInt(date.substring(beginIndex, endIndex));
		return getLastDayOfMonth(year, month, format);
	}

	public static String getLastDayOfMonth(int year, int month, String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	/**
	 * 获取今天剩余秒
	 * 
	 * @return
	 */
	public static int getLeftSecondsOfToday() {
		Calendar curDate = Calendar.getInstance();
		Calendar tommorowDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH),
				curDate.get(Calendar.DATE) + 1, 0, 0, 0);
		return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
	}

	/*
	 * 判断某个时间是否在某个日期之前的几天之内
	 */
	public static long compaerBetweent(String time1, int continuday) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String day1 = DateUtils.getThityDay(continuday);
		String day2 = DateUtils.getThityDay(0);
		try {
			Long day_1 = df.parse(day1).getTime();// 登录的第一天
			Long day_2 = df.parse(day2).getTime();// 今天
			Long day_3 = df.parse(time1).getTime();// 许愿的创建时间
			if (day_3 >= day_1 && day_3 <= day_2) {
				long day = (day_2 - day_3) / (1000 * 60 * 60 * 24);
				// 计算连续登录多少天
				return day + 1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	/* 判断两个时间是否相等 */
	public static boolean compareDate(Date date, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date11 = sdf.format(date);
		if (date11.equals(date2)) {
			return true;
		} else {
			return false;
		}

	}

	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String result = sdf.format(date);
		return result;
	}

	/** 获取开始时间-结束时间的中间的天数 */
	public static Integer getDays(String startDate, String endDate, String format) {
		Date start = String2Date(startDate, format);
		Date end = String2Date(endDate, format);
		return (int) ((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * 
	 * @param nowTime
	 *            当前时间
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算时间差
	 * 
	 * @return day + "天" + hour + "小时" + min + "分钟";
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}

	/**
	 * 根据日期获取星期几
	 */
	public static String getDayOfWeekByDate(String date) {
		String dayOfweek = "-1";
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = myFormatter.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("E");
			String str = formatter.format(myDate);
			if (str.equals("Mon")) {
				str = "星期一";
			}
			if (str.equals("Tue")) {
				str = "星期二";
			}
			if (str.equals("Wed")) {
				str = "星期三";
			}
			if (str.equals("Thu")) {
				str = "星期四";
			}
			if (str.equals("Fri")) {
				str = "星期五";
			}
			if (str.equals("Sat")) {
				str = "星期六";
			}
			if (str.equals("Sun")) {
				str = "星期日";
			}
			dayOfweek = str;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dayOfweek;
	}


	/**
	 * date2比date1多的天数:比较是基于年月日做的比较,不计算时分秒
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 不同年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else // 同一年
		{
			// System.out.println("判断day2 - day1 : " + (day2-day1));
			return day2 - day1;
		}
	}

	public static boolean timeBlock(Integer start, Integer end) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
		String hour = simpleDateFormat.format(new Date());
		System.out.println(hour);
		if (Integer.parseInt(hour) > end) {
			return false;
		}
		if (Integer.parseInt(hour) < start) {
			return false;
		}
		return true;
	}

	public static boolean timeDiffer(Integer time,Date createTime) throws ParseException{
		long times = new Date().getTime() - createTime.getTime();
		long result = times / (1000 * 60 * 60);
		if(result > time){
			return false;
		}
		return true;
	}

	/**
	 * 获取给定时间所在月的第几天
	 * @param days
	 * @return
	 */
	public static Date getDaysMonth(int days,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1=Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.set(Calendar.DAY_OF_MONTH, days);
		System.out.println("该季度"+days+"天: "+sdf.format(calendar1.getTime()));
		return calendar1.getTime();
	}

	/**
	 * 获取当前时间所属季度开始月第一天
	 * @param dBegin
	 * @return
	 */
	public static Date quarterStart(Date dBegin) {
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		int remainder = calBegin.get(Calendar.MONTH)  % 3;
		int month = remainder != 0 ? calBegin.get(Calendar.MONTH) - remainder: calBegin.get(Calendar.MONTH);

		calBegin.set(Calendar.MONTH, month);
		calBegin.set(Calendar.DAY_OF_MONTH, calBegin.getActualMinimum(Calendar.DAY_OF_MONTH));

		calBegin.setTime(calBegin.getTime());
		return calBegin.getTime();
	}

	/**
	 * 判断当前时间是否在该季度的第一个月内
	 * @return true表示在第一个月内
	 */
	public static boolean inFirstMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 当前时间所在季度的第一天
		Date startDate = DateUtils.quarterStart(new Date());
		System.out.println("当前时间所在季度第一天是："+sdf.format(startDate));
		// 当前时间所在季度的第一个月的最后一天
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date daysMonth = c.getTime();
		System.out.println("当前时间所在季度第一个月的最后一天是："+sdf.format(daysMonth));
		// 判断当前时间是否在当前季度的第一个月
		boolean effectiveDate = DateUtils.isEffectiveDate(new Date(), startDate, daysMonth);
		return effectiveDate;
	}

	/**
	 * 获取当前时间所属季度结束月最后一天
	 * @param endDate
	 * @return
	 */
	public static Date quarterEnd(Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		int remainder = (calendar.get(Calendar.MONTH) + 1) % 3;
		int month = remainder != 0 ? calendar.get(Calendar.MONTH) + (3 - remainder) : calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.setTime(calendar.getTime());
		return calendar.getTime();
	}

	/**
	 * 获取指定季度的开始结束时间
	 * @param quarter   指定季度
	 * @param diffYear  与当年的差值  如-1表示当年的前一年对应的季度开始结束日期 1表示当年的后一年对应的季度开始结束日期
	 * @return
	 */
	public static Map<String, String> getQuarterStartEndTime(int quarter,int diffYear) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> result = new HashMap<>();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.add(Calendar.YEAR,diffYear);
		end.add(Calendar.YEAR,diffYear);
		try {
			if (quarter == 1 ) {
				start.set(Calendar.MONTH, 0);
				start.set(Calendar.DATE,1);
				end.set(Calendar.MONTH, 2);
				end.set(Calendar.DATE, 31);
			} else if (quarter == 2) {
				start.set(Calendar.MONTH, 3);
				start.set(Calendar.DATE,1);
				end.set(Calendar.MONTH, 5);
				end.set(Calendar.DATE, 30);
			} else if (quarter == 3) {
				start.set(Calendar.MONTH, 6);
				start.set(Calendar.DATE,1);
				end.set(Calendar.MONTH, 8);
				end.set(Calendar.DATE, 30);
			} else if (quarter == 4) {
				start.set(Calendar.MONTH, 9);
				start.set(Calendar.DATE,1);
				end.set(Calendar.MONTH, 11);
				end.set(Calendar.DATE, 31);
			}
//			end.add(Calendar.DATE,1);
			result.put("startTime",sdf.format(start.getTime()));
			result.put("endTime",sdf.format(end.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取指定时间所在的年+季度字符串
	 * @param date
	 * @return
	 */
	public static String getYearQuarter(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		LocalDate localDate;
//		if (Objects.isNull(date)) {
// 			localDate = LocalDate.now();
//		}else {
//			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		}
//		TemporalQuery<String> quarterOfYearQuery = new QuarterOfYearQuery();
//		String currentQuarter = localDate.query(quarterOfYearQuery);
		String currentQuarter = getCurrentQuarter(date);
		int currentYear = Integer.parseInt(sdf.format(date));
		String assessStr = currentYear+""+currentQuarter;
		return assessStr;
	}

	public static final List<String> FIRST_CURRENT_LIST = Stream.of("01","02","03").collect(Collectors.toList());
	public static final List<String> SECOND_CURRENT_LIST = Stream.of("04","05","06").collect(Collectors.toList());
	public static final List<String> THIRD_CURRENT_LIST = Stream.of("07","08","09").collect(Collectors.toList());
	public static final List<String> FOURTH_CURRENT_LIST = Stream.of("10","11","12").collect(Collectors.toList());

	/**
	 * 获取当前时间所在季度
	 * @param date
	 * @return 季度数字格式：01 02 03 04
	 */
	public static String getCurrentQuarter(Date date) {
		String res = ",";
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String month = sdf.format(date);
		if (FIRST_CURRENT_LIST.contains(month)) {
			res = "01";
		}
		if (SECOND_CURRENT_LIST.contains(month)) {
			res = "02";
		}
		if (THIRD_CURRENT_LIST.contains(month)) {
			res = "03";
		}
		if (FOURTH_CURRENT_LIST.contains(month)) {
			res = "04";
		}
		return res;
	}

	public static Date dateConvert(String time) {
		if (StringUtils.isBlank(time)) {
			return null;
		}
		time = time.replace("Z", "");
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date d = null;
		try {
			d = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

    public static class QuarterOfYearQuery implements TemporalQuery<String> {
		@Override
		public String queryFrom(TemporalAccessor temporal) {
			LocalDate now = LocalDate.from(temporal);
			if(now.isBefore(now.with(Month.APRIL).withDayOfMonth(1))) {
				return "01";
			} else if(now.isBefore(now.with(Month.JULY).withDayOfMonth(1))) {
				return "02";
			} else if(now.isBefore(now.with(Month.NOVEMBER).withDayOfMonth(1))) {
				return "03";
			} else {
				return "04";
			}
		}
	}

	/**
	 * 获取给定季度的下一季度
	 * @param quarter  给定季度 格式为yyyyMM 如202101
	 * @return 下一季度 格式为yyyyMM 如202102
	 */
	public static String getNextQuarter(String quarter) {
		int currentYear = Integer.parseInt(quarter.substring(0, 4));
		int currentQuarter = Integer.parseInt(quarter.substring(5));
		if (currentQuarter != 4) {
			return currentYear+"0"+(currentQuarter+1);
		}else {
			int resYear = currentYear + 1;
			return resYear+"01";
		}
	}

	/**
	 * 获取给定时间所在季度第一个月的最后一天
	 */
	public static String getLastDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 当前时间所在季度的第一天
		Date startDate = DateUtils.quarterStart(date);
		System.out.println("当前时间所在季度第一天是："+sdf.format(startDate));
		// 当前时间所在季度的第一个月的最后一天
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date daysMonth = c.getTime();
		System.out.println("当前时间所在季度第一个月的最后一天是："+sdf.format(daysMonth));
		return sdf.format(daysMonth);
	}

	public static Date getBeginTime(int year, int month) {
		YearMonth yearMonth = YearMonth.of(year, month);
		LocalDate localDate = yearMonth.atDay(1);
		LocalDateTime startOfDay = localDate.atStartOfDay();
		ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

		return Date.from(zonedDateTime.toInstant());
	}

	public static Date getEndTime(int year, int month) {
		YearMonth yearMonth = YearMonth.of(year, month);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();
		LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * 获取与给定时间相差diff天的时间
	 * @param date	给定时间
	 * @param diff	时间差
	 * @return
	 */
	public static Date getDiffDay(Date date,int diff) {
		if (Objects.isNull(date)) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,diff);
		return calendar.getTime();
	}

	/**
	 * 获取给定月份每一天的列表
	 * @param yyyymm
	 * @return
	 */
	public static List<String> getDayListOfMonth(String yyyymm) {

		if (yyyymm.length() != 6) {
			return null;
		}

		List<String> list = new ArrayList<String>();
		String ystr = yyyymm.substring(0, 4);
		String mstr = yyyymm.substring(4,6);

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, Integer.parseInt(ystr));//年份
		a.set(Calendar.MONTH,Integer.parseInt(mstr) - 1);//月份
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);

		int maxDate = a.get(Calendar.DATE);

		for (int i = 0; i < maxDate; i++) {
			int d = i+1;
			String dstr = "";
			if (d < 10) {
				dstr = "0"+String.valueOf(d);
			}else {
				dstr = String.valueOf(d);
			}
			String day = ystr + "-" + mstr + "-" + dstr;
			list.add(day);
		}

		return list;
	}


	/**
	 * 获取指定范围内的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max)
	{
		Random random = new Random();
		int res = random.nextInt(max) % (max - min + 1) + min;
		return res;
	}



	public static void main(String[] args) throws ParseException {
		String yyyyMM = getLastMonth("yyyyMM");
		System.out.println("yyyyMM = " + yyyyMM);
	}
}
