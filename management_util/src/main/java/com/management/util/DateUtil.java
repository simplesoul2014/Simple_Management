package com.management.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author huyh
 *
 */
public class DateUtil {

	/**
	 * 获得系统当前时间串,时间格式为yyyyMMddHHmmss [20041120203020]
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		return date2String(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDate() throws ParseException{
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormater.parse(dateFormater.format(date));
	
	}
	/**
	 * 使用指定的格式格式当前的日期对象
	 * 
	 * @param obj Date 要格式化的时间对象 为空时返回但前时间
	 * @param format String 指定的格式
	 * @return String 返回的时间串
	 */
	public static String date2String(Date date, String format) {
		if (date == null) {
			date = new Date();
		}

		SimpleDateFormat dateFormater = new SimpleDateFormat(format);
		return dateFormater.format(date);
	}
	
	/**
	 * 时间比较 
	 * @param time1
	 * @param time2
	 * @return 0表式不在48小时之内，1表式在48小时内
	 * @throws ParseException
	 */
	public static int compareDate(String time1,String time2) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = df.parse(time1);
		Date date=df.parse(time2);
		long l=(now.getTime()-date.getTime())/1000;
		if(l-(48*60*60)>0){
			return 0;
		}
		return 1;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String args[]){
		try{
			compareDate(getCurrentTime(),"2013-01-20 09:30:24");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
