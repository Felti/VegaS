package com.vega.app.exceptions.handlers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.vega.app.entities.CustomResponse;

@RestControllerAdvice
public class RestExceptionHundler {

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		if (ObjectUtils.isEmpty(statusCode)) return HttpStatus.INTERNAL_SERVER_ERROR;

		return HttpStatus.valueOf(statusCode);
	}

	@ResponseBody
	@ExceptionHandler(MultipartException.class)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	ResponseEntity<Object> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity(ex.getMessage(), status);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGeneralException(Exception ex) {
		return new ResponseEntity<>(new CustomResponse<>(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
