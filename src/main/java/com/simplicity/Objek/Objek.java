package com.simplicity.Objek;

public abstract class Objek{
    private String nama;
    private int harga;
    public Objek(String nama, int harga){
        this.nama = nama;
        this.harga = harga;
    }   
    public String getNama(){
        return nama;
    }
    public int getHarga(){
        return harga;
    }
}