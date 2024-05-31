package com.example.challenge4.dto.product;

import com.example.challenge4.model.Merchant;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;

    private String name;
    private int price;

    private Merchant merchant;

    private boolean deleted;
}
