package org.prgms.todolist.domain.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TodoUpdateReq {

	@NotBlank
	private String content;
}
