package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getByEmail(String email); // The missing method causing the error
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}