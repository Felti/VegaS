package com.vega.app.exceptions;

public class ObjectAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 388882136694879185L;

	private final String message;

	public ObjectAlreadyExists(String className) {
		super();
		this.message = "Object " + className + " Already exists";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
