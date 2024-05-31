package com.example.challenge4.service;

import com.example.challenge4.dto.orderDetail.OrderDetailReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceFacade {
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    UsersService usersService;

    public OrderDetailReportDto reportInvoice(UUID userId){
        OrderDetailReportDto orderDetailReportDto = new OrderDetailReportDto();
        orderDetailReportDto.setUsername(usersService.getUserById(userId).getUsername());
        orderDetailReportDto.setProductName(
                orderDetailService.getOrderDetailByUserId(userId).stream().findAny().get()
                        .getProduct().getName()
        );
        orderDetailReportDto.setPrice(
                String.valueOf(orderDetailService.getOrderDetailByUserId(userId).stream().findAny().get()
                        .getProduct().getPrice())
        );
        orderDetailReportDto.setQuantity(
                String.valueOf(orderDetailService.getOrderDetailByUserId(userId).stream().findAny().get()
                        .getQuantity())
        );
        orderDetailReportDto.setTotalPrice(
                orderDetailService.getOrderDetailByUserId(userId).stream().findAny().get()
                        .getTotalPrice().toString()
        );

        return orderDetailReportDto;
    }
}
