package com.wangz.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理公共类
 * @author jxi
 *
 */
@Slf4j
public class DateTimeUtil {
	public static String fullPattern2 = "yyyyMMddHHmmssSSS";
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String DEFAULT_DATE = "yyyyMMdd";


    /**
     * 两个时间相减 返回小时数
     * @param beginTime
     * @param endTime
     * @return
     */
	public static Long hoursSubTime(Date endTime,Date beginTime) {
        try {
            // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
            Long result = (endTime.getTime() - beginTime.getTime()) / 3600000;
            log.info("当前时间减去测试时间=" + result + "小时");

            return result;
        } catch (Exception e) {
            log.error("时间相减出现异常 :" + e.getMessage(), e);
        }

        return null;
    }


	/**
	 * 获取指定格式日期字附后串
	 * @param format 日期格式
	 * @return 日期字附串
	 */
	public static String getformatdate(String format)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sp = new SimpleDateFormat(format);
		return sp.format(calendar.getTime());
	}
	
	/**
	 * 将字符串转换成格式为：yyyy-MM-dd HH:mm 日期.
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str) {
		if (str == null || str.trim().length() == 0)
			return null;
		try {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return fmt.parse(str.trim());

		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字符串转换成指定格式的日期.
	 * 
	 * @param str
	 *            日期字符串.
	 * @param dateFormat
	 *            日期格式. 如果为空，默认为:yyyy-MM-dd HH:mm:ss.
	 * @return
	 */
	public static Date strToDate(String str, String dateFormat) {
		if (str == null || str.trim().length() == 0)
			return null;
		try {
			if (dateFormat == null || dateFormat.length() == 0)
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			DateFormat fmt = new SimpleDateFormat(dateFormat);
			Date  date=fmt.parse(str.trim());
			
			return  date;
		} catch (Exception ex) {
			/*
			 * log.error("将字符串(" + str + ")转换成指定格式(" + dateFormat +
			 * ")的日期时失败！错误原因：" + ex.getMessage());
			 */
			return null;
		}
	}

	/**
	 * 将当前日期转换成指定格式的字符串. 如：20071012141350
	 * 
	 * @return
	 */
	public static String currentDateToStr(String pattern) {

		if(CheckParam.isNull(pattern)){
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return dateToStr(new Date(), pattern);
	}

	/**
	 * 将日期转换成 日期(yyyy-MM-dd)字符串.
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String dateTodateStr(Date date) {
		return dateToStr(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期转换成 yyyy-MM-dd HH:mm 字符串.
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String dateToStr(Date date) {
		return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将数据库查询出来的日期进行转换
	 * 
	 * @param list
	 */
	public static void getFormatDate(List<Map<String,Object>> list) {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Map<String, Object> item : list) {
			for (String key : item.keySet()) {
				Object value = item.get(key);
				if (value != null
						&& value.getClass() == Timestamp.class)
					item.put(key, fm.format(value));
			}
		}
	}

	/**
	 * 将日期转换成 yyyy-MM-dd HH:mm 日期类型.
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date dateToDate(Date date) {
		return dateToDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 数据库的日期类型转换成JAVA的DATE类型
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static Date dateToDate(Date date, String dateFormat) {
		if (date == null || "".equals(date)) {
			// log.debug("未知时间");
			return null;
			// return "未知时间";
		}
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		String time = sf.format(date);
		return Timestamp.valueOf(time);
	}

	/**
	 * 将日期转换成指定格式的字符串.
	 * 
	 * @param date
	 *            日期
	 * @param dateFormat
	 *            日期格式. 如果为空，默认为:yyyy-MM-dd HH:mm:ss.
	 * @return
	 */
	public static String dateToStr(Date date, String dateFormat) {
		if (date == null || "".equals(date)) {
			// log.debug("未知时间");
			return "";
			// return "未知时间";
		}
		try {
			if (dateFormat == null || dateFormat.trim().length() == 0)
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			if ("yyyy-MM-dd".equals(dateFormat))
				dateFormat = "yyyy-MM-dd";
			DateFormat fmt = new SimpleDateFormat(dateFormat.trim());

			return fmt.format(date);
		} catch (Exception ex) {
			 System.out.println("将日期转换成指定格式(" + dateFormat + ")的字符串时失败！错误原因：" +
			 ex.getMessage());
			 
			return "";
			// return "日期格式不匹配";
		}
	}

	/**
	 * 返回减去指定天数的日期字符串. 获取计算后的日期：指定日期dateStr前day天
	 * 
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            减去的天数. return
	 */
	public static String subtractDateToString(Date date, int day) {
		try {
			Date tempDate = subtractDate(date, day);
			String dateStr = dateToStr(tempDate, null);
			return dateStr;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 返回减去指定天数的日期.
	 * 
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            减去的天数. return
	 */
	public static Date subtractDate(Date date, int day) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currDay = Calendar.DAY_OF_MONTH;
			calendar.set(currDay, calendar.get(currDay) - day); // 让日期减 day天

			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}

	/**
	 * 返回减去指定天数和小时数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param hour
	 *            减去的小时. return
	 */
	public static Date subtractAayAndTime(Date date,int day, int hour) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currHour = Calendar.HOUR_OF_DAY;

            int currDay = Calendar.DAY_OF_MONTH;

			calendar.set(currHour, calendar.get(currHour) - hour); // 让日期减 hour小时

            calendar.set(currDay, calendar.get(currDay) - day); // 让日期减 day天

			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}

	/**
	 * 返回减去指定小时数的日期.
	 * 
	 * @param date
	 *            将运算日期.
	 * @param hour
	 *            减去的小时. return
	 */
	public static Date subtractTime(Date date, int hour) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currHour = Calendar.HOUR_OF_DAY;
			calendar.set(currHour, calendar.get(currHour) - hour); // 让日期减 hour小时天

			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}


	/**
	 * 返回加上指定天数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            加上的天数. return
	 */
	public static Date plusDateAndSubtractHour(Date date, int day , int hour) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currDay = Calendar.DAY_OF_MONTH;
			int currHour = Calendar.HOUR_OF_DAY;

			calendar.set(currDay, calendar.get(currDay) + day); // 让日期加 day天
			calendar.set(currHour,calendar.get(currHour) - hour); //让日期减去 hour小时

			return calendar.getTime();
		} catch (Exception ex) {
			return null;
		}
	}


	/**
	 * 返回加上指定天数的日期.
	 * 
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            加上的天数. return
	 */
	public static Date plusDate(Date date, int day) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currDay = Calendar.DAY_OF_MONTH;

			calendar.set(currDay, calendar.get(currDay) + day); // 让日期加 day天

			return calendar.getTime();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 返回加上指定天数的日期字符串.
	 * 
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            加上的天数. return
	 */
	public static String plusDateToString(Date date, int day) {
		try {
			Date tempDate = subtractDate(date, day);
			String dateStr = dateToStr(tempDate, null);

			return dateStr;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 取得指定日期date月份最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMaxDayOfMonth(Date date) {
		int max = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		max = cal.get(Calendar.DAY_OF_MONTH);

		return max;
	}

	/**
	 * 计算数组的中年月日对应的天数,返回int
	 * 
	 * @param overplus
	 *            格式"X年X个月X天"
	 */
	public static int getDayForOverplus(String overplus,String sign) {
		Calendar c = getCalendarForOverplus(new Date(),overplus,sign);
		
		return getDateNumber(c);
	}

	/**
	 * 计算当前日期至此日期的天数差
	 * 
	 * @param c1
	 * @return
	 */
	public static int getDateNumber(Calendar c1) {
		long bts1, bts2;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());

		bts1 = c1.getTimeInMillis() - cal1.getTimeInMillis();
		bts2 = bts1 / (24 * 60 * 60 * 1000); // 结果也有可能超出int,故用long bts2

		return (int) bts2;
	}

	/**
	 * 计算两个时间（startDate,endDate）差值，并以数组形式返回差值，格式为resultArray[]={年,月,日}
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int[] getDateArray(Date startDate, Date endDate) {
		int resultArray[] = new int[3]; // 存放结果，格式为resultArray[]={年,月,日}
		int day = 0;
		int month = 0;
		int year = 0;
		boolean dayFlag = false; // 日差为负标志，true：为负
		boolean monthFlag = false; // 月差为负标志，true：为负

		try {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(startDate);
//			endDate = plusDate(endDate,1);	//加上结束时间当天
			c2.setTime(endDate);

			int startDay = c1.get(c1.DAY_OF_MONTH);
			int endDay = c2.get(c2.DAY_OF_MONTH);
			int startMonth = c1.get(c1.MONTH);
			int endMonth = c2.get(c2.MONTH);
			int startYear = c1.get(c1.YEAR);
			int endYear = c2.get(c2.YEAR);

			// 计算天数差
			int tempDay = endDay - startDay;
			if (tempDay < 0) {
				dayFlag = true;
				// day = getMaxDayOfMonth(startDate) - startDay + endDay;
				day = getMaxDayOfMonth(startDate) + tempDay;
			} else {
				day = tempDay;
			}

			// 计算月份差
			if (dayFlag)
				endMonth--;
			int tempMonth = endMonth - startMonth;
			if (tempMonth < 0) {
				monthFlag = true;
				month = 12 + tempMonth;
			} else {
				month = tempMonth;
			}

			// 计算年差
			if (monthFlag)
				endYear--;
			int tempYear = endYear - startYear;
			year = tempYear;

			// 封装结果
			resultArray[0] = year;
			resultArray[1] = month;
			resultArray[2] = day;

		} catch (Exception ex) {
			// log.error("计算时间段时失败！错误原因：" + ex.getMessage());
		}

		return resultArray;
	}
	
	/**
     * 计算两个日期之间相差的天数（有正负之分）
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int diffdates(Date date1, Date date2) {
        int result = 0;

        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        gc1.setTime(date1);
        gc2.setTime(date2);
        result = getDays(gc1, gc2);
        
        //date1大于date2时返回负数的天数
        if (gc1.after(gc2)) {
        	result = 0-result;
        }

        return result;
    } 


    public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
    	  int elapsed = 0;
    	  GregorianCalendar gc1, gc2;

    	  if (g2.after(g1)) {
    	   gc2 = (GregorianCalendar) g2.clone();
    	   gc1 = (GregorianCalendar) g1.clone();
    	  } else {
    	   gc2 = (GregorianCalendar) g1.clone();
    	   gc1 = (GregorianCalendar) g2.clone();
    	  }

    	  gc1.clear(Calendar.MILLISECOND);
    	  gc1.clear(Calendar.SECOND);
    	  gc1.clear(Calendar.MINUTE);
    	  gc1.clear(Calendar.HOUR_OF_DAY);

    	  gc2.clear(Calendar.MILLISECOND);
    	  gc2.clear(Calendar.SECOND);
    	  gc2.clear(Calendar.MINUTE);
    	  gc2.clear(Calendar.HOUR_OF_DAY);

    	  while (gc1.before(gc2)) {
    	   gc1.add(Calendar.DATE, 1);
    	   elapsed++;
    	  }
    	  return elapsed;
    	 }

    
    /**
     * 计算特定日期加上/减去数组的中年月日对应的日期        
     * @param baseDate
     * @param overplus 格式"X年X个月X天"
     * @param sign +(加上)，-(减去)
     * @return
     */
	public static Date getDateForOverplus(Date baseDate,String overplus,String sign) {
		return getCalendarForOverplus(baseDate,overplus,sign).getTime();
	}

	/**
	 * 计算特定日期加上/减去数组的中年月日对应Calendar类型  
	 * @param baseDate
	 * @param overplus
	 * @param sign
	 * @return
	 */
	public static Calendar getCalendarForOverplus(Date baseDate,String overplus,String sign){
		// 解析传入X年X个月X天字符
		Calendar c1 = Calendar.getInstance();
		c1.setTime(baseDate);
		int year = 0;				//年
		int month = 0;				//月
		int day = 0;				//天
		int indexYear = overplus.indexOf("年");
		int indexMonth1 = overplus.indexOf("个");
		int indexMonth2 = overplus.indexOf("月");
		int indexDay = overplus.indexOf("天");
		boolean flag = false;
		
		try {
			
			if (indexYear != -1) {
				year = Integer.parseInt(overplus.substring(0,indexYear));
				flag = true;
			}
			if (indexMonth1 != -1) {
				month = Integer.parseInt(overplus.substring(indexYear+1,indexMonth1));
				flag = true;
			}
			if (indexDay != -1) {
				day = Integer.parseInt(overplus.substring(indexMonth2+1,indexDay));
				flag = true;
			}

			if (!flag)
				year = Integer.valueOf(overplus);
			
			System.out.println(year + "年" + month +"个月" + day + "天");
			
			//add
			if("+".equals(sign)){
				c1.set(c1.YEAR, c1.get(c1.YEAR) + year);
				c1.set(c1.MONTH, c1.get(c1.MONTH) + month);
				c1.set(c1.DAY_OF_MONTH, c1.get(c1.DAY_OF_MONTH) + day);
			}else if("-".equals(sign)){
				c1.set(c1.YEAR, c1.get(c1.YEAR) - year);
				c1.set(c1.MONTH, c1.get(c1.MONTH) - month);
				c1.set(c1.DAY_OF_MONTH, c1.get(c1.DAY_OF_MONTH) - day);
			}else
				System.out.println("ERROR:未计算！");
				
			
		} catch (Exception ex) {
			 System.out.println("计算时间段时失败！错误原因：" + ex.getMessage());
		}
		
		return c1;
	}
	
	
	 /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }
    
	/**
	 * 字符串转换为时间戳，只到秒
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static long strToLongDate(String dateStr, String format) {
		long result = 0l;
		Date date = strToDate(dateStr, format);
		if (date != null) {
			result = date.getTime() / 1000;
		}
		return result;
	}
	
	/**
	 * 将时间搓转换成指定的时间格式字符串
	 * @param time  时间搓
	 * @param format 格式
	 * @return
	 */
	public static  String  Long2DateStr(Long  time,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String sd = sdf.format(new Date(time*1000));   // 时间戳转换成时间
        return sd;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    public static void main(String[] args) {
        System.out.println("昨天0点时间："+ getLastDayTimesMorning().toLocaleString());
        System.out.println("昨天23点时间："+ getLastDayTimesWeeHours().toLocaleString());
        System.out.println("当天零点时间："+ getTimesWeeHours().toLocaleString());
        System.out.println("当天早上十点："+ getTimesMorningTen().toLocaleString());
        System.out.println("当天晚上十点："+ getTimesWeeHoursTen().toLocaleString());
		System.out.println("当天凌晨时间："+ getTimesMorning().toLocaleString());
		System.out.println("昨天凌晨五点时间："+ getLastDayTimesWeeHoursFive().toLocaleString());
		System.out.println("今天凌晨五点时间："+ getTodayTimesWeeHoursFive().toLocaleString());
        System.out.println("下一天五点时间："+ getNextDayTimesWeeHoursFive().toLocaleString());
		System.out.println("当天23点时间："+ getTimesWeeHours().toLocaleString());
        System.out.println("本周结束时间："+ getTimesCurrentWeekEnd().toLocaleString());
        System.out.println("本周开始时间："+ getTimesCurrentWeekBegin().toLocaleString());
        System.out.println("时间差："+ String.valueOf(getLastDayTimesWeeHours().getTime() - getLastDayTimesMorning().getTime()));
        System.out.println("时间差："+ String.valueOf(getLastDayTimesWeeHours().getTime() - getTimesMorning().getTime()));

        Date currentDate = new Date();
        System.out.println((currentDate.compareTo(DateTimeUtil.getTimesMorningTen()) < 0) || (currentDate.compareTo(DateTimeUtil.getTimesWeeHoursTen()) > 0));

		System.out.println(hoursSubTime(getLastDayTimesWeeHours(),getLastDayTimesMorning()));
       	System.out.println("本周周一0点时间："+ getTimesCurrentWeekBegin().toLocaleString());
        System.out.println("本周周日24点时间："+ getTimesCurrentWeekEnd().toLocaleString());


		System.out.println("上周一0点时间："+ getTimePreviousWeekBegin().toLocaleString());
		System.out.println("上周周日24点时间："+ getTimePreviousWeekEnd().toLocaleString());


        System.out.println(dateToStr(plusDateAndSubtractHour(new Date(),1,1)));
        System.out.println(dateToStr(subtractAayAndTime(new Date(),1,1)));

        /* System.out.println("上周周一0点时间："+ getTimePreviousWeekBegin().toLocaleString());
        System.out.println("上周周日24点时间："+ getTimePreviousWeekEnd().toLocaleString());



        System.out.println("本月初0点时间："+ getTimesCurrentMonthBegin().toLocaleString());
        System.out.println("本月末24点时间："+ getTimesCurrentMonthEnd().toLocaleString());

        System.out.println("上月初0点时间："+ getTimesPreviousMonthBegin().toLocaleString());
        System.out.println("上月末24点时间："+ getTimesPreviousMonthEnd().toLocaleString());


        System.out.println("本年初0点时间："+ getTimesCurrentYearBegin().toLocaleString());
        System.out.println("本年末24点时间："+ getTimesCurrentYearEnd().toLocaleString());

        System.out.println("上年初0点时间："+ getTimesPreviousYearBegin().toLocaleString());
        System.out.println("上年末24点时间："+ getTimesPreviousYearEnd().toLocaleString());*/
	}


    /**
     * 今天凌晨五点
     * @return
     */
    public static Date getTodayTimesWeeHoursFive() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 05);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return  calendar.getTime();
    }



	/**
	 * 昨天0点时间
	 * @return
	 */
	public static Date getLastDayTimesMorning() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 当天时间下一天凌晨五点
	 * @return
	 */
	public static Date getNextDayTimesWeeHoursFive() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,  +1);
		calendar.set(Calendar.HOUR_OF_DAY, 05);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.MILLISECOND, 00);
		return  calendar.getTime();
	}

	/**
	 * 昨天凌晨五点
	 * @return
	 */
	public static Date getLastDayTimesWeeHoursFive() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,  -1);
		calendar.set(Calendar.HOUR_OF_DAY, 05);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.MILLISECOND, 00);
		return  calendar.getTime();
	}

	/**
	 * 昨天24点时间
	 * @return
	 */
	public static Date getLastDayTimesWeeHours() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,  -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return  calendar.getTime();
	}


    /**
     * 当天早上十点时间
     * @return
     */
    public static Date getTimesMorningTen() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天晚上十点时间
     * @return
     */
    public static Date getTimesWeeHoursTen() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 22);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return  cal.getTime();
    }

	/**
     * 当天00:00:00时间
     * @return
     */
    public static Date getTimesMorning() {
		Calendar cal =  Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

    /**
     * 当天23点59分59秒
     * @return
     */
    public static Date getTimesWeeHours() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return  cal.getTime();
    }

    /**
     * 上周开始时间
     * @return
     */
    public static Date getTimePreviousWeekBegin(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //设置时间为周一
        cal.add(Calendar.DATE, -7);
        return  cal.getTime();
    }

    /**
     * 上周结束时间
     * @return
     */
    public static Date getTimePreviousWeekEnd(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //设置时间为周一
        cal.add(Calendar.DATE, -1);
        return  cal.getTime();
    }

    /**
     * 本周一0点时间
     * @return
     */
    public static Date getTimesCurrentWeekBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return  cal.getTime();
    }

    /**
     * 本周日24点时间
     * @return
     */
    public  static Date getTimesCurrentWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesCurrentWeekBegin());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }

    /**
     * 本月第一天0点时间
     * @return
     */
    public static Date getTimesCurrentMonthBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return  cal.getTime();
    }


    /**
     * 上个月开始时间
     * @return
     */
    public static Date getTimesPreviousMonthBegin() {
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); //getActualMaximum代表拿到一个月内最大的天数

        return calendar.getTime();
    }

    /**
     * 上月最后一天24点时间
     * @return
     */
    public static Date getTimesPreviousMonthEnd() {
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); //getActualMaximum代表拿到一个月内最小的天数

        return calendar.getTime();
    }

    /**
     * 本月最后一天24点时间
     * @return
     */
    public static Date getTimesCurrentMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }


    /**
     * 本年开始时间
     * @return
     */
    public static Date getTimesCurrentYearBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }


    /**
     * 本年结束时间
     * @return
     */
    public static Date getTimesCurrentYearEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    /**
     * 上一年结束时间
     * @return
     */
    public static Date getTimesPreviousYearEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        int year = cal.get(Calendar.YEAR); //当年
        cal.set(Calendar.YEAR,year - 1); //设置年数目为现在年份减1
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    /**
     * 上一年开始时间
     * @return
     */
    public static Date getTimesPreviousYearBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        int year = cal.get(Calendar.YEAR); //当年
        cal.set(Calendar.YEAR,year - 1); //设置年数目为现在年份减1
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

}
