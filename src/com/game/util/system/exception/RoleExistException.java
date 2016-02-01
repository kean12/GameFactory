package com.game.util.system.exception;

public class RoleExistException extends BusinessException {
	private static final long serialVersionUID = -526197308007043749L;

	/**
	 *  
	 */
	public RoleExistException() {
		super();
	}

	/**
	 * @param s
	 */
	public RoleExistException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public RoleExistException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public RoleExistException(Throwable e) {
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