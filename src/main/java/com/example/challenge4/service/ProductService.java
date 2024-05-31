package com.example.challenge4.service;

import com.example.challenge4.dto.product.ProductCreateRequestDto;
import com.example.challenge4.dto.product.ProductDeleteRequestDto;
import com.example.challenge4.dto.product.ProductDto;
import com.example.challenge4.dto.product.ProductUpdatePriceRequestDto;
import com.example.challenge4.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAll();

    ProductDto create(ProductCreateRequestDto productCreateRequestDto);

    ProductDto update(UUID productId, ProductUpdatePriceRequestDto productUpdatePriceRequestDto);

    ProductDto delete(UUID productId, ProductDeleteRequestDto productDeleteRequestDto);

    Product getById(UUID productId);

    List<Product> getProductByMerchantId(UUID merchantId);
}
