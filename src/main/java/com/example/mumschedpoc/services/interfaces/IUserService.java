package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.dto.UpdateUserRequest;
import com.example.mumschedpoc.dto.UserCreationRequest;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(Integer id);

    User insert(UserCreationRequest userRequest);

    void delete(Integer id);

    User update(Integer id, UpdateUserRequest updateUserRequest);

    User findByEmail(String email);

}
