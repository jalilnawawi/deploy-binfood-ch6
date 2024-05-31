package com.example.challenge4.repository;

import com.example.challenge4.model.accounts.ERole;
import com.example.challenge4.model.accounts.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(ERole name);
}
