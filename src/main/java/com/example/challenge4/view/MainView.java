package com.example.challenge4.view;

public class MainView {
    public void displayLandingPage(){
        System.out.println("==========================");
        System.out.println("Selamat Datang di BinarFud");
        System.out.println("==========================");
        System.out.println("Masuk Sebagai : \n" +
                "1. User\n" +
                "2. Merchant"
                );
        System.out.print("=> ");
    }

    public void displayUserSelection(){
        System.out.println("===================");
        System.out.println("1. Masuk ke Halaman User");
        System.out.println("2. Masuk ke Halaman Order");
        System.out.println("===================");
        System.out.println("note : jika ingin registrasi silahkan pilih opsi 1");
        System.out.print("=> ");
    }

    public void displayMerchantSelection(){
        System.out.println("============================");
        System.out.println("1. Masuk ke Halaman Merchant");
        System.out.println("2. Masuk ke Halaman Product");
        System.out.println("============================");
        System.out.print("=> ");
    }

}
