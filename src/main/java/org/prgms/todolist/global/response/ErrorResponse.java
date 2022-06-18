package org.prgms.todolist.global.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.prgms.todolist.global.error.exception.ErrorCodedException;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ErrorResponse {
	private int status;
	private String code;
	private String message;

	public ErrorResponse(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public static ErrorResponse of(ErrorCodedException ex) {
		return new ErrorResponse(
			ex.getHttpStatus()
			  .value(),
			ex.getErrorCode(),
			ex.getMessage()
		);
	}
}
