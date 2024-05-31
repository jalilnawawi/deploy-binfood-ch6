package com.example.challenge4.view;

import com.example.challenge4.controller.MerchantController;
import com.example.challenge4.model.Merchant;
import org.hibernate.annotations.View;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class MerchantView {
    public void displayInputNama(){
        System.out.print("nama : ");
    }
    public void displayInputLokasi(){
        System.out.print("lokasi : ");
    }
    public void displayInputStatus(){
        System.out.print("open : ");
    }

    public void merchantServiceDisplay(){
        System.out.println("=====================");
        System.out.println("Halaman Merchant");
        System.out.println("=====================");
        System.out.println("1. Register");
        System.out.println("2. List Merchant");
        System.out.println("3. Update Merchant");
        System.out.println("4. Hapus Merchant");
    }
}
