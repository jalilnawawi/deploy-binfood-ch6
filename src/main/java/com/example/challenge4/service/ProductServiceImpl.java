package com.example.challenge4.service;

import com.example.challenge4.dto.product.ProductCreateRequestDto;
import com.example.challenge4.dto.product.ProductDeleteRequestDto;
import com.example.challenge4.dto.product.ProductDto;
import com.example.challenge4.dto.product.ProductUpdatePriceRequestDto;
import com.example.challenge4.model.Product;
import com.example.challenge4.repository.MerchantRepository;
import com.example.challenge4.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto create(ProductCreateRequestDto productCreateRequestDto) {
        Product product = new Product();
        product.setName(productCreateRequestDto.getName());
        product.setPrice(productCreateRequestDto.getPrice());
        product.setMerchant(productCreateRequestDto.getMerchant());
        productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(UUID productId, ProductUpdatePriceRequestDto productUpdatePriceRequestDto) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Produk dengan Id " + productId + " tidak ditemukan")
        );
        product.setPrice(productUpdatePriceRequestDto.getPrice());
        productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto delete(UUID productId, ProductDeleteRequestDto productDeleteRequestDto) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Produk dengan Id " + productId + " tidak ditemukan")
        );
        product.setDeleted(productDeleteRequestDto.isDeleted());
        productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product getById(UUID productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.get();
    }

    @Override
    public List<Product> getProductByMerchantId(UUID merchantId) {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(product -> product.getMerchant().getId().equals(merchantId))
                .toList();
    }
}
