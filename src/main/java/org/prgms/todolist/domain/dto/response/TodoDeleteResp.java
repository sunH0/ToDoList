package org.prgms.todolist.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDeleteResp {
	private final Long id;
	private static final String message = "Successfully deleted.";

	public TodoDeleteResp(Long id) {
		this.id = id;
	}
}
