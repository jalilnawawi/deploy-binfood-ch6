package com.example.challenge4.dto.users;

import com.example.challenge4.model.accounts.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UsersCreateRequestDto {
    private String username;
    private String email;
    private String password;
}
