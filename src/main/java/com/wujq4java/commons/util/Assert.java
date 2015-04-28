package com.wujq4java.commons.util;

import java.util.Collection;

public class Assert {
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
	 * 去除字符串arg两端的空格
	 * <pre>
	 * 
	 * @param arg
	 * @return
	 */
	public static String trim(String arg)
	{
		return arg != null ? arg.trim() : null;
	}
}
