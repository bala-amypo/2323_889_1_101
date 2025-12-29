package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.AuthRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ... (other methods: getAllUsers, getUserById, etc.)

    @Override
    public User getByEmail(String email) {
        // FIX for Line 35: Use .orElse(null) or .orElseThrow() 
        // because findByEmail returns Optional<User>
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public User register(UserRegisterDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // In a real app, encode this!
        return userRepository.save(user);
    }

    @Override
    public String login(AuthRequest authRequest) {
        // Basic logic: check user and return a message or token
        User user = getByEmail(authRequest.getEmail());
        if(user.getPassword().equals(authRequest.getPassword())) {
            return "Login Successful";
        }
        throw new RuntimeException("Invalid credentials");
    }

    // Ensure other interface methods (updateUser, deleteUser) are also present here...
}