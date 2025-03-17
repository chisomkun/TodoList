package com.chiz.todo.todoList.login;

import com.chiz.todo.todoList.appuser.AppUserService;
import com.chiz.todo.todoList.security.JwtTokenService;
import com.chiz.todo.todoList.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestLoginController {

    @InjectMocks
    private TestLoginController controller;

    @Mock
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private AppUserService appUserService;
    private UsernamePasswordAuthenticationToken userPassToken;

/*    @Test
    void testLoginController() {
        when(UsernamePasswordAuthenticationToken.unauthenticated("test@test.com","password")).thenReturn(TestUtil.getAuth());

    }*/
}
