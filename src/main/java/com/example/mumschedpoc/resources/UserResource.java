package com.example.mumschedpoc.resources;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.entities.enums.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Ricardo", UserRole.ADMIN, "123", "password");
        return ResponseEntity.ok(u);
    }

}
