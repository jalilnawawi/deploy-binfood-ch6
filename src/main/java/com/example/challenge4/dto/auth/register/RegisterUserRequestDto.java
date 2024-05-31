package com.example.challenge4.dto.auth.register;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserRequestDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    private Set<String> role;

    public RegisterUserRequestDto(String username, String email, String password, Set<String> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
