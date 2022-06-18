package org.prgms.todolist.global.error.exception;

import lombok.Getter;
import org.prgms.todolist.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundResourceException extends ErrorCodedException {

	public NotFoundResourceException(ErrorCode errorCode) {
		super(HttpStatus.NOT_FOUND, errorCode);
	}

}
