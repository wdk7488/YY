package com.ssh.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	public static final String YYYYMMDD = "yyyyMMdd";
	
	public static final String YYYYMMDD2 = "yyyy年MM月dd日";
	public static final String YYYYMMDD3 = "yyyy/MM/dd日";
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	
	private static final String YYYY_MM_DDREGEX = "^[1-9]\\d{3}-(0[1-9]|1[0-2]|[1-9])-([1-9]|]0[1-9]|[1-2][0-9]|3[0-1])$";
	private static final String YYYYMMDDREGEX = "^[1-9]\\d{3}(0[1-9]|1[0-2]|[1-9])([1-9]|]0[1-9]|[1-2][0-9]|3[0-1])$";
	private static final String YYYY_MM_DDREGEX1 = "^[1-9]\\d{3}.(0[1-9]|1[0-2]|[1-9]).([1-9]|]0[1-9]|[1-2][0-9]|3[0-1])$";
	
	
	
	private static final String YYYY_MM_DD_HH_MM_SSREGEX = "^[1-9]\\d{3}-(0[1-9]|1[0-2]|[1-9])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
	private static final String YYYYMMDDHHMMSSREGEX = "^[1-9]\\d{3}(0[1-9]|1[0-2]|[1-9])(0[1-9]|[1-2][0-9]|3[0-1])+(20|21|22|23|[0-1]\\d)[0-5]\\d[0-5]\\d$";
	
	
	//默认输出YYYYMMDDHHMMSS格式
	public static String dateToString(Date date){
		return dateToString(date, YYYY_MM_DD_HH_MM_SS);
	}
	
	//自定义格式
	public static String dateToString(Date date,String dateFormat){
		if(null == date){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateStr = sdf.format(date);
		
		return dateStr;
	}
	
	/**
	 * 判断事件格式 自动转  时间格式没有对应则return null
	 * @param dateString
	 * @return 返回Date时间格式
	 * @throws ParseException 
	 */
	public static Date stringToDateCommon(String dateString) throws ParseException{ 
		if(null == dateString){
			log.info("[stringToDateCommon] param dateString is null return null");
			return null;
		}
		//Pattern pattern = Pattern.compile(regex);
		//Matcher matcher = pattern.matcher(dateString);
		//Pattern.matches(YYYY_MM_DDREGEX, dateString);
		if(Pattern.matches(YYYY_MM_DDREGEX, dateString)){
			return stringToDate(dateString, YYYY_MM_DD, YYYY_MM_DDREGEX);
		}
		else if(Pattern.matches(YYYY_MM_DD_HH_MM_SSREGEX, dateString)){
			return stringToDate(dateString, YYYY_MM_DD_HH_MM_SS, YYYY_MM_DD_HH_MM_SSREGEX);
		}
		else if(Pattern.matches(YYYYMMDDREGEX, dateString)){
			return stringToDate(dateString, YYYYMMDD, YYYYMMDDREGEX);
		}
		else if(Pattern.matches(YYYYMMDDHHMMSSREGEX, dateString)){
			return stringToDate(dateString, YYYYMMDDHHMMSS, YYYYMMDDHHMMSSREGEX);
		}
		else if(Pattern.matches(YYYY_MM_DDREGEX1, dateString)){
			return stringToDate(dateString, YYYYMMDD2, YYYYMMDDHHMMSSREGEX);
		}
		else if(Pattern.matches(YYYY_MM_DDREGEX1, dateString)){
			return stringToDate(dateString, YYYYMMDD3, YYYYMMDDHHMMSSREGEX);
		}
		return null;
	}
	
	
	public static Date stringToDate(String dateString,String format,String regex) throws ParseException{
		
		if(null == dateString || null == format || null == regex){
			log.info("[stringToDateCommon] param is null return null");
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Pattern pattern = Pattern.compile(regex);
		
		
		Matcher matcher = pattern.matcher(dateString);
		if(matcher.matches()){
			
			return sdf.parse(dateString);
			
		}
		
		
		return null;
	}
	
	public static Date stringToDate(String dateString) throws ParseException{
		return stringToDate(dateString,YYYY_MM_DD,YYYY_MM_DDREGEX);
		
	}
	
	public static Date StringToDateTime(String dateString) throws ParseException{
		return stringToDate(dateString,YYYY_MM_DD_HH_MM_SS,YYYY_MM_DD_HH_MM_SSREGEX);
	}
	
}
