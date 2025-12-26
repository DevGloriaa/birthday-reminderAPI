package com.example.birthdayreminder.service;

import com.example.birthdayreminder.dto.LoginRequest;
import com.example.birthdayreminder.dto.RegisterRequest;
import com.example.birthdayreminder.model.User;

public interface AuthService {
    User register(RegisterRequest request);
    String login(LoginRequest request);
}
