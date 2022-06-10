package com.mission.todolist.domain.Item.dto;

import com.mission.todolist.domain.Item.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ItemRequest {

	@Getter
	public static class CreateRequest {

		private final String content;
		private final String status;

		public CreateRequest(String content, String status) {
			this.content = content;
			this.status = status;
		}

		public Item toEntity(){
			return new Item(this.content);
		}

	}

	@Getter
	@NoArgsConstructor
	public static class UpdateRequest {

		private String content;

		public UpdateRequest(String content) {
			this.content = content;
		}

	}

}
