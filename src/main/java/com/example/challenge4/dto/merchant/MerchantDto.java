package com.example.challenge4.dto.merchant;

import lombok.Data;

import java.util.UUID;

@Data
public class MerchantDto {
    private UUID id;
    private String name;
    private String location;
    private boolean open;
    private boolean deleted;
}
