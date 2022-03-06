package com.example.mumschedpoc.resources.dto;

public class UserCreationRequest {
    public String name;
    public String email;
    public int userRoleId;
    public String password;

    public UserCreationRequest(String name, String email, int userRoleId, String password) {
        this.name = name;
        this.email = email;
        this.userRoleId = userRoleId;
        this.password = password;
    }
}
