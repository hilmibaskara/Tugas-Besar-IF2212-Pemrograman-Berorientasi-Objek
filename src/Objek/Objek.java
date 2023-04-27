package Objek;

public abstract class Objek{
    private String nama;
    private String tipe;
    public Objek(String tipe , String nama){
        this.tipe = tipe;
        this.nama = nama;
    }
    public String getTipe(){
        return tipe;
    }

    public String getNama(){
        return nama;
    }
}