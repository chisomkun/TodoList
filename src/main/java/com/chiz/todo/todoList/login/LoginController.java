package com.chiz.todo.todoList.login;

import com.chiz.todo.todoList.appuser.AppUserService;
import com.chiz.todo.todoList.model.LoginRequest;
import com.chiz.todo.todoList.response.MessageResponse;
import com.chiz.todo.todoList.response.TokenResponse;
import com.chiz.todo.todoList.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<MessageResponse> loginUser(@RequestBody LoginRequest request) {
        Authentication authReq = UsernamePasswordAuthenticationToken.unauthenticated(request.email(), request.password());

        authenticationManager.authenticate(authReq);

        var user = appUserService.loadUserByUsername(request.email());
        String token = jwtTokenService.generateToken(user);
        return ResponseEntity.ok(new MessageResponse(token));
    }
}
