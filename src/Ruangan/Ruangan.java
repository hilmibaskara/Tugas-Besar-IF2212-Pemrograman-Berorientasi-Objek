package Ruangan;

import java.util.ArrayList;
import java.util.List;

public class Ruangan {
    private String name;
    private int panjang;
    private int lebar;
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
    public ArrayList getObjectList() : List <Objek>
    public void addObject(Objek object, loc Location) : void
    public void removeObject(object: Objek) : void
    public void moveObject(object: Objek, loc: Point): void
    public void isTileEmpty() : boolean
    public void getObjectTile(loc: Point) : string 
    public Ruangan getAboveRuangan() {
        return this.aboveRuangan;
    }
    public Ruangan getBelowRuangan() : Ruangan
    public Ruangan getLeftRuangan() : Ruangan
    public Ruangan getRightRuangan() : Ruangan
    public void setAboveRuangan() : void
    public void setBelowRuangan() : void
    public void setRightRuangan() : void
    public void setLeftRuangan() : void
    public void showRuanganMap() : void
}