package com.auth.authapi.controller;

import com.auth.authapi.dto.LoginRequest;
import com.auth.authapi.entity.User;
import com.auth.authapi.security.JwtService;
import com.auth.authapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request) {
        User user = userService.authenticate(
                request.getEmail(),
                request.getPassword()
        );
        return jwtService.generateToken(user.getEmail());
    }
}
