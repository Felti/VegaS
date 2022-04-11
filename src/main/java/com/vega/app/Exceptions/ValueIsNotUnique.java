package com.vega.app.Exceptions;

public class ValueIsNotUnique extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5298355015536307220L;
	private String Message;

	public ValueIsNotUnique(String value) {
		super();
		Message = value + " already exists, please provide another value";
	}

	@Override
	public String getMessage() {
		return Message;
	}

}
