package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.controllers.dto.LoginRequest;
import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.entities.enums.UserRole;
import com.example.mumschedpoc.services.exceptions.InvalidEmailException;
import com.example.mumschedpoc.services.interfaces.ILoginService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(LoginController.class)
class LoginControllerTests {

    @MockBean
    private ILoginService service;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson;

    @BeforeEach
    void setupTests()
    {
        gson = new Gson();
    }

    @Test
    void login_givenAValidUsernameAndPassword_ShouldReturnOk() throws Exception {
        //Arrange
        LoginRequest request = new LoginRequest();
        User user = new User();
        user.setUserRole(UserRole.ADMIN);
        when(service.login(any(LoginRequest.class))).thenReturn(user);

        //Act
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request))
                        .accept(MediaType.APPLICATION_JSON))
        //Assert
                .andExpect(status().isOk());
    }

    @Test
    void login_givenNotExistentUsername_ShouldReturn404() throws Exception {
        //Arrange
        String expectedErrorMsg = "User not found";
        when(service.login(any(LoginRequest.class))).thenThrow(new InvalidEmailException("email"));

        //Act
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(new LoginRequest()))
                        .accept(MediaType.APPLICATION_JSON))
        //Assert
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString(expectedErrorMsg)));
    }
}
