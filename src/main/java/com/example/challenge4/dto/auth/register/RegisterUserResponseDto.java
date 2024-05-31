package com.example.challenge4.dto.auth.register;

import lombok.Data;

import java.util.List;

@Data
public class RegisterUserResponseDto {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;

    public RegisterUserResponseDto(String token, String type, String username, List<String> roles) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
    }
}
