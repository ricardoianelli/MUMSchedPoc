package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.dto.NewUserDTO;
import com.example.mumschedpoc.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();

    UserDTO findById(Integer id);

    UserDTO insert(NewUserDTO userRequest);

    void delete(Integer id);

    UserDTO update(Integer id, UserDTO updateUserRequest);

    UserDTO findByEmail(String email);

}
