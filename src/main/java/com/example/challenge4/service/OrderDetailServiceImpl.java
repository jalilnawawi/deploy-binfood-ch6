package com.example.challenge4.service;

import com.example.challenge4.dto.orderDetail.OrderDetailDto;
import com.example.challenge4.model.Order;
import com.example.challenge4.model.OrderDetail;
import com.example.challenge4.model.Product;
import com.example.challenge4.repository.OrderDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getById(UUID id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);
        return orderDetailOptional.get();
    }

    @Override
    public OrderDetailDto create(Order order, Product product, int quantity, double totalPrice) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);
        orderDetail.setTotalPrice(totalPrice);
        orderDetailRepository.save(orderDetail);

        return modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public List<OrderDetail> getOrderDetailByUserId(UUID userId) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        return orderDetailList.stream().filter(orderDetail ->
                orderDetail.getOrder().getUsers().getId().equals(userId)
                ).toList();
    }

}
