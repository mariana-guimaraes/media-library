package com.mariana.media_library.web.error;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mariana.media_library.exception.TitleNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

	// 404
	@ExceptionHandler(TitleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleTitleNotFound(TitleNotFoundException ex, HttpServletRequest request) {
		return new ApiError(Instant.now(), 404, "TITLE_NOT_FOUND", ex.getMessage(), request.getRequestURI());
	}

	// 400
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
		return new ApiError(Instant.now(), 400, "VALIDATION_ERROR", "Invalid request data", request.getRequestURI());
	}

	// 500
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError handleUnexpectedError(Exception ex, HttpServletRequest request) {

		log.error("API_UNEXPECTED path={} exception={}", request.getRequestURI(), ex.getClass().getSimpleName(), ex);

		return new ApiError(Instant.now(), 500, "INTERNAL_ERROR", "An unexpected error occurred", request.getRequestURI());
	}
}
