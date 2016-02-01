/*
 * Created on 2003-9-30
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.game.util.system.exception;

public class DAOException extends BaseRuntimeException {
	private static final long serialVersionUID = -4020626577696390768L;

	/**
	 *  
	 */
	public DAOException() {
		super();
	}

	/**
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}