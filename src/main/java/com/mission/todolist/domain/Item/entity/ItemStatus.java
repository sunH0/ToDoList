package com.mission.todolist.domain.Item.entity;

import com.mission.todolist.global.error.BusinessException;
import com.mission.todolist.global.error.ErrorCode;
import java.util.Arrays;

public enum ItemStatus {
	COMPLETE,
	ACTIVE;

	public static ItemStatus of(String status) {
		return Arrays.stream(values())
			.filter(code -> code.name().equalsIgnoreCase(status))
			.findFirst()
			.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));

	}

}

