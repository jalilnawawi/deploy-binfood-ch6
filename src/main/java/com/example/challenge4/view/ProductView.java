package com.example.challenge4.view;

public class ProductView {
    public void displayInputNama(){
        System.out.print("nama : ");
    }
    public void displayInputHarga(){
        System.out.print("harga : ");
    }

    public void displayHeader(){
        System.out.println(
                "Nama Merchant" + " | "
                + "Menu" + " | "
                + "Harga"
        );
    }

    public void productServiceDisplay(){
        System.out.println("=====================");
        System.out.println("Halaman Product");
        System.out.println("=====================");
        System.out.println("1. Buat Produk");
        System.out.println("2. List Produk");
        System.out.println("3. List Produk dari Merchant");
        System.out.println("4. Update Produk");
        System.out.println("5. Hapus Produk");
    }
}
