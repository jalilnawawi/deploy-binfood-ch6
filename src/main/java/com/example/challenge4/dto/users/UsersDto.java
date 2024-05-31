package com.example.challenge4.dto.users;

import com.example.challenge4.model.accounts.ERole;
import com.example.challenge4.model.accounts.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UsersDto {
    private UUID id;
    private String username;
    private String emailAddress;
    private String password;
    private boolean isActive = Boolean.FALSE;
    private String otp;
    private boolean deleted;
    private Set<Role> roles;
}
