package com.example.challenge4.security.service.activationUser;

import com.example.challenge4.dto.auth.login.ActivateUserRequestDto;
import com.example.challenge4.dto.users.UsersDto;
import com.example.challenge4.model.accounts.ERole;
import com.example.challenge4.model.accounts.Role;
import com.example.challenge4.model.accounts.Users;
import com.example.challenge4.repository.RoleRepository;
import com.example.challenge4.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ActivationServiceImpl implements ActivationService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UsersDto activate(ActivateUserRequestDto activateUserRequestDto) {
        Users user = usersRepository.findByEmailAddress(
                activateUserRequestDto.getEmail()
        );
        user.setActive(true);

        if (user.getOtp() == activateUserRequestDto.getOtp()){
            user.setOtp(activateUserRequestDto.getOtp());
        }

        Set<String> strRoles = activateUserRequestDto.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role){
                    case "merchant" :
                        Role merchantRole = roleRepository.findByName(ERole.ROLE_MERCHANT);
                        roles.add(merchantRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);

        usersRepository.save(user);
        return modelMapper.map(user, UsersDto.class);
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(1000000);
        return String.valueOf(otp);
    }
}
