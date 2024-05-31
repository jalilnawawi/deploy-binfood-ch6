package com.example.challenge4.dto.orderDetail;

import com.example.challenge4.model.Order;
import com.example.challenge4.model.Product;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailDto {
    private UUID id;
    private Order order;
    private Product product;
    private int quantity;
    private Double totalPrice;
    private boolean deleted;
}
