package com.example.user_services.controller;

import com.example.user_services.dto.userDto;
import com.example.user_services.modules.User;
import com.example.user_services.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private userService service;


    @PostMapping
    public boolean createUser(@RequestBody userDto dto) {
        return service.saveUser(dto);
    }


    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }


    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable Integer id, @RequestBody userDto dto) {
        return service.updateUser(id, dto);
    }


    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        return service.deleteUser(id);
    }

    //edit role
    @PatchMapping("/{id}/role")
    public boolean editRole(@PathVariable Integer id, @RequestParam String role) {
        return service.editRole(id, role);
    }
}