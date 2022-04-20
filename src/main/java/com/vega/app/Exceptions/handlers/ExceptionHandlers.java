package com.vega.app.exceptions.handlers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vega.app.entities.CustomResponse;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<CustomResponse<Object>> accessDeniedException() {
		return new ResponseEntity<>(new CustomResponse<>("Accès non autorisé"), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<CustomResponse<Object>> badCredentialException() {
		return new ResponseEntity<>(new CustomResponse<>("Login/password incorrect"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DisabledException.class)
	public final ResponseEntity<CustomResponse<Object>> disabledAccountException() {
		return new ResponseEntity<>(new CustomResponse<>("Your account is disabled"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<Object> handleConstraintViolations(ConstraintViolationException cve) {
		return new ResponseEntity<>(new CustomResponse<>(cve.getCause().getMessage()), HttpStatus.CONFLICT);
	}
}
