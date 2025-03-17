package com.chiz.todo.todoList.todos;

import com.chiz.todo.todoList.model.ItemRequest;
import com.chiz.todo.todoList.response.MessageResponse;
import com.chiz.todo.todoList.response.TodoListItemResponse;
import com.chiz.todo.todoList.response.TodoListResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/todos")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService service;

    @PostMapping
    public ResponseEntity<TodoListItemResponse> addItem(@RequestBody ItemRequest itemRequest) {
        var itemResponse = service.addItem(itemRequest);
        var resp = new TodoListItemResponse(itemResponse.getId(),itemResponse.getTitle(),itemResponse.getDescription());

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoListItemResponse> getItem(@PathVariable long id){
        return ResponseEntity.ok(service.getItem(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoListItemResponse> updateItem(@PathVariable long id, @RequestBody ItemRequest itemRequest){
        return ResponseEntity.ok(service.updateItem(id,itemRequest));

    }

    @GetMapping("/search")
    public ResponseEntity<TodoListResponse> getTodoList(@RequestParam(required = false) Integer limit,
                                                        @RequestParam(required = false) Integer page){
        return ResponseEntity.ok(service.getTodoList(limit,page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTodoItem(@PathVariable long id){
        return ResponseEntity.ok(new MessageResponse(service.deleteTodoItem(id)));
    }
}
