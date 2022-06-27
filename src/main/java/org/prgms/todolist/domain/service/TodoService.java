package org.prgms.todolist.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.prgms.todolist.domain.dto.request.TodoCreateReq;
import org.prgms.todolist.domain.dto.request.TodoUpdateReq;
import org.prgms.todolist.domain.dto.response.TodoResp;
import org.prgms.todolist.domain.entity.Todo;
import org.prgms.todolist.domain.entity.TodoStatus;
import org.prgms.todolist.domain.repository.TodoRepository;
import org.prgms.todolist.global.error.ErrorCode;
import org.prgms.todolist.global.error.exception.NotFoundResourceException;
import org.prgms.todolist.global.mapper.TodoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
	private final TodoRepository repository;

	@Transactional
	public TodoResp createTodo(TodoCreateReq request) {
		final Todo todo = repository.save(TodoMapper.INSTANCE.intoEntity(request));
		return TodoResp.builder()
					   .content(todo.getContent())
					   .status(todo.getStatus())
					   .build();
	}

	public TodoResp fetchTodo(Long id) {
		Todo todo = getTodo(id);
		return TodoMapper.INSTANCE.intoResp(todo);
	}

	public List<TodoResp> fetchTodos(String status) {
		List<Todo> todoList;
		if (status.isEmpty()) {
			todoList = repository.findAll();
		} else {
			todoList = repository.findAllByStatus(TodoStatus.valueOf(status.toUpperCase()));
		}
		return todoList.stream()
					   .map(TodoMapper.INSTANCE::intoResp)
					   .collect(Collectors.toList());
	}

	@Transactional
	public TodoResp updateTodo(Long id, TodoUpdateReq todoUpdateReq) {
		Todo todo = getTodo(id);
		todo.updateContent(todoUpdateReq.getContent());
		return TodoMapper.INSTANCE.intoResp(todo);
	}

	@Transactional
	public void deleteTodo(Long id) {
		if (existById(id)) {
			repository.deleteById(id);
		}
		throw new NotFoundResourceException(ErrorCode.NOT_FOUND_TODO);
	}

	@Transactional
	public TodoResp changeStatus(Long id, String status) {
		Todo todo = getTodo(id);
		todo.updateStatus(TodoStatus.valueOf(status));
		return TodoMapper.INSTANCE.intoResp(todo);
	}

	private boolean existById(Long id) {
		return repository.existsById(id);
	}

	public Todo getTodo(Long id) {
		return repository.findById(id)
						 .orElseThrow(() -> new NotFoundResourceException(ErrorCode.NOT_FOUND_TODO));
	}

}
