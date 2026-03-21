package com.example.user_services.service;

import com.example.user_services.dto.LoginRequestDto;
import com.example.user_services.dto.userDto;
import com.example.user_services.modules.User;

import java.util.List;

public interface userService {

    boolean saveUser(userDto userDto);
    List<User> getAllUsers();
    boolean updateUser(Integer id, userDto dto);
    boolean deleteUser(Integer id);
    boolean editRole(Integer id, String role);
    LoginRequestDto login(LoginRequestDto request);
    User getUserById(Integer id);

}