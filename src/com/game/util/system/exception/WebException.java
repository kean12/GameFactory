package com.game.util.system.exception;

public class WebException extends BaseException {
	private static final long serialVersionUID = -7692420887258625536L;

	/**
	 *  
	 */
	public WebException() {
		super();
	}

	/**
	 * @param s
	 */
	public WebException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public WebException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public WebException(Throwable e) {
		super(e);
	}
}