package org.prgms.todolist.global.error.exception;

import lombok.Getter;
import org.prgms.todolist.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidArgumentException extends ErrorCodedException {

	public InvalidArgumentException(ErrorCode errorCode) {
		super(HttpStatus.BAD_REQUEST, errorCode);
	}
}
