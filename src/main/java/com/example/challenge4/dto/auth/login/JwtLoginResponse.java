package com.example.challenge4.dto.auth.login;

import lombok.Data;

import java.util.List;

@Data
public class JwtLoginResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private boolean isActive = Boolean.TRUE;

    public JwtLoginResponse(String token, String username, List<String> roles, boolean active) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.isActive = active;
    }
}
