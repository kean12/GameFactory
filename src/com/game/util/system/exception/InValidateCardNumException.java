package com.game.util.system.exception;

public class InValidateCardNumException extends BusinessException {
	private static final long serialVersionUID = 115304137780753250L;

	/**
	 *  
	 */
	public InValidateCardNumException() {
		super();
	}

	/**
	 * @param s
	 */
	public InValidateCardNumException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public InValidateCardNumException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public InValidateCardNumException(Throwable e) {
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