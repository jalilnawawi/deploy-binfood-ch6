package com.example.challenge4.view;

public class UsersView {
    public void displayInputUsername(){
        System.out.print("username : ");
    }
    public void displayInputEmail(){
        System.out.print("email : ");
    }
    public void displayInputPassword(){
        System.out.print("password : ");
    }


    public void userServiceDisplay(){
        System.out.println("=====================");
        System.out.println("Halaman User");
        System.out.println("=====================");
        System.out.println("1. Register");
        System.out.println("2. Update User");
        System.out.println("3. Hapus User");
    }
}
