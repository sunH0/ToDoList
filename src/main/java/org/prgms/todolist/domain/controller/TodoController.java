package org.prgms.todolist.domain.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgms.todolist.domain.dto.request.TodoCreateReq;
import org.prgms.todolist.domain.dto.request.TodoUpdateReq;
import org.prgms.todolist.domain.dto.response.TodoDeleteResp;
import org.prgms.todolist.domain.dto.response.TodoResp;
import org.prgms.todolist.domain.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@PostMapping
	public ResponseEntity<TodoResp> createTodo(@Valid @RequestBody TodoCreateReq todoCreateReq) {
		return ResponseEntity.ok(todoService.createTodo(todoCreateReq));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TodoResp> fetchTodo(@PathVariable Long id) {
		return ResponseEntity.ok(todoService.fetchTodo(id));
	}

	@GetMapping
	public ResponseEntity<List<TodoResp>> fetchTodos(@RequestParam String status) {
		return ResponseEntity.ok(todoService.fetchTodos(status));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TodoResp> updateTodo(
		@PathVariable Long id,
		@RequestBody TodoUpdateReq todoUpdateReq
	) {
		return ResponseEntity.ok(todoService.updateTodo(id, todoUpdateReq));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TodoDeleteResp> deleteTodo(@PathVariable Long id) {
		// TODO: 2022-06-14
		//  Map<String, String> result = new HashMap<>();
		//  result.put("message", "success");
		return ResponseEntity.ok(todoService.deleteTodo(id));
	}

	@PatchMapping("/{id}/{status}")
	public ResponseEntity<TodoResp> changeStatus(
		@PathVariable Long id,
		@PathVariable String status
	) {
		return ResponseEntity.ok(todoService.changeStatus(id, status));
	}

}
