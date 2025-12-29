package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.AuthRequest;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getByEmail(String email);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);

    // FIX: Added these two methods so AuthController can find them
    User register(UserRegisterDto registrationDto);
    String login(AuthRequest authRequest); 
}