package com.game.util.web;

import java.util.List;

public class BinaryUtil {
	/**
	 * 对集合里面的数做 逻辑（|）的操作并返回
	 * @param ns Number...集合
	 * @return Number
	 */
	public static Number logic(Number... ns) {
		Number n = 0;
		for (Number number : ns) {
			n = n.intValue() | number.intValue();
		}
		return n;
	}

	/**
	 * 对集合里面的数做 逻辑（|）的操作并返回
	 * @param nl List<Number>集合
	 * @return Number
	 */
	public static Number logic(List<? extends Number> nl) {
		Number n = 0;
		for (Number number : nl) {
			n = n.intValue() | number.intValue();
		}
		return n;
	}

}
