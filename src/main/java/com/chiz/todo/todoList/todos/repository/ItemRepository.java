package com.chiz.todo.todoList.todos.repository;

import com.chiz.todo.todoList.todos.TodoListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<TodoListItem, Long> {

    Optional<TodoListItem> findById(long id);

}
