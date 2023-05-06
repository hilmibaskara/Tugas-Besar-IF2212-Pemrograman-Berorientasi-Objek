package com.simplicity.Objek;
import java.util.Objects;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Objek)) {
            return false;
        }
        Objek other = (Objek) obj;
        return this.nama.equals(other.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nama);
    }
}