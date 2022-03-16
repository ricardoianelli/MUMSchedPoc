package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.dto.UpdateUserRequest;
import com.example.mumschedpoc.dto.UserCreationRequest;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(Long id);

    User insert(UserCreationRequest userRequest);

    void delete(Long id);

    User update(Long id, UpdateUserRequest updateUserRequest);

    User findByEmail(String email);

}
