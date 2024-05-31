package com.example.challenge4.controller;

import com.example.challenge4.dto.product.ProductCreateRequestDto;
import com.example.challenge4.dto.product.ProductDeleteRequestDto;
import com.example.challenge4.dto.product.ProductDto;
import com.example.challenge4.dto.product.ProductUpdatePriceRequestDto;
import com.example.challenge4.model.Product;
import com.example.challenge4.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<List<Product>> add(@PathVariable("id") UUID merchantId){
        return new ResponseEntity<>(productService.getProductByMerchantId(merchantId), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<Map<String, Object>> add(@RequestBody ProductCreateRequestDto productCreateRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("product", productService.create(productCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ProductDto add(@PathVariable("id") UUID productId, @RequestBody ProductUpdatePriceRequestDto productUpdatePriceRequestDto){
        return productService.update(productId, productUpdatePriceRequestDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ProductDto add(@PathVariable("id") UUID productId, @RequestBody ProductDeleteRequestDto productDeleteRequestDto){
        return productService.delete(productId, productDeleteRequestDto);
    }
}
