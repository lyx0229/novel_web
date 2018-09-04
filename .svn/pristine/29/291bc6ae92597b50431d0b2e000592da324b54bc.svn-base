package com.novel.common.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static void main(String[] args) throws Exception {
		Date date1=monthAddFrist(new Date(),1);
		Date date2=monthAddFrist(new Date(),12);
		System.out.println(date1);
		System.out.println(date2);
		
	}

	/**
	 * 获取当天起始时间
	 * 
	 * @return
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		todayStart.set(Calendar.HOUR_OF_DAY, 23);
		return todayStart.getTime();
	}

	/**
	 * 获取当天终止时间
	 * 
	 * @return
	 */
	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		return todayEnd.getTime();
	}

	public static Date monthAddFrist(Date date,int term) {
		Calendar ct = Calendar.getInstance();
		ct.setTime(date);
		ct.add(Calendar.MONTH, +term);
		return ct.getTime();
	}

}
