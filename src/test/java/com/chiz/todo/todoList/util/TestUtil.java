package com.chiz.todo.todoList.util;

import com.chiz.todo.todoList.appuser.AppUser;
import com.chiz.todo.todoList.appuser.AppUserRole;
import com.chiz.todo.todoList.model.RegistrationRequest;
import com.chiz.todo.todoList.response.MessageResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TestUtil {

    public static RegistrationRequest getRegistrationRequest(){
        return new RegistrationRequest(
                "test",
                "test",
                "test@example.com",
                "password");
    }

    public static MessageResponse getRegistrationRequestResponse(){
        return new MessageResponse("User registered successfully");
    }

    public static Authentication getAuth(){
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public Object getCredentials() {
                return "String";
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "test@test.com";
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "";
            }
        };
    }

    public static AppUser getAppUser(){
        return new AppUser("Tester",
                "Tests",
                "test@test.com",
                "password",
                AppUserRole.USER);
    }
}
