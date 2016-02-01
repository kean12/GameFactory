package com.game.util.system.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 4233489944402058652L;

	/** DOCUMENT ME! */
	private Log log = LogFactory.getLog(getClass());

	/** DOCUMENT ME! */
	private Throwable rootCause;

	/**
	 * default constructor
	 */
	public BaseRuntimeException() {
		super();
	}

	/**
	 * @param arg0
	 *            message
	 */
	public BaseRuntimeException(final String arg0) {
		//super(arg0);
		this(arg0, null);
		rootCause = this;
	}

	/**
	 * @param arg0
	 *            throwable
	 */
	public BaseRuntimeException(final Throwable arg0) {
		this("", arg0);
	}

	/**
	 * @param arg0
	 *            message
	 * @param arg1
	 *            throwable
	 */
	public BaseRuntimeException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);

		if (arg1 instanceof BaseRuntimeException) {
			rootCause = ((BaseRuntimeException) arg1).rootCause;
		} else {
			rootCause = arg1;
		}

		log.error(arg0, arg1);
	}
}