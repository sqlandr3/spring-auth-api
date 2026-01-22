package com.auth.authapi.controller;

import com.auth.authapi.dto.CreateUserRequest;
import com.auth.authapi.entity.User;
import com.auth.authapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(
                request.getEmail(),
                request.getPassword()
        );
    }
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
