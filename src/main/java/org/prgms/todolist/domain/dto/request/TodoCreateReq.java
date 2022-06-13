package org.prgms.todolist.domain.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TodoCreateReq {

	@NotBlank
	private String content;
	private String status_str;

}
