package com.example.challenge4.service;

import com.example.challenge4.dto.merchant.MerchantCreateRequestDto;
import com.example.challenge4.dto.merchant.MerchantDeleteRequestDto;
import com.example.challenge4.dto.merchant.MerchantDto;
import com.example.challenge4.dto.merchant.MerchantUpdateStatus;
import com.example.challenge4.model.Merchant;
import com.example.challenge4.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService{
    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public MerchantDto create(MerchantCreateRequestDto merchantCreateRequestDto) {
        Merchant merchant = new Merchant();
        merchant.setName(merchantCreateRequestDto.getName());
        merchant.setLocation(merchantCreateRequestDto.getLocation());
        merchant.setOpen(true);
        merchantRepository.save(merchant);

        return modelMapper.map(merchant, MerchantDto.class);
    }

    @Override
    public MerchantDto update(UUID id, MerchantUpdateStatus merchantUpdateStatus) {
        Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Merchant dengan ID " + id + " tidak ditemukan"));

        merchant.setOpen(merchantUpdateStatus.isOpen());

        merchantRepository.save(merchant);

        return modelMapper.map(merchant, MerchantDto.class);
    }

    @Override
    public MerchantDto delete(UUID id, MerchantDeleteRequestDto merchantDeleteRequestDto) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Merchant dengan ID " + id +" tidak ditemukan")
        );
        merchant.setDeleted(merchantDeleteRequestDto.isDeleted());
        merchantRepository.save(merchant);
        return modelMapper.map(merchant, MerchantDto.class);
    }

    @Override
    public MerchantDto getMerchantById(UUID merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() ->
                new EntityNotFoundException("Merchant dengan ID " + merchantId + " tidak ditemukan")
        );
        return modelMapper.map(merchant, MerchantDto.class);
    }

    @Override
    public List<Merchant> getOpenMerchant() {
        return merchantRepository.findOpenMerchant();
    }

//    @Override
//    public List<Merchant> getOpenMerchant(MerchantGetStatusOpenRequestDto merchantGetStatusOpenRequestDto) {
//        List<Merchant> merchantList = new ArrayList<>();
//        merchantList.forEach(merchant ->
//                merchantGetStatusOpenRequestDto.setOpen(merchantGetStatusOpenRequestDto.isOpen())
//        );
//        return merchantList;
//    }


}
