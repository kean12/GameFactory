package com.game.util.system.exception;

public class BusinessException extends BaseRuntimeException {
	private static final long serialVersionUID = -5615761416288718736L;

	/**
	 *  
	 */
	public BusinessException() {
		super();
	}

	/**
	 * @param s
	 */
	public BusinessException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public BusinessException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public BusinessException(Throwable e) {
		super(e);
	}
}