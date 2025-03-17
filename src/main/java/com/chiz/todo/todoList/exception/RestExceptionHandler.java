package com.chiz.todo.todoList.exception;

import com.chiz.todo.todoList.response.error.ApiError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ApiError> handleUsernameNotFound(BadCredentialsException ex){
        String error = "Invalid user/password";
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiError> itemNotFound(EntityNotFoundException ex){
        String error = "Item not found";
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<ApiError> itemNotFound(IllegalStateException ex){
        String error = ex.getMessage();
        return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED, error), HttpStatus.UNAUTHORIZED);
    }

}
