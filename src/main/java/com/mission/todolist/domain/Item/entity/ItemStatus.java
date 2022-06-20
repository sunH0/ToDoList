package com.mission.todolist.domain.Item.entity;

import com.mission.todolist.global.error.BusinessException;
import com.mission.todolist.global.error.ErrorCode;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ItemStatus {
	COMPLETE,
	ACTIVE;

	public static ItemStatus of(String status) {
		return Arrays.stream(values())
			.filter(code -> code.name().equalsIgnoreCase(status))
			.findFirst()
			.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));

	}

	private static final Map<String, ItemStatus> STATUS_MAP =
		Collections.unmodifiableMap(Stream
										.of(values())
										.collect(Collectors.toMap(Enum::name, Function.identity())));

	public static ItemStatus find(String status) {
		return Optional.ofNullable(STATUS_MAP.get(status)).orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));
	}

}

