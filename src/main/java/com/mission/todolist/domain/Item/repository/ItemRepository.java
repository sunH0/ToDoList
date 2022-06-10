package com.mission.todolist.domain.Item.repository;

import com.mission.todolist.domain.Item.entity.Item;
import com.mission.todolist.domain.Item.entity.ItemStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findAllByStatus(ItemStatus status);

}
