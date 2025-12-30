package com.example.demo.service.impl;

import com.example.demo.config.JwtProvider;
import com.example.demo.dto.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Override
    public User register(UserRegisterDto dto) {
        if(userRepo.findByEmail(dto.getEmail()).isPresent()) throw new IllegalArgumentException("Email exists");
        
        Set<Role> roles = new HashSet<>();
        // Handle optional role from DTO
        if (dto.getRole() != null) {
             try { roles.add(Role.valueOf("ROLE_" + dto.getRole())); } catch(Exception e) { roles.add(Role.ROLE_USER); }
        } else {
             roles.add(Role.ROLE_USER);
        }

        User u = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(encoder.encode(dto.getPassword()))
            .roles(roles)
            .build();
        return userRepo.save(u);
    }

    @Override
    public AuthResponse login(AuthRequest req) {
        User u = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if(!encoder.matches(req.getPassword(), u.getPassword())) 
            throw new IllegalArgumentException("Invalid credentials");
        
        Set<String> roles = u.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
        String token = jwtProvider.generateToken(u.getEmail(), u.getId(), roles);
        return new AuthResponse(token, u.getId(), u.getEmail(), roles);
    }
}