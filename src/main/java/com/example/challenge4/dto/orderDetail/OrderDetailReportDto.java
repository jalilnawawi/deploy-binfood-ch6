package com.example.challenge4.dto.orderDetail;

import lombok.Data;

@Data
public class OrderDetailReportDto {
    String username;
    String productName;
    String price;
    String quantity;
    String totalPrice;
}
