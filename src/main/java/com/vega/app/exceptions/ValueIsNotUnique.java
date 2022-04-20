package com.vega.app.exceptions;

public class ValueIsNotUnique extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5298355015536307220L;
	private final String message;

	public ValueIsNotUnique(String value) {
		super();
		message = value + " already exists, please provide another value";
	}

	@Override
	public String getMessage() {
		return message;
	}

}
