package com.mission.todolist.domain.Item.dto;

import com.mission.todolist.domain.Item.entity.Item;
import com.mission.todolist.domain.Item.entity.ItemStatus;
import java.time.LocalDate;
import lombok.Getter;

public class ItemResponse {

	@Getter
	public static class ItemInfoResponse {
		private final Long id;
		private final String content;
		private final LocalDate updatedAt;
		private final ItemStatus status;

		public ItemInfoResponse(Long id, String content, LocalDate updatedAt, ItemStatus status) {
			this.id = id;
			this.content = content;
			this.updatedAt = updatedAt;
			this.status = status;
		}

		public static ItemInfoResponse of(Item item){
			return new ItemInfoResponse(item.getId(), item.getContents(), item.getModifiedAt(), item.getStatus());
		}

	}

}
