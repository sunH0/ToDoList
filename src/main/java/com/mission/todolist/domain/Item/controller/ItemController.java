package com.mission.todolist.domain.Item.controller;

import com.mission.todolist.domain.Item.dto.ItemRequest;
import com.mission.todolist.domain.Item.dto.ItemResponse;
import com.mission.todolist.domain.Item.entity.ItemStatus;
import com.mission.todolist.domain.Item.service.ItemService;
import com.mission.todolist.domain.Item.valid.EnumTypeValid;
import com.mission.todolist.domain.common.ApiResponse;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class ItemController {

	private final ItemService itemService;

	@PostMapping
	public ApiResponse<ItemResponse.ItemInfoResponse> addItem(@RequestBody @Validated ItemRequest.CreateRequest request){
		ItemResponse.ItemInfoResponse response = itemService.createItem(request);

		return ApiResponse.ok(response);
	}

	@GetMapping
	public ApiResponse<List<ItemResponse.ItemInfoResponse>> getAllTodoByStatus(@EnumTypeValid(target = ItemStatus.class, isNull = true, ignoreCase = true) @RequestParam(name = "status") String status) {
		List<ItemResponse.ItemInfoResponse> responses = itemService.getAllTodos(status);

		return ApiResponse.ok(responses);
	}

	@PatchMapping("/{id}/{status}")
	public ApiResponse<ItemResponse.ItemInfoResponse> changeStatus(@PathVariable(name = "id") long id, @EnumTypeValid(target = ItemStatus.class, ignoreCase = true) @PathVariable(name = "status") String status) {
		ItemResponse.ItemInfoResponse response = itemService.changeStatus(id, status);

		return ApiResponse.ok(response);
	}

	@PutMapping("/{id}")
	public ApiResponse<ItemResponse.ItemInfoResponse> updateTodo(@PathVariable(name = "id") long id, @RequestBody @Validated ItemRequest.UpdateRequest content) {
		ItemResponse.ItemInfoResponse response = itemService.amendComment(id, content);

		return ApiResponse.ok(response);
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteTodo(@PathVariable(name = "id") long id) {
		itemService.removeItem(id);
		return ApiResponse.ok();
	}

}
