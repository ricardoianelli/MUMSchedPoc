package com.example.mumschedpoc.dto;

import com.example.mumschedpoc.entities.User;

public class UserDTO {
    public Integer id;
    public String name;
    public String email;
    public int userRoleId;

    public UserDTO() {
    }

    //TODO: DTOs should NOT know about the entity and vice-versa, but for simplicity let's just do it for now
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userRoleId = user.getUserRole().getCode();
    }
}
