package com.example.challenge4.service;

import com.example.challenge4.dto.orderDetail.OrderDetailDto;
import com.example.challenge4.model.Order;
import com.example.challenge4.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailFacade {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailService orderDetailService;

    public OrderDetailDto placeOrderDetail(Order order, Product product, int quantity, double totalPrice){
        boolean productNotAvailable = productService.getAll().isEmpty();
        if (productNotAvailable) {
            throw new RuntimeException("Produk tidak tersedia");
        } else {
            order = orderService.confirm(order);
            OrderDetailDto orderDetailDto = orderDetailService.create(order, product, quantity, totalPrice);
            return orderDetailDto;
        }
    }
}
