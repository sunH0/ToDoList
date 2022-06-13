package com.mission.todolist.domain.Item.dto;

import com.mission.todolist.domain.Item.entity.Item;
import com.mission.todolist.domain.Item.entity.ItemStatus;
import com.mission.todolist.domain.Item.valid.EnumTypeValid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ItemRequest {

	@Getter
	public static class CreateRequest {

		@NotBlank
		@Size(max = 50)
		private final String content;
		@EnumTypeValid(target = ItemStatus.class, message = "잘못된 Status 값 입니다.", ignoreCase = true)
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

		@NotBlank
		@Size(max = 50)
		private String content;

		public UpdateRequest(String content) {
			this.content = content;
		}

	}

}
