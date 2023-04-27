package Objek;

public abstract class Objek{
    private String tipe;
    public Objek(String tipe){
        this.tipe = tipe;
    }
    public String getTipe(){
        return tipe;
    }
}