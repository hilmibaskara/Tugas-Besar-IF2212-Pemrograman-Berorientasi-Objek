import java.util.ArrayList;

public class Rumah {
    private String namaOwner;
    private Point houseLoc;
    private ArrayList<Ruangan> ruanganList;

    public Rumah(int x, int y) {
        // this.tambahRuangan("kamar");
        this.setHouseLoc(x, y);
        this.ruanganList = new ArrayList<Ruangan>();
    }    

    public void setHouseLoc(int x, int y) {
        houseLoc.setPoint(x, y);
    }

    public String getNamaOwner() {
        return this.namaOwner;
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

    public void tambahRuangan(String namaRuangan) {
        ruanganList.add(new Ruangan(namaRuangan, null, null, null, null));
    }
}