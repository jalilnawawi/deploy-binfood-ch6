package com.example.challenge4.service;

import com.example.challenge4.dto.users.UsersCreateRequestDto;
import com.example.challenge4.dto.users.UsersDeleteRequestDto;
import com.example.challenge4.dto.users.UsersDto;
import com.example.challenge4.dto.users.UsersUpdatePasswordRequsetDto;
import com.example.challenge4.model.accounts.ERole;
import com.example.challenge4.model.accounts.Role;
import com.example.challenge4.model.accounts.Users;
import com.example.challenge4.repository.RoleRepository;
import com.example.challenge4.repository.UsersRepository;
import com.example.challenge4.security.service.activationUser.ActivationService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    MailService mailService;

    @Autowired
    ActivationService activationService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(UUID userId) {
        Optional<Users> usersOptional = usersRepository.findById(userId);
        return usersOptional.get();
    }

    @Override
    public UsersDto create(UsersCreateRequestDto usersCreateRequestDto) {
        Users user = new Users();
        user.setUsername(usersCreateRequestDto.getUsername());
        user.setEmailAddress(usersCreateRequestDto.getEmail());
        user.setPassword(usersCreateRequestDto.getPassword());
        usersRepository.save(user);

        return modelMapper.map(user, UsersDto.class);
    }

    @Override
    public UsersDto update(UUID userId, UsersUpdatePasswordRequsetDto usersUpdatePasswordRequsetDto) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User dengan Id " + userId + "tidak ditemukan")
        );

        user.setPassword(usersUpdatePasswordRequsetDto.getPassword());
        usersRepository.save(user);
        return modelMapper.map(user, UsersDto.class);
    }

    @Override
    public UsersDto delete(UUID userId, UsersDeleteRequestDto usersDeleteRequestDto) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User dengan Id " + userId + "tidak ditemukan")
        );

        user.setDeleted(usersDeleteRequestDto.isDeleted());
        usersRepository.save(user);
        return modelMapper.map(user, UsersDto.class);
    }

    @Override
    public void createUserPostLogin(String username, String email) {
        Role role = roleRepository.findByName(ERole.ROLE_USER);
        Set<Role> roles = new HashSet<>(Collections.singleton(role));

        Users user = getByUsername(email);
        if (user == null){
            user = new Users();
            user.setUsername(username);
            user.setEmailAddress(email);
            user.setRoles(roles);
            user.setOtp(activationService.generateOtp());
            user.setActive(false);
            usersRepository.save(user);
        }
        mailService.sendMail(
                user.getEmailAddress(),
                "Activate your account",
                "Don't Share this OTP " + user.getOtp()
        );

    }

    @Override
    public void createMerchantUserPostLogin(String username, String email) {
        Role role = roleRepository.findByName(ERole.ROLE_MERCHANT);
        Set<Role> roles = new HashSet<>(Collections.singleton(role));

        Users user = getByUsername(email);
        if (user == null){
            user = new Users();
            user.setUsername(username);
            user.setEmailAddress(email);
            user.setRoles(roles);
            usersRepository.save(user);
        }
    }

    @Override
    public Users getByUsername(String username) {
        Optional<Users> user = usersRepository.findByUsername(username);
        return user.orElse(null);
    }

}
