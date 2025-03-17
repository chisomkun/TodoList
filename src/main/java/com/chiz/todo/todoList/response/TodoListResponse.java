package com.chiz.todo.todoList.response;

import java.util.List;

public record TodoListResponse(List<TodoListItemResponse> data, int page, int limit, long total) {

}
