package com.example.user_services.service;

import com.example.user_services.dto.userDto;
import com.example.user_services.modules.User;
import com.example.user_services.repository.UserRepository;
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
        existing.setRole(dto.getRole());
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
}