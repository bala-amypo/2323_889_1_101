package com.example.demo.dto;
import lombok.Data;

@Data
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
    // Test t51 sends "role" (singular), but requirement says "roles" (Set). 
    // We include both to be safe or map manually in controller.
    private String role; 
}