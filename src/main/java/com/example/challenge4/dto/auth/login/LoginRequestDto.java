package com.example.challenge4.dto.auth.login;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
    private boolean isActive = Boolean.TRUE;
}
