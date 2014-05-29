package com.management.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串辅助
 * @author huyh
 *
 */
public class StringUtil {

	/**
	 * 正则校验
	 * @param strValue
	 * @param regular
	 * @return
	 */
	public static boolean regStr(String strValue,String regular){
		strValue = strValue.toUpperCase();
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(strValue);;
		return matcher.matches();
	}
	
	/**
	 * 正则截取字符串
	 * @param strValue
	 * @param regular
	 * @return
	 */	
	public static String intercepStr(String strValue,String regular){
		Pattern pattern = Pattern.compile(regular); 
		Matcher matcher = pattern.matcher(strValue);  
		if (matcher.find()) { 
			return matcher.group(1);
		}
		return "";
	}
	

	public static void main(String[] args)  { 
		int count = 3;
		Pattern pattern = Pattern.compile(count+"=(\\d*?),"); 
		Matcher matcher = pattern.matcher("1=11,2=998,3=1234,4=9999,");  
		if (matcher.find()) { 
			String temp = matcher.group(1);
			System.out.println(temp);  
			System.out.println("11 ".trim().toUpperCase());
         }
	}
}
