package com.example.challenge4.dto.merchant;

import com.example.challenge4.model.Merchant;
import com.example.challenge4.model.Order;
import com.example.challenge4.model.OrderDetail;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MerchantReportDto {
    Merchant merchant;
    LocalDate orderDate;
    OrderDetail orderDetail;
}
