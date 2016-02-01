package com.game.util.system.exception;

/**
 * @author Administrator To change the template for this generated type comment
 *         go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public class AccountAlreadyExistException extends BusinessException {
    
	private static final long serialVersionUID = -5055313828633739939L;

	public AccountAlreadyExistException() {
		super();
	}

	public AccountAlreadyExistException(String s) {
		super(s);
	}

	public AccountAlreadyExistException(String s, Throwable e) {
		super(s, e);
	}

	public AccountAlreadyExistException(Throwable e) {
		super(e);
	}
}