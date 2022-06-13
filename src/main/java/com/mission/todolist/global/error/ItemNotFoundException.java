package com.mission.todolist.global.error;

public class ItemNotFoundException extends BusinessException{

	public ItemNotFoundException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public ItemNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

}
