// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.UserRegisterDto;
// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     @PostMapping("/register")
//     public User register(@RequestBody UserRegisterDto dto) {
//         return userService.register(dto);
//     }

//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody AuthRequest request) {
//         return userService.login(request);
//     }
// }
// package com.example.demo.controller;

// import com.example.demo.config.JwtProvider;
// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.UserRegisterDto;
// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Set;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
//     @Autowired
//     private AuthenticationManager authenticationManager;
//     @Autowired
//     private UserService userService;
//     @Autowired
//     private JwtProvider jwtProvider;

//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
//         return ResponseEntity.ok(userService.registerUser(dto));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody AuthRequest request) {
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
//         SecurityContextHolder.getContext().setAuthentication(authentication);
        
//         // Extract details for token
//         User user = (User) ((com.example.demo.security.CustomUserDetailsService) 
//                 ((org.springframework.security.core.userdetails.UserDetails)authentication.getPrincipal()))
//                 .loadUserByUsername(request.getEmail()); 
        
//         // Note: Casting UserDetails to domain User might require tweaking UserDetailsService. 
//         // For simplicity in this demo structure, we'll re-fetch or assume UserDetails has authorities.
//         Set<String> roles = authentication.getAuthorities().stream()
//                 .map(r -> r.getAuthority())
//                 .collect(Collectors.toSet());
                
//         // In real app, we get userId properly. Here using 0L or mocked fetch.
//         String token = jwtProvider.generateToken(request.getEmail(), 0L, roles);
        
//         return ResponseEntity.ok(new AuthResponse(token));
//     }
// }


package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // FIX: Delegate the login logic (finding user, checking password, generating token)
        // to the UserService. This avoids the ClassCastException completely.
        return ResponseEntity.ok(userService.login(request));
    }
}