package com.example.challenge4.repository;

import com.example.challenge4.model.Merchant;
import com.example.challenge4.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
