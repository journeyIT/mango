package com.louis.mango.common.utils;

/**
 * @Author: journey
 * @Date: 2020/2/18
 * @Time: 1:42 下午
 * @Description: 字符串工具类
 */
public class StringUtils {

	/**
	 * 判空操作
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
	}

}
