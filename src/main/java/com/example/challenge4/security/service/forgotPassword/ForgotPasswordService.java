package com.example.challenge4.security.service.forgotPassword;

public interface ForgotPasswordService {
    String forgotPassword(String email);
    String resetPassword(String otp,String password);
}
