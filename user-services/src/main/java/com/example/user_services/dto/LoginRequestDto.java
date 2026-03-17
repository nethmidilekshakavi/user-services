package com.example.user_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginRequestDto {
    private Long id;
    private String email;
    private String password;
    private String role;

    public LoginRequestDto(Long id, String email, String role,String password) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
    }


}
