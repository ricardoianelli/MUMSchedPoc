package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.controllers.dto.LoginRequest;
import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.services.LoginService;
import com.example.mumschedpoc.services.interfaces.ILoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTests {

    @Mock
    private ILoginService service = Mockito.mock(LoginService.class);

    @InjectMocks
    private LoginController controller;

    @Test
    void login_givenAValidUsernameAndPassword_ShouldReturnOk() {
        LoginRequest request = new LoginRequest();
        when(service.login(request)).thenReturn(new User());

        ResponseEntity<User> response = controller.login(request);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
