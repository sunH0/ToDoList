package org.prgms.todolist.global.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.prgms.todolist.domain.entity.TodoStatus;
import org.prgms.todolist.domain.dto.request.TodoCreateReq;
import org.prgms.todolist.domain.dto.response.TodoResp;
import org.prgms.todolist.domain.entity.Todo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {

	TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

	@Mapping(source = "status_str", target = "status")
	Todo intoEntity(TodoCreateReq request);
	TodoResp intoResp(Todo todo);

	default TodoStatus toTodoStatus(String status) {
		if (status == null) {
			return TodoStatus.ACTIVE;
		}
		return TodoStatus.valueOf(status.toUpperCase());
	}
}
