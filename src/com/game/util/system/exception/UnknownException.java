package com.game.util.system.exception;


public class UnknownException extends BaseException {
	private static final long serialVersionUID = -3213220323455001439L;

	/**
	 *  
	 */
	public UnknownException() {
		super();
	}

	/**
	 * @param s
	 */
	public UnknownException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public UnknownException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public UnknownException(Throwable e) {
		super(e);
	}
}