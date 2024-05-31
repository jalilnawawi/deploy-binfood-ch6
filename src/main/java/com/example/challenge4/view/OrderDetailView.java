package com.example.challenge4.view;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailView {
    public void displaySelectProduct(){
        System.out.print("Pilih Menu => ");
    }

    public void displayInputQuantity(){
        System.out.print(
                "Berapa pesanan anda => "
        );
    }

    public void displayConfirmPayHeader() {
        System.out.println();
        System.out.println("=====================\n"
                + "Konfirmasi dan Bayar\n"
                + "=====================\n"
        );
    }

    public void displayConfirmPaySelection(){
        System.out.println(
                "1. " + "Konfirmasi dan Bayar\n"
                        + "2. " + "Kembali ke menu utama\n"
                        + "3. " + "Keluar aplikasi"
        );
        System.out.print(
                "Select option => "
        );
    }

}
