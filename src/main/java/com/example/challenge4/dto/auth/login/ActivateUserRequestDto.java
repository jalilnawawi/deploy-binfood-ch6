package com.example.challenge4.dto.auth.login;
import lombok.Data;

import java.util.Set;

@Data
public class ActivateUserRequestDto {
    private String email;
    private String otp;
    private Set<String> roles;
}
