// package com.example.demo.service.impl;

// import com.example.demo.config.JwtProvider;
// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.UserRegisterDto;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.Role;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.HashSet;
// import java.util.Set;
// import java.util.stream.Collectors;

// @Service
// public class UserServiceImpl implements UserService {

//     @Autowired
//     private UserRepository userRepository;
//     @Autowired
//     private PasswordEncoder passwordEncoder;
//     @Autowired
//     private JwtProvider jwtProvider;

//     @Override
//     public User registerUser(UserRegisterDto dto) {
//         if (userRepository.existsByEmail(dto.getEmail())) {
//             throw new IllegalArgumentException("Email already exists");
//         }
        
//         User user = new User();
//         user.setName(dto.getName());
//         user.setEmail(dto.getEmail());
//         user.setPassword(passwordEncoder.encode(dto.getPassword()));
//         user.setCreatedAt(LocalDateTime.now());
        
//         Set<Role> roles = new HashSet<>();
//         if ("ADMIN".equalsIgnoreCase(dto.getRole())) {
//             roles.add(Role.ROLE_ADMIN);
//         } else {
//             roles.add(Role.ROLE_USER);
//         }
//         user.setRoles(roles);
        
//         return userRepository.save(user);
//     }
    
//     @Override
//     public AuthResponse login(AuthRequest request) {
//          User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));

//          if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//              throw new BadCredentialsException("Invalid password");
//          }
         
//          Set<String> roles = user.getRoles().stream()
//                  .map(Enum::name)
//                  .collect(Collectors.toSet());

//          String token = jwtProvider.generateToken(user.getEmail(), user.getId(), roles);
//          return new AuthResponse(token);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.config.JwtProvider;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User registerUser(UserRegisterDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        Set<Role> roles = new HashSet<>();
        if ("ADMIN".equalsIgnoreCase(dto.getRole())) {
            roles.add(Role.ROLE_ADMIN);
        } else {
            roles.add(Role.ROLE_USER);
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        // 1. Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 2. Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // 3. Generate JWT Token
        Set<String> roles = user.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        String token = jwtProvider.generateToken(user.getEmail(), user.getId(), roles);

        return new AuthResponse(token);
    }
}