package com.example.challenge4.security.service.forgotPassword;

import com.example.challenge4.model.accounts.Users;
import com.example.challenge4.repository.UsersRepository;
import com.example.challenge4.security.service.activationUser.ActivationService;
import com.example.challenge4.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ActivationService activationService;

    @Autowired
    MailService mailService;

    @Override
    public String forgotPassword(String email) {
        Optional<Users> usersOptional = Optional.ofNullable(usersRepository.findByEmailAddress(email));

        if (!usersOptional.isPresent()){
            return "Invalid email";
        }


        Users user = usersOptional.get();
        user.setOtp(activationService.generateOtp());
        usersRepository.save(user);
        mailService.sendMail(
                user.getEmailAddress(),
                "Forgot Password",
                "Don't Share this OTP " + user.getOtp()
        );

        return "Check your email to get OTP";
    }

    @Override
    public String resetPassword(String otp, String password) {
        Optional<Users> usersOptional = Optional.ofNullable(usersRepository.findByOtp(otp));

        if (!usersOptional.isPresent()){
            return "Invalid OTP";
        }

        Users user = usersOptional.get();
        user.setPassword(passwordEncoder.encode(password));
        user.setOtp(null);

        usersRepository.save(user);

        return "Your password successfully updated";
    }
}
