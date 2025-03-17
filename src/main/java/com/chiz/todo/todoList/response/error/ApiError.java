package com.chiz.todo.todoList.response.error;


import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus code, String errorMessage) {


}