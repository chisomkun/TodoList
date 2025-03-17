package com.chiz.todo.todoList.util;

import com.chiz.todo.todoList.security.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestInfo {

    private final HttpServletRequest request;
    private final JwtTokenService jwtTokenService;

    public String getUserName() {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        return jwtTokenService.extractUsername(token);
    }

}
