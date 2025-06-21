package com.kelsyfrank.learning.controller;

import com.kelsyfrank.learning.model.User;
import com.kelsyfrank.learning.service.UserService;
import com.kelsyfrank.learning.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers().stream()
                .map(user -> new UserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return service.getUserById(id)
                .map(user -> ResponseEntity.ok(new UserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
