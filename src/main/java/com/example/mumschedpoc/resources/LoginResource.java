package com.example.mumschedpoc.resources;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.resources.dto.LoginRequest;
import com.example.mumschedpoc.resources.exceptions.StandardError;
import com.example.mumschedpoc.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@Tag(name="Login")
public class LoginResource {

    @Autowired
    private final LoginService service;

    public LoginResource(LoginService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary="User Login", responses = {
            @ApiResponse(description = "Login Successful", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content(schema = @Schema(implementation = StandardError.class))),

            @ApiResponse(description = "Wrong password", responseCode = "401",
                    content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User responseUser = service.login(loginRequest);

        return ResponseEntity.ok(responseUser);
    }

}
