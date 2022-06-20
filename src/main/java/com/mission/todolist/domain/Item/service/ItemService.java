package com.mission.todolist.domain.Item.service;

import com.mission.todolist.domain.Item.dto.ItemRequest;
import com.mission.todolist.domain.Item.dto.ItemResponse;
import com.mission.todolist.domain.Item.entity.Item;
import com.mission.todolist.domain.Item.entity.ItemStatus;
import com.mission.todolist.domain.Item.repository.ItemRepository;
import com.mission.todolist.global.error.ErrorCode;
import com.mission.todolist.global.error.ItemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public ItemResponse.ItemInfoResponse createItem(ItemRequest.CreateRequest request){
		Item item = itemRepository.save(request.toEntity());

		return ItemResponse.ItemInfoResponse.of(item);
	}

	@Transactional
	public ItemResponse.ItemInfoResponse changeStatus(Long id, String status){
		Item item = getIndexById(id);
		item.changeStatus(ItemStatus.of(status));

		return ItemResponse.ItemInfoResponse.of(item);
	}

	@Transactional
	public ItemResponse.ItemInfoResponse amendComment(Long id, ItemRequest.UpdateRequest content) {
		Item item = getIndexById(id);
		item.amendContents(content.getContent());

		return ItemResponse.ItemInfoResponse.of(item);
	}

	public List<ItemResponse.ItemInfoResponse> getAllTodos(String status){
		List<Item> items;

		if(status.isEmpty()) {
			items = itemRepository.findAll();
		}else {
			items = itemRepository.findAllByStatus(ItemStatus.of(status));
		}

		List<ItemResponse.ItemInfoResponse> responses = new ArrayList<>();

		for (Item item : items) {
			responses.add(ItemResponse.ItemInfoResponse.of(item));
		}

		return responses;
	}

	@Transactional
	public void removeItem(Long id){
		if(!existsById(id)){
			throw new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND);
		}
		itemRepository.deleteById(id);
	}

	private boolean existsById(Long id){
		return itemRepository.existsById(id);
	}

	private Item getIndexById(Long id){
		return itemRepository
			.findById(id)
			.orElseThrow(() -> new ItemNotFoundException(ErrorCode.ITEM_NOT_FOUND));
	}

}
