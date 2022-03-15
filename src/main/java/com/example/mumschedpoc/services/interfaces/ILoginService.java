package com.example.mumschedpoc.services.interfaces;

import com.example.mumschedpoc.entities.User;
import com.example.mumschedpoc.controllers.dto.LoginRequest;

public interface ILoginService {
    User login(LoginRequest loginRequest);

    boolean comparePassword(User user, String password);

}
