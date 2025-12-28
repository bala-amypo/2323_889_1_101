// package com.example.demo.dto;

// import lombok.Getter;
// import lombok.Setter;

// import java.util.Set;

// @Getter
// @Setter
// public class UserRegisterDto {

//     private String name;
//     private String email;
//     private String password;
//     private Set<String> roles;
// }
package com.example.demo.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
    private String role; // "USER" or "ADMIN"
}