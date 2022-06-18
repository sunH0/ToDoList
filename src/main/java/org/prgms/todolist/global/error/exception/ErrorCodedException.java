package org.prgms.todolist.global.error.exception;

import lombok.Getter;
import org.prgms.todolist.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ErrorCodedException extends RuntimeException{
	protected final HttpStatus httpStatus;
	protected final String errorCode;

	protected ErrorCodedException(HttpStatus status, ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.httpStatus = status;
		this.errorCode = errorCode.getCode();
	}
}
