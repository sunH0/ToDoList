package org.prgms.todolist.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "todo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "content", nullable = false)
	private String content;
	@Enumerated(EnumType.STRING)
	private TodoStatus status;

	public void updateContent(String content) {
		this.content = content;
	}

	public void updateStatus(TodoStatus status) {
		this.status = status;
	}
}
