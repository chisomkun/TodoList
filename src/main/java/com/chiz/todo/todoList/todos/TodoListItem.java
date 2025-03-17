package com.chiz.todo.todoList.todos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TodoListItem {

    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;
    private String username;
    private String title;
    private String description;

    public TodoListItem(String username, String title, String description){
        this.username = username;
        this.title = title;
        this.description = description;

    }

    public TodoListItem() {

    }
}
