package com.example.challenge4.dto.order;

import com.example.challenge4.model.accounts.Users;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;

    private LocalDate orderTime;

    private String destinationAddress;

    private Users users;

    private boolean completed;
    private boolean deleted;
}
