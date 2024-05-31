package com.example.challenge4.security.service.activationUser;

import com.example.challenge4.dto.auth.login.ActivateUserRequestDto;
import com.example.challenge4.dto.users.UsersDto;

public interface ActivationService {
    UsersDto activate(ActivateUserRequestDto activateUserRequestDto);
    String generateOtp();
}
