package com.example.mumschedpoc.resources.dto;

public class UpdateUserRequest {
    public String name;
    public String email;
    public int userRoleId;

    public UpdateUserRequest(String name, String email, int userRoleId) {
        this.name = name;
        this.email = email;
        this.userRoleId = userRoleId;
    }
}
