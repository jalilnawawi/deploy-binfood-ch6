package com.example.challenge4.security.service.register;

import com.example.challenge4.dto.auth.register.RegisterUserRequestDto;
import com.example.challenge4.dto.users.UsersDto;

public interface RegisService {
    UsersDto registerNewUser(RegisterUserRequestDto registerUserRequestDto);
}
