package com.example.challenge4.controller;

import com.example.challenge4.dto.merchant.MerchantCreateRequestDto;
import com.example.challenge4.dto.merchant.MerchantDeleteRequestDto;
import com.example.challenge4.dto.merchant.MerchantDto;
import com.example.challenge4.dto.merchant.MerchantUpdateStatus;
import com.example.challenge4.model.Merchant;
import com.example.challenge4.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
@Slf4j

@RestController
@RequestMapping("merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;


    @GetMapping
    public ResponseEntity<List<Merchant>> getAll(){
        return new ResponseEntity<>(merchantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("open")
    public ResponseEntity<List<Merchant>> getOpenMerchant(){
        return new ResponseEntity<>(merchantService.getOpenMerchant(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public MerchantDto add(@PathVariable("id") UUID merchantId){
        return merchantService.getMerchantById(merchantId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<Map<String, Object>> add(@RequestBody MerchantCreateRequestDto merchantCreateRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("merchant", merchantService.create(merchantCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public MerchantDto add(@PathVariable("id") UUID merchantId, @RequestBody MerchantUpdateStatus merchantUpdateStatus){
        return merchantService.update(merchantId, merchantUpdateStatus);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public MerchantDto add(@PathVariable("id") UUID merchantId
            , @RequestBody MerchantDeleteRequestDto merchantDeleteRequestDto){
        return merchantService.delete(merchantId, merchantDeleteRequestDto);
    }

}
