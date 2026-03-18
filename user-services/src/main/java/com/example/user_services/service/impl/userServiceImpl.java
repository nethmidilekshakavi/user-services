package com.example.user_services.service.impl;

import com.example.user_services.dto.LoginRequestDto;
import com.example.user_services.dto.userDto;
import com.example.user_services.modules.User;
import com.example.user_services.repository.UserRepository;
import com.example.user_services.service.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean saveUser(userDto userDto) {
        userDto.setRole("user");
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean updateUser(Integer id, userDto dto) {
        User existing = userRepo.findById(Long.valueOf(id)).orElse(null);
        if (existing == null) return false;
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setRole("user");
        if (dto.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userRepo.save(existing);
        return true;
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (!userRepo.existsById(Long.valueOf(id))) return false;
        userRepo.deleteById(Long.valueOf(id));
        return true;
    }

    @Override
    public boolean editRole(Integer id, String role) {
        User existing = userRepo.findById(Long.valueOf(id)).orElse(null);
        if (existing == null) return false;
        existing.setRole(role);
        userRepo.save(existing);
        return true;
    }

    @Override
    public LoginRequestDto login(LoginRequestDto request) {
        User user = userRepo.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        boolean isMatch = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!isMatch) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginRequestDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
        );
    }

    }


