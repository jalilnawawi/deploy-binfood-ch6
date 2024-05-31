package com.example.challenge4.controller;

import com.example.challenge4.dto.orderDetail.OrderDetailDto;
import com.example.challenge4.dto.orderDetail.OrderDetailRequestDto;
import com.example.challenge4.dto.orderDetail.OrderDetailResponseDto;
import com.example.challenge4.model.Order;
import com.example.challenge4.model.OrderDetail;
import com.example.challenge4.model.Product;
import com.example.challenge4.service.*;
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
@RequestMapping("orderDetail")
public class OrderDetailController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderDetailFacade orderDetailFacade;


    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<OrderDetail>> getAll(){
        return new ResponseEntity<>(orderDetailService.getAll(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<OrderDetail>> add(@PathVariable("id") UUID userId){
        return new ResponseEntity<>(orderDetailService.getOrderDetailByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("place")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> add(@RequestBody OrderDetailRequestDto orderDetailRequestDto){
        Order order = orderService.getById(orderDetailRequestDto.getOrderId());
        Product product = productService.getById(orderDetailRequestDto.getProductId());
        double totalPrice = orderDetailRequestDto.getQuantity() * product.getPrice();

        OrderDetailDto orderDetailDto = orderDetailFacade
                .placeOrderDetail(order,product, orderDetailRequestDto.getQuantity(), totalPrice);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        OrderDetailResponseDto orderDetailResponseDto = new OrderDetailResponseDto();
        orderDetailResponseDto.setOrderDetailId(orderDetailDto.getId());
        data.put("orderDetail", orderDetailResponseDto);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
