package com.example.mumschedpoc.dto;

public class UserCredentialsDTO {
    public String email;
    public String password;

    public UserCredentialsDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
