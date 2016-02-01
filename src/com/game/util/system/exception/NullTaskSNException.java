package com.game.util.system.exception;

public class NullTaskSNException extends BusinessException {
	private static final long serialVersionUID = 4598687695576790131L;

	/**
	 *  
	 */
	public NullTaskSNException() {
		super();
	}

	/**
	 * @param s
	 */
	public NullTaskSNException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public NullTaskSNException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public NullTaskSNException(Throwable e) {
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