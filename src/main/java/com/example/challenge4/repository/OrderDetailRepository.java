package com.example.challenge4.repository;

import com.example.challenge4.controller.OrderDetailController;
import com.example.challenge4.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
