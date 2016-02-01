package com.game.util.system.exception;

public class BeanException extends BaseException {
	private static final long serialVersionUID = -1663688840147947516L;

	/**
	 *  
	 */
	public BeanException() {
		super();
	}

	/**
	 * @param s
	 */
	public BeanException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public BeanException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public BeanException(Throwable e) {
		super(e);
	}
}