package org.prgms.todolist.global.error;

import lombok.extern.slf4j.Slf4j;
import org.prgms.todolist.global.error.exception.ErrorCodedException;
import org.prgms.todolist.global.error.exception.InvalidArgumentException;
import org.prgms.todolist.global.error.exception.NotFoundResourceException;
import org.prgms.todolist.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleErrorCodedException(Exception ex) {
		log.warn("Exception: {}", ex.getMessage());
		ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
		return ResponseEntity
			.internalServerError()
			.body(new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				errorCode.getCode(),
				errorCode.getMessage()
			));
	}

	@ExceptionHandler(NotFoundResourceException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundResourceException(NotFoundResourceException ex) {
		log.warn("Exception: {}", ex.getMessage());
		return ResponseEntity
			.status(ex.getHttpStatus())
			.body(ErrorResponse.of(ex));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
		log.warn("Exception: {}", ex.getMessage());
		final ErrorResponse error = ErrorResponse.of(
			new InvalidArgumentException(ErrorCode.INVALID_INPUT_VALUE));
		return ResponseEntity
			.status(error.getStatus())
			.body(error);
	}
}
