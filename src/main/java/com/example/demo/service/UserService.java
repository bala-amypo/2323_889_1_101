package com.example.demo.service;
import com.example.demo.dto.*;
import com.example.demo.model.User;

public interface UserService {
    User register(UserRegisterDto dto);
    AuthResponse login(AuthRequest request);
}