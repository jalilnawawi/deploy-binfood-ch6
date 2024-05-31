package com.example.challenge4.controller;

import com.example.challenge4.dto.users.UsersCreateRequestDto;
import com.example.challenge4.dto.users.UsersDeleteRequestDto;
import com.example.challenge4.dto.users.UsersDto;
import com.example.challenge4.dto.users.UsersUpdatePasswordRequsetDto;
import com.example.challenge4.model.accounts.Users;
import com.example.challenge4.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAll(){
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@RequestBody UsersCreateRequestDto usersCreateRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("users", usersService.create(usersCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UsersDto add(@PathVariable("id") UUID userId, @RequestBody UsersUpdatePasswordRequsetDto usersUpdatePasswordRequsetDto){
        return usersService.update(userId, usersUpdatePasswordRequsetDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UsersDto add(@PathVariable("id") UUID userId, @RequestBody UsersDeleteRequestDto usersDeleteRequestDto){
        return usersService.delete(userId, usersDeleteRequestDto);
    }
}
