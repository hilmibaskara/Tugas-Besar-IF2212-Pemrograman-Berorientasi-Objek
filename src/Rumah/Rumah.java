package Rumah;

import java.util.ArrayList;
import Ruangan.Ruangan;

public class Rumah {
    private String name;
    private Point houseLoc;
    private ArrayList<Ruangan> ruanganList;

    public Rumah(int x, int y) {
        this.addRuangan();
        this.setHouseLoc(x, y);
        this.ruanganList = new ArrayList<Ruangan>();
    }    

    public void setHouseLoc(int x, int y) {
        houseLoc.setPoint(x, y);
    }

    public String getRumahName() {
        return this.name;
    }
    public Point getRumahLoc() {
        return this.houseLoc;
    } 
    public int getHouseLocX() {
        return this.houseLoc.getX();
    }
    public int getHouseLocY() {
        return this.houseLoc.getY();
    }

    public void addRuangan() {

    } 
}
