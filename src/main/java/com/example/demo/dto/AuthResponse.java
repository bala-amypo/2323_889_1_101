// package com.example.demo.dto;

// import java.util.Set;

// public class AuthResponse {

//     private String token;
//     private Long userId;          // ✅ Long
//     private String email;
//     private Set<String> roles;

//     public AuthResponse() {
//     }

//     public AuthResponse(String token, Long userId, String email, Set<String> roles) {
//         this.token = token;
//         this.userId = userId;
//         this.email = email;
//         this.roles = roles;
//     }

//     public String getToken() {
//         return token;
//     }

//     public Long getUserId() {     // ✅ Long
//         return userId;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public Set<String> getRoles() {
//         return roles;
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        // Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Fetch user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found")
                );

        // Generate JWT
        String token = jwtService.generateToken(user);

        // ✅ FINAL FIX: Long → String
        return new AuthResponse(
                token,
                String.valueOf(user.getId()),  // 🔥 THIS FIXES YOUR ERROR
                user.getEmail(),
                user.getRoles()
        );
    }
}
