package Objek;

public abstract class Objek{
    private String nama;
    public Objek(String nama){
        this.nama = nama;
    }   
    public String getNama(){
        return nama;
    }
}