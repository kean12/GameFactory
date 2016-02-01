package com.game.util.system.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1438097935068478635L;

	/** DOCUMENT ME! */
	private Log log = LogFactory.getLog(getClass());

	/** DOCUMENT ME! */
	private Throwable rootCause;

	/**
	 *  
	 */
	public BaseException() {
		super();
	}

	/**
	 * @param s
	 */
	public BaseException(String s) {
		this(s, null);
		rootCause = this;
	}

	/**
	 * Creates a new BaseException object.
	 * 
	 * @param s
	 *            DOCUMENT ME!
	 * @param e
	 *            DOCUMENT ME!
	 */
	public BaseException(String s, Throwable e) {
		super(s);

		if (e instanceof BaseException) {
			rootCause = ((BaseException) e).rootCause;
		} else {
			rootCause = e;
		}

		log.info(s, e);
	}

	/**
	 * Creates a new BaseException object.
	 * 
	 * @param e
	 *            DOCUMENT ME!
	 */
	public BaseException(Throwable e) {
		this("", e);
	}

	/**
	 * @return
	 */
	public Throwable getRootCause() {
		return rootCause;
	}
}