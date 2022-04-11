package com.vega.app.entities;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponse<T> {

	private T data;
	private String message;
	private String detailedMessage;
	private LocalDateTime timestamp;

	public CustomResponse(String message) {
		super();
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	public CustomResponse(T data) {
		super();
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public CustomResponse(T data, String message) {
		super();
		this.data = data;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	public CustomResponse(T data, String message, String detailedMessage) {
		super();
		this.data = data;
		this.message = message;
		this.detailedMessage = detailedMessage;
		this.timestamp = LocalDateTime.now();
	}

}
