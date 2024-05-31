package com.example.challenge4.controller;
import com.example.challenge4.dto.order.OrderCreateRequestDto;
import com.example.challenge4.model.Order;
import com.example.challenge4.service.OrderService;
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

@Component
@Slf4j

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Order>> getAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> add(@RequestBody OrderCreateRequestDto orderCreateRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("order", orderService.create(orderCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
