package com.game.util.system.exception;

public class RightExistException extends BaseException {
	private static final long serialVersionUID = 1994332555985786971L;

	/**
	 *  
	 */
	public RightExistException() {
		super();
	}

	/**
	 * @param s
	 */
	public RightExistException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public RightExistException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public RightExistException(Throwable e) {
		super(e);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param args
	 *            DOCUMENT ME!
	 */
	public static void main(String[] args) {
	}
}