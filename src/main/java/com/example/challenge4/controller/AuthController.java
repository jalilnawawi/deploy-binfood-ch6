package com.example.challenge4.controller;

import com.example.challenge4.dto.auth.login.ActivateUserRequestDto;
import com.example.challenge4.dto.auth.login.JwtLoginResponse;
import com.example.challenge4.dto.auth.login.LoginRequestDto;
import com.example.challenge4.dto.auth.register.RegisterUserRequestDto;
import com.example.challenge4.dto.users.UsersDto;
import com.example.challenge4.model.accounts.ERole;
import com.example.challenge4.model.accounts.Role;
import com.example.challenge4.model.accounts.Users;
import com.example.challenge4.repository.RoleRepository;
import com.example.challenge4.repository.UsersRepository;
import com.example.challenge4.security.jwt.JwtUtils;
import com.example.challenge4.security.service.activationUser.ActivationService;
import com.example.challenge4.security.service.forgotPassword.ForgotPasswordServiceImpl;
import com.example.challenge4.security.service.register.RegisService;
import com.example.challenge4.security.service.UserDetailsImpl;
import com.example.challenge4.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RegisService regisService;

    @Autowired
    ForgotPasswordServiceImpl forgotPasswordService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivationService activationService;

    @PostMapping("/user/signin")
    public ResponseEntity<Map<String, Object>> userAuthenticate(@RequestBody LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Users user = usersRepository.findByEmailAddress(userDetails.getEmail());
        if (user != null && !user.isActive()){
            user.setActive(true);
            usersRepository.save(user);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        JwtLoginResponse jwtResponse = new JwtLoginResponse(jwt, userDetails.getUsername(), roles,
                userDetails.isEnabled());
        data.put("jwt", jwtResponse);
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("hash/password")
    public String hashingPassword(){
        System.out.println(passwordEncoder.encode("abdul123"));
        return "hashing password";
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> regis(@RequestBody RegisterUserRequestDto registerUserRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("users", regisService.registerNewUser(registerUserRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/forgot/password")
    public String forgotPassword(@RequestParam String email){
        String response = forgotPasswordService.forgotPassword(email);

        if (!response.startsWith("Invalid")){
            response = "OTP has sent to your email. ";
        }
        return response;
    }

    @PutMapping("/reset/password")
    public String resetPassword(@RequestParam String otp, @RequestParam String password){
        return forgotPasswordService.resetPassword(otp, password);
    }

    @GetMapping("/user/oauth2/success")
    public ResponseEntity<Map<String, Object>> googleLoginUserSuccess(Authentication authentication){
        //Create a new principal object with modified authorities
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = new ArrayList<>(oidcUser.getAuthorities());
        Role role = roleRepository.findByName(ERole.valueOf(ROLE_USER));
        authorities.add(new SimpleGrantedAuthority(role.getName().toString()));

        UserDetailsImpl modifiedUserDetails = UserDetailsImpl.build(oidcUser);
        OidcUser modifiedOidcUser = new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

        //Create a new Authentication object with the modified Principal
        Authentication modifiedAuthentication = new UsernamePasswordAuthenticationToken(
                modifiedOidcUser,
                oidcUser.getIdToken(),
                authorities
        );

        //Generate token using the modified authentication
        String jwt = jwtUtils.generateToken(modifiedAuthentication);

        //Extract user details from the modified authentication
        List<String> roles = modifiedAuthentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success, please check your email to activate");
        Map<String, Object> data = new HashMap<>();
        JwtLoginResponse jwtLoginResponse = new JwtLoginResponse(jwt, modifiedUserDetails.getUsername(), roles,
                !modifiedUserDetails.isEnabled());
        data.put("jwt", jwtLoginResponse);

        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("activate/user")
    public UsersDto activateUser(@RequestBody ActivateUserRequestDto activateUserRequestDto){
        return activationService.activate(activateUserRequestDto);
    }
}
