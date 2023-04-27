package Ruangan;

import Objek.Objek;
import java.util.ArrayList;
import java.util.List;

public class Ruangan {
    private String name;
    private int panjang;
    private int lebar;

    private ArrayList<Objek> listObjek;

    private Ruangan aboveRuangan;
    private Ruangan belowRuangan;
    private Ruangan rightRuangan;
    private Ruangan leftRuangan;
    
    public Ruangan (Ruangan atas, Ruangan bawah, Ruangan kanan, Ruangan kiri) {
        panjang = 6;
        lebar = 6;

    }

    public String getRuanganName() {
        return this.name;
    }
    public ArrayList<Objek> getObjectList(){
        return this.listObjek;
    }

    public void addObject(Objek object, loc Location) {
        listObjek.add(object);
        
    }
    public void removeObject(Objek objek) {
        
    }
    public void moveObject(object: Objek, loc: Point) {

    }
    public void isTileEmpty() {
        
    }
    public void getObjectTile(loc: Point) {

    }
    public Ruangan getAboveRuangan() {
        return this.aboveRuangan;
    }
    public Ruangan getBelowRuangan() {
        return this.belowRuangan;
    }
    public Ruangan getLeftRuangan() {
        return this.leftRuangan;
    }
    public Ruangan getRightRuangan() {
        return this.rightRuangan;
    }
    public void setAboveRuangan(Ruangan atas) {
        this.aboveRuangan = atas;
    }
    public void setBelowRuangan(Ruangan bawah) {
        this.belowRuangan = bawah;
    }
    public void setRightRuangan(Ruangan kanan) {
        this.rightRuangan = kanan;
    }
    public void setLeftRuangan(Ruangan kiri) {
        this.leftRuangan = kiri;
    }
    public void showRuanganMap() {
        System.out.printf("Peta Ruangan " + this.getRuanganName());
    }
}