package com.kelsyfrank.learning.controller;

import com.kelsyfrank.learning.dto.UserDTO;
import com.kelsyfrank.learning.model.User;
import com.kelsyfrank.learning.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow();
        
        return new UserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }
    
}
