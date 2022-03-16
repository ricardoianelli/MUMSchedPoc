package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.dto.UserDTO;
import com.example.mumschedpoc.dto.NewUserDTO;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(Integer id);

    User insert(NewUserDTO userRequest);

    void delete(Integer id);

    User update(Integer id, UserDTO updateUserRequest);

    User findByEmail(String email);

}
