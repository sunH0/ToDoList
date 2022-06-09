package com.mission.todolist.global.error;


import lombok.Getter;

@Getter
public enum  ErrorCode {

	// Common
	INTERNAL_SERVER_ERROR(500,"Server Error"),
	INVALID_INPUT_VALUE(400,"입력값을 다시 확인해주세요."),


	ITEM_NOT_FOUND(404, "아이템을 찾을 수 없습니다");

	private final int status;
	private final String message;

	ErrorCode(final int status, final String message) {
		this.status = status;
		this.message = message;
	}

}
