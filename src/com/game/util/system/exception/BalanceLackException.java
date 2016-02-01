package com.game.util.system.exception;

public class BalanceLackException extends RuntimeException {
	private static final long serialVersionUID = 3560873788598112285L;
	public BalanceLackException()
	{
		super();
	}
	public BalanceLackException(String mes)
	{
		super(mes);
	}

}
