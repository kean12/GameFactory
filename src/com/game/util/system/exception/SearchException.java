package com.game.util.system.exception;

public class SearchException extends BaseRuntimeException {
	private static final long serialVersionUID = 13009568514305943L;

	/**
	 *  
	 */
	public SearchException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public SearchException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public SearchException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SearchException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}