package com.example.challenge4.security.service.register;

import com.example.challenge4.dto.auth.register.RegisterUserRequestDto;
import com.example.challenge4.dto.users.UsersDto;
import com.example.challenge4.model.accounts.ERole;
import com.example.challenge4.model.accounts.Role;
import com.example.challenge4.model.accounts.Users;
import com.example.challenge4.repository.RoleRepository;
import com.example.challenge4.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisServiceImpl implements RegisService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UsersDto registerNewUser(RegisterUserRequestDto registerUserRequestDto) {
        Users user = new Users();
        user.setUsername(registerUserRequestDto.getUsername());
        user.setEmailAddress(registerUserRequestDto.getEmail());

        String encodedPassword = passwordEncoder.encode(registerUserRequestDto.getPassword());
        user.setPassword(encodedPassword);

        Set<String> strRoles = registerUserRequestDto.getRole();
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

}
