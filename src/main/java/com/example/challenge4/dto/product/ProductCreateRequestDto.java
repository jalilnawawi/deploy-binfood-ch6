package com.example.challenge4.dto.product;

import com.example.challenge4.model.Merchant;
import lombok.Data;

@Data
public class ProductCreateRequestDto {
    private String name;
    private int price;

    private Merchant merchant;
}
