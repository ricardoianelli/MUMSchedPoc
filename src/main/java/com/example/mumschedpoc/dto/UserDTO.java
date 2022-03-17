package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.User;

public class UserDTO {
    public Integer id;
    public String name;
    public String email;
    public int userRoleId;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userRoleId = user.getUserRole().getCode();
    }
}
