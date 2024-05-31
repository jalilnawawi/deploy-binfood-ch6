package com.example.challenge4.service;

import com.example.challenge4.dto.order.OrderCreateRequestDto;
import com.example.challenge4.dto.order.OrderDto;
import com.example.challenge4.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAll();

    OrderDto create(OrderCreateRequestDto orderCreateRequestDto);
    Order confirm(Order order);

    Order getById(UUID orderId);
}
