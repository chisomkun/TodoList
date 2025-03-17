package com.chiz.todo.todoList.registration;

import com.chiz.todo.todoList.model.RegistrationRequest;
import com.chiz.todo.todoList.response.MessageResponse;
import com.chiz.todo.todoList.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TestRegistrationController {

    @InjectMocks
    private RegistrationController controller;

    @Mock
    private RegistrationService registrationService;

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        RegistrationRequest request = TestUtil.getRegistrationRequest();
        MessageResponse response = TestUtil.getRegistrationRequestResponse();

        when(registrationService.register(any(RegistrationRequest.class))).thenReturn(response);

        ResponseEntity<MessageResponse> resp = controller.register(request);
        assertEquals("Expected Equals", HttpStatus.CREATED, resp.getStatusCode());
    }
}

