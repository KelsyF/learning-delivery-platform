package com.kelsyfrank.learning.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required.")
    private String fullName;

    @Email(message = "Must be a valid email.")
    @NotBlank(message = "Email is required.")
    @Column(unique = true)
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        STUDENT,
        ADMIN
    }
}