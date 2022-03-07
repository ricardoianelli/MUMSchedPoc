package com.example.mumschedpoc.services;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.resources.dto.LoginRequest;
import com.example.mumschedpoc.services.exceptions.InvalidEmailException;
import com.example.mumschedpoc.services.exceptions.InvalidPasswordException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public User login(LoginRequest loginRequest) {
        try {
            User user = userService.findByEmail(loginRequest.email);
            if (comparePassword(user, loginRequest.password)) {
                return user;
            }
            throw new InvalidPasswordException(loginRequest.email);
        } catch (EntityNotFoundException | JpaObjectRetrievalFailureException ex) {
            throw new InvalidEmailException(loginRequest.email);
        }
    }

    public boolean comparePassword(User user, String password) {
        return user.getPassword().compareTo(password) == 0;
    }

}
