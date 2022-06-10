package com.mission.todolist.domain.Item.entity;

import com.mission.todolist.domain.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contents", length = 50)
	private String contents;

	@Enumerated(EnumType.STRING)
	private ItemStatus status;

	public Item(String contents) {
		this.contents = contents;
		this.status = ItemStatus.ACTIVE;
	}

	public void changeStatus(ItemStatus status){
		this.status = status;
	}

	public void amendContents(String contents){
		this.contents = contents;
	}

}
