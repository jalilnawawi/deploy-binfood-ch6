package com.example.challenge4.repository;

import com.example.challenge4.model.accounts.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);
    Users findByEmailAddress(String email);
    Users findByOtp(String otp);
}
