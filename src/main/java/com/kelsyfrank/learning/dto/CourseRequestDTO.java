package com.kelsyfrank.learning.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {
    
    @NotBlank(message = "Title is required.")
    private String title;

    private String description;
}
