package com.wujq4java.commons.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 数据校验工具类
 * 
 * @author wu.jinqing
 * @date 2015年4月30日
 */
public class Assert {
	private static final String PHONE_NUMBER_REGEX = "^1[0-9]{10}$";
	private static final String EMAIL_REGEX = "^[0-9a-zA-Z][0-9a-zA-Z_]+@[0-9a-zA-Z]+(\\.[0-9a-zA-Z]+)+$";
	private static final String DATE_REGEX = "^[1-9][0-9]{3}((((0[13578])|(1[02]))((0[1-9])|([12][0-9])|(3[01])))|(((0[2469])|(11))((0[1-9])|([12][0-9])|(30))))$";
	private static final String TIME_REGEX = "^(([0-1][0-9])|(2[0-3]))[0-5][0-9][0-5][0-9]$";
	
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
	private static final Pattern TIME_PATTERN = Pattern.compile(TIME_REGEX);
	
	/**
	 * <pre>
	 * 判断字符串arg是否不为空，如果arg == null || arg.trim().length() == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isNotEmpty(String arg)
	{
		return (arg == null || arg.trim().length() == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断字符串arg是否为空，如果arg == null || arg.trim().length() == 0返回true，否则返回false
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(String arg)
	{
		return (arg == null || arg.trim().length() == 0) ? true : false;
	}
	
	/**
	 * <pre>
	 * 判断集合是否不为空，如果c == null || c.size() == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param c
	 * @return
	 */
	public static <T> boolean isNotEmpty(Collection<T> c)
	{
		return (c == null || c.size() == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断集合是否不为空，如果c == null || c.size() == 0返回true，否则返回false
	 * <pre>
	 * 
	 * @param c
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> c)
	{
		return (c == null || c.size() == 0) ? true : false;
	}
	
	/**
	 * <pre>
	 * 判断指定数组是否不为空，如果c == null || c.length == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param c
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] c)
	{
		return (c == null || c.length == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断指定数组是否为空，如果c == null || c.length == 0返回true，否则返回false
	 * <pre>
	 * 
	 * @param c
	 * @return
	 */
	public static <T> boolean isEmpty(T[] c)
	{
		return (c == null || c.length == 0) ? true : false;
	}
	
	/**
	 * <pre>
	 * 判断集合是否不为空，如果c == null || c.size() == 0返回false，否则返回true
	 * <pre>
	 * 
	 * @param m
	 * @return
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> m)
	{
		return (m == null || m.size() == 0) ? false : true;
	}
	
	/**
	 * <pre>
	 * 判断集合是否为空，如果c == null || c.size() == 0返回true，否则返回false
	 * <pre>
	 * 
	 * @param m
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> m)
	{
		return (m == null || m.size() == 0) ? true : false;
	}
	
	/**
	 * <pre>
	 *  判断arg是否不为null, 如果arg == null返回false，否则返回true
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static <T> boolean isNotNull(T arg)
	{
		return arg == null ? false : true;
	}
	
	/**
	 * <pre>
	 *  判断arg是否为null, 如果arg == null返回true，否则返回false
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static <T> boolean isNull(T arg)
	{
		return arg == null ? true : false;
	}
	
	/**
	 * <pre>
	 * 去除字符串arg两端的空格
	 * <pre>
	 * 
	 * @param arg
	 * @return  arg != null ? arg.trim() : null
	 */
	public static String trim(String arg)
	{
		return arg != null ? arg.trim() : null;
	}
	
	/**
	 * <pre>
	 * 去除字符串arg两端的空格
	 * <pre>
	 * 
	 * @param arg
	 * @return  arg != null ? arg.trim() : ""
	 */
	public static String trimToEmpty(String arg)
	{
		return arg != null ? arg.trim() : "";
	}
	
	/**
	 * <pre>
	 *  手机号验证
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isPhoneNumber(String arg)
	{
		return PHONE_NUMBER_PATTERN.matcher(trimToEmpty(arg)).matches();
	}
	
	/**
	 * <pre>
	 *  邮箱验证
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isEmail(String arg)
	{
		return EMAIL_PATTERN.matcher(trimToEmpty(arg)).matches();
	}
	
	/**
	 * <pre>
	 *  验证日期:1000年01月01日 - 9999年12月31日
	 * <pre>
	 * 
	 * @param arg 格式: yyyyMMdd
	 * @return
	 */
	public static boolean isDate(String arg)
	{
		return DATE_PATTERN.matcher(trimToEmpty(arg)).matches();
	}
	
	/**
	 * <pre>
	 *  验证时间:0时0分 - 23时59分
	 * <pre>
	 * 
	 * @param arg 格式: HHmm
	 * @return
	 */
	public static boolean isTime(String arg)
	{
		return TIME_PATTERN.matcher(trimToEmpty(arg)).matches();
	}
	
	
	public static void main(String[] args) {
		int year = 2015;
		
			for(int month = 1; month <= 12; month++)
			{
				String m = "";
				
				if(month < 10)
					m = "0"+month;
				else 
					m= month+"";
				
				
					
				if("|2|4|6|9|11|".contains("|" + month + "|"))
				{
					for(int day = 1; day <= 31; day++)
					{
						
						String d = "";
						
						if(day < 10)
							d = "0"+day;
						else
							d = ""+day;
						
						String arg = year+ m + d;
						
						if(isDate(arg))
							System.out.println(arg);
						
					}
				}else
				{
					for(int day = 1; day <= 31; day++)
					{
						String d = "";
						
						if(day < 10)
							d = "0"+day;
						else
							d = ""+day;
						
						String arg = year+ m + d;
						
						if(isDate(arg))
							System.out.println(arg);
					}
				}
				
			}
		
	System.out.println("success");
	}
}
