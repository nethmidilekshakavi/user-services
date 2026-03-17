package com.example.user_services.controller;

import com.example.user_services.dto.LoginRequestDto;
import com.example.user_services.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private userService userService;

    @PostMapping("/login")
    public LoginRequestDto login(@RequestBody LoginRequestDto request) {
        return userService.login(request);
    }
}