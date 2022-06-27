package org.prgms.todolist.domain.repository;

import java.util.List;
import org.prgms.todolist.domain.entity.TodoStatus;
import org.prgms.todolist.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findAllByStatus(TodoStatus status);

}
