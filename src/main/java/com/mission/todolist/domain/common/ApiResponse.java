package com.mission.todolist.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

	private int statusCode;
	private T data;

	public ApiResponse(final int statusCode, final T data) {
		this.statusCode = statusCode;
		this.data = data;
	}

	public static <T> ApiResponse<T> ok(final T data) {
		return new ApiResponse<>(200, data);
	}

	public static <T> ApiResponse<String> ok() {
		return new ApiResponse<>(200, "success");
	}

}
