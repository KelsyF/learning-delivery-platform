package com.kelsyfrank.learning.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required.")
    @JsonProperty(required = true)
    private String title;

    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users = new HashSet<>();
}
