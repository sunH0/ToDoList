package org.prgms.todolist.domain.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prgms.todolist.domain.entity.TodoStatus;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoResp {
	private Long id;
	private String content;
	private TodoStatus status;
	private LocalDate updatedAt;
}
