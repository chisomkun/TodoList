package com.chiz.todo.todoList.registration;

import com.chiz.todo.todoList.appuser.AppUser;
import com.chiz.todo.todoList.appuser.AppUserService;
import com.chiz.todo.todoList.model.RegistrationRequest;
import com.chiz.todo.todoList.response.MessageResponse;
import com.chiz.todo.todoList.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestRegistrationService {

    @InjectMocks
    private RegistrationService service;

    @Mock
    private AppUserService appUserService;

    @Test
    void testRegister(){
        RegistrationRequest request = TestUtil.getRegistrationRequest();
        MessageResponse response = TestUtil.getRegistrationRequestResponse();

        when(appUserService.signUpUser(any(AppUser.class))).thenReturn(response);

        MessageResponse resp = service.register(request);

        assertEquals("Should equal","User registered successfully",resp.response());

    }
}
