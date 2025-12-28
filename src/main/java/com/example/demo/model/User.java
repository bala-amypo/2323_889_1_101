// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;
// import java.util.Set;

// @Entity
// @Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String name;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(nullable = false)
//     private String password;

//     @ElementCollection(fetch = FetchType.EAGER)
//     @Enumerated(EnumType.STRING)
//     @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//     private Set<Role> roles;

//     private LocalDateTime createdAt;
// }
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private LocalDateTime createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}