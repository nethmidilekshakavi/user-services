package com.example.user_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role ;

}
