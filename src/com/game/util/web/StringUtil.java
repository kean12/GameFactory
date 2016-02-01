package com.game.util.web;

/**
 * 字符串帮助类
 */
public final class StringUtil {

	/**
	 * 计算字符串长度（中文2个字符字母1个字符）
	 */
	public static int getLength(String str) {
		return str.replaceAll("[^\\x00-\\xff]", "**").length();
	}

}
