package com.example.birthdayreminder.service;


import com.example.birthdayreminder.dto.LoginRequest;
import com.example.birthdayreminder.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    void login(LoginRequest request);
}

