package org.prgms.todolist.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

	INTERNAL_SERVER_ERROR("G001", "정의되지 않은 에러가 발생했습니다."),
	INVALID_INPUT_VALUE("G002", "올바른 입력 형식이 아닙니다."),
	NOT_FOUND_TODO("T001", "존재하지 않는 TODO 입니다.");


	private final String code;
	private final String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
