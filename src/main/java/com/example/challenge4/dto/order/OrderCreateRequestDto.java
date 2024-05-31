package com.example.challenge4.dto.order;

import com.example.challenge4.model.accounts.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderCreateRequestDto {
    private LocalDate orderTime;

    private String destinationAddress;

    private Users users;
}
