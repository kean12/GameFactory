package com.game.util.system.exception;

public class NoSuchTaskSNException extends BusinessException {
	private static final long serialVersionUID = 4330078791802446106L;

	/**
	 *  
	 */
	public NoSuchTaskSNException() {
		super();
	}

	/**
	 * @param s
	 */
	public NoSuchTaskSNException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public NoSuchTaskSNException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public NoSuchTaskSNException(Throwable e) {
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