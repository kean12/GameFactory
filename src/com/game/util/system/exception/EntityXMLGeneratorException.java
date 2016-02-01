/*
 * Created on 2003-11-1
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.game.util.system.exception;

public class EntityXMLGeneratorException extends BusinessException {
	private static final long serialVersionUID = -2133046811279387639L;

	/**
	 *  
	 */
	public EntityXMLGeneratorException() {
		super();
	}

	/**
	 * @param s
	 */
	public EntityXMLGeneratorException(String s) {
		super(s);
	}

	/**
	 * @param s
	 * @param e
	 */
	public EntityXMLGeneratorException(String s, Throwable e) {
		super(s, e);
	}

	/**
	 * @param e
	 */
	public EntityXMLGeneratorException(Throwable e) {
		super(e);
	}
}
