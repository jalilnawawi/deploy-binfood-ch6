package com.example.challenge4.dto.orderDetail;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailRequestDto {
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private double totalPrice;
}
