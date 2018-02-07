package com.emotte.eserver.core.db.exception;

import com.emotte.eserver.core.exception.BaseException;

public class ConnectionException extends BaseException {

	/**
	 * TODO
	 * 2017年4月10日
	 */
	private static final long serialVersionUID = 1L;
	public ConnectionException() {
		super();
	}

	public ConnectionException(String message) {
		super(message);
	}
	
	public ConnectionException(Throwable e) {
		super(e);
	}
}
