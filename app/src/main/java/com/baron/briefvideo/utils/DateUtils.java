package com.baron.briefvideo.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * java时间处理工具
 * 
 * @author baron
 * 
 */
public class DateUtils {
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat format_Y_M_D_H_m_S = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat format_Y_M_D_00_00_01 = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat format_Y_M_D_23_59_59 = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat format_h_m_s = new SimpleDateFormat(
			"hh:mm:ss");

	public static String getNow() {
		return format_Y_M_D_H_m_S.format(new Date()) + "";
	}


	public static String getToday() {
		return format.format(new Date()) + "";
	}

	// 获当前月的第一天
	public static String getFirstDayForwardMonth() {
		Calendar cal_1 = Calendar.getInstance();
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	// 获当前月的最后一天
	public static String getEndDayForwardMonth() {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}

	// 获得当前月的第一天
	public static String getFirstDayNowMonth(SimpleDateFormat format_this) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String first = format_this.format(c.getTime());
		return first;
	}

	// 获得当前月的最后一天
	public static String getEndDayNowMonth(SimpleDateFormat format_this) {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format_this.format(ca.getTime());
		return last;
	}

	// 获得某月的最后一天
	public static String getEndDayOneMonth(SimpleDateFormat format_this,
			int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		String last = format_this.format(lastDate);
		return last;
	}

	// 获得某月的第一天
	public static String getFirstDayOneMonth(SimpleDateFormat format_this,
			int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		String first = format_this.format(firstDate);
		return first;
	}

	/* 
	  毫秒转化成时间
	*/  
	public static String formatTime(long ms) {  
	              
	             int ss = 1000;  
	             int mi = ss * 60;  
	             int hh = mi * 60;  
	             int dd = hh * 24;  
	  
	             long day = ms / dd;  
	             long hour = (ms - day * dd) / hh;  
	             long minute = (ms - day * dd - hour * hh) / mi;  
	             long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	             long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
	  
	             String strDay = day < 10 ? "0" + day : "" + day; //��  
	             String strHour = hour < 10 ? "0" + hour : "" + hour;//Сʱ  
	             String strMinute = minute < 10 ? "0" + minute : "" + minute;//����  
	             String strSecond = second < 10 ? "0" + second : "" + second;//��  
	             String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//����  
	             strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;  
	              
	             return strHour+":"+strMinute + ":" + strSecond + "";
	   }  
}
