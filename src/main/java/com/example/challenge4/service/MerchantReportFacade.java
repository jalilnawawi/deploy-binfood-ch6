package com.example.challenge4.service;

import com.example.challenge4.dto.merchant.MerchantDto;
import com.example.challenge4.dto.merchant.MerchantReportDto;
import com.example.challenge4.dto.orderDetail.OrderDetailDto;
import com.example.challenge4.model.Merchant;
import com.example.challenge4.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MerchantReportFacade {
    @Autowired
    MerchantService merchantService;

    @Autowired
    OrderDetailService orderDetailService;

    public MerchantReportDto reportMerchant(UUID merchantId, UUID orderDetailId){
        MerchantReportDto merchantReportDto = new MerchantReportDto();
        MerchantDto merchantDto = merchantService.getMerchantById(merchantId);
        OrderDetail orderDetail = orderDetailService.getById(orderDetailId);

        return merchantReportDto;
    }
}
