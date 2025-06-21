package com.kelsyfrank.learning.dto;

import com.kelsyfrank.learning.model.User.Role;

public record UserDTO(Long id, String fullName, String email, Role role) {}
