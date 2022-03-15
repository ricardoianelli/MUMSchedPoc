package com.example.mumschedpoc.controllers;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.controllers.dto.UpdateUserRequest;
import com.example.mumschedpoc.controllers.dto.UserCreationRequest;
import com.example.mumschedpoc.services.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/users")
@Tag(name="User")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary="List users")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary="Get User", responses = {
            @ApiResponse(description = "User found", responseCode = "200",
            content = @Content(schema = @Schema(implementation = User.class))),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Operation(summary="Add User", responses = {
            @ApiResponse(description = "User created", responseCode = "201",
                    content = @Content),
    })
    public ResponseEntity<User> insert(@RequestBody UserCreationRequest userRequest) {
        User responseUser = service.insert(userRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(responseUser.getId()).toUri();

        return ResponseEntity.created(uri).body(responseUser);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete User", responses = {
            @ApiResponse(description = "User deleted", responseCode = "200",
                    content = @Content),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary="Update User", responses = {
            @ApiResponse(description = "User updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),

            @ApiResponse(description = "User not found", responseCode = "404",
                    content = @Content)
    })
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        User user = service.update(id, updateUserRequest);
        return ResponseEntity.ok(user);
    }

}
