package com.example.challenge4.service;

import com.example.challenge4.dto.orderDetail.OrderDetailDto;
import com.example.challenge4.model.Order;
import com.example.challenge4.model.OrderDetail;
import com.example.challenge4.model.Product;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {
    List<OrderDetail> getAll();
    OrderDetail getById(UUID id);

    OrderDetailDto create(Order order, Product product, int quantity, double totalPrice);

    List<OrderDetail> getOrderDetailByUserId(UUID userId);
}
