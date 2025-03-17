package com.chiz.todo.todoList.registration;

import com.chiz.todo.todoList.appuser.AppUser;
import com.chiz.todo.todoList.appuser.AppUserRole;
import com.chiz.todo.todoList.appuser.AppUserService;
import com.chiz.todo.todoList.model.RegistrationRequest;
import com.chiz.todo.todoList.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public MessageResponse register(RegistrationRequest request) {
        return appUserService.signUpUser(
                new AppUser(request.firstName(),
                        request.lastName(),
                        request.email(),
                        request.password(),
                        AppUserRole.USER)
        );
    }
}
