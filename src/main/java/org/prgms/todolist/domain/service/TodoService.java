package org.prgms.todolist.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.prgms.todolist.domain.entity.TodoStatus;
import org.prgms.todolist.domain.dto.request.TodoCreateReq;
import org.prgms.todolist.domain.dto.request.TodoUpdateReq;
import org.prgms.todolist.domain.dto.response.TodoDeleteResp;
import org.prgms.todolist.domain.dto.response.TodoResp;
import org.prgms.todolist.domain.entity.Todo;
import org.prgms.todolist.domain.repository.TodoRepository;
import org.prgms.todolist.global.mapper.TodoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository repository;

	public TodoResp createTodo(TodoCreateReq request) {
		final Todo todo = repository.save(TodoMapper.INSTANCE.intoEntity(request));
		return TodoResp.builder()
					   .content(todo.getContent())
					   .status(todo.getStatus())
					   .build();
	}


	public TodoResp fetchTodo(Long id) {
		return TodoMapper.INSTANCE.intoResp(repository.findById(id)
													  .orElseThrow(RuntimeException::new));
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

	public TodoResp updateTodo(Long id, TodoUpdateReq todoUpdateReq) {
		Todo todo = repository.findById(id)
							  .orElseThrow(RuntimeException::new);
		todo.updateContent(todoUpdateReq.getContent());
		return TodoMapper.INSTANCE.intoResp(todo);
	}

	public TodoDeleteResp deleteTodo(Long id) {
		repository.deleteById(id);
		return new TodoDeleteResp(id);
	}

	public TodoResp changeStatus(Long id, String status) {
		Todo todo = repository.findById(id)
							  .orElseThrow(RuntimeException::new);
		todo.updateStatus(TodoStatus.valueOf(status));
		return TodoMapper.INSTANCE.intoResp(todo);
	}

}
