package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRegisterDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return userService.login(req);
    }
}