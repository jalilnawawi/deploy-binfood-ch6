package com.example.challenge4.service;

import com.example.challenge4.dto.merchant.MerchantCreateRequestDto;
import com.example.challenge4.dto.merchant.MerchantDeleteRequestDto;
import com.example.challenge4.dto.merchant.MerchantDto;
import com.example.challenge4.dto.merchant.MerchantUpdateStatus;
import com.example.challenge4.model.Merchant;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<Merchant> getAll();

    MerchantDto create(MerchantCreateRequestDto merchantCreateRequestDto);

    MerchantDto update(UUID id, MerchantUpdateStatus merchantUpdateStatus);
    MerchantDto delete(UUID id, MerchantDeleteRequestDto merchantDeleteRequestDto);

    MerchantDto getMerchantById(UUID merchantId);

//    List<Merchant> getOpenMerchant(MerchantGetStatusOpenRequestDto merchantGetStatusOpenRequestDto);
    List<Merchant> getOpenMerchant();
}
