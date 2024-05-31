package com.example.challenge4.service;

import com.example.challenge4.dto.order.OrderCreateRequestDto;
import com.example.challenge4.dto.order.OrderDto;
import com.example.challenge4.model.Order;
import com.example.challenge4.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderDto create(OrderCreateRequestDto orderCreateRequestDto) {
        Order order = new Order();
        order.setUsers(orderCreateRequestDto.getUsers());
        order.setOrderTime(LocalDate.now());
        order.setDestinationAddress(orderCreateRequestDto.getDestinationAddress());

        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order confirm(Order order) {
        order.setCompleted(true);
        return orderRepository.save(order);
    }

    @Override
    public Order getById(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.get();
    }
}
