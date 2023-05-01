import java.util.ArrayList;

public class Rumah {
    private String namaOwner;
    private Point houseLoc;
    // private HashMap<Point, Ruangan> ruanganLoc;
    private ArrayList<Ruangan> ruanganList;
    // private Ruangan[][] ruanganTile;

    public Rumah(int x, int y, String namaOwner) {
        // this.tambahRuangan("kamar");
        houseLoc = new Point(x,y);

        this.ruanganList = new ArrayList<Ruangan>();
        this.namaOwner = namaOwner;

        // ruangan kamar sebagai ruangan pertama
        Ruangan kamar = new Ruangan("Kamar");
        ruanganList.add(kamar);
    }    

    public Ruangan getKamar() {
        return ruanganList.get(0);
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

    public ArrayList<Ruangan> getDaftarRuangan() {
        return this.ruanganList;
    }

    // variabel ruangBarunya bikin dulu
    // kalo ngaco kabarin yey
    public void pasangRuanganBaru(Ruangan ruangBaru, Ruangan ruangAcuan, String arahMataPosisi) {
        if (arahMataPosisi.equals("atas")) {
            ruangBaru.setRuanganBawah(ruangAcuan);
            ruangAcuan.setRuanganAtas(ruangBaru);

            ruangBaru.setRuanganLoc(ruangAcuan.getRuanganLocX(), ruangAcuan.getRuanganLocY() + 1);

            cekRuanganAtas(ruangBaru);
            cekRuanganKanan(ruangBaru);
            cekRuanganKiri(ruangBaru);

        } else if (arahMataPosisi.equals("bawah")) {
            ruangBaru.setRuanganAtas(ruangAcuan);
            ruangAcuan.setRuanganBawah(ruangBaru);

            ruangBaru.setRuanganLoc(ruangAcuan.getRuanganLocX(), ruangAcuan.getRuanganLocY() - 1);

            cekRuanganBawah(ruangBaru);
            cekRuanganKanan(ruangBaru);
            cekRuanganKiri(ruangBaru);

        } else if (arahMataPosisi.equals("kanan")) {
            ruangBaru.setRuanganKiri(ruangAcuan);
            ruangAcuan.setRuanganAtas(ruangBaru);

            ruangBaru.setRuanganLoc(ruangAcuan.getRuanganLocX() + 1, ruangAcuan.getRuanganLocY());

            cekRuanganKanan(ruangBaru);
            cekRuanganAtas(ruangBaru);
            cekRuanganBawah(ruangBaru);

        } else if (arahMataPosisi.equals("kiri")) {
            ruangBaru.setRuanganKanan(ruangAcuan);
            ruangAcuan.setRuanganKiri(ruangBaru);

            ruangBaru.setRuanganLoc(ruangAcuan.getRuanganLocX() - 1, ruangAcuan.getRuanganLocY());

            cekRuanganKiri(ruangBaru);
            cekRuanganAtas(ruangBaru);
            cekRuanganBawah(ruangBaru);;
        }

        ruanganList.add(ruangBaru);
    }

    public void cekRuanganAtas(Ruangan ruangBaru) {
        for (Ruangan i: ruanganList) {
            if (i.getRuanganLocX() == ruangBaru.getRuanganLocX() && i.getRuanganLocY() == ruangBaru.getRuanganLocY() + 1) {
                ruangBaru.setRuanganAtas(i);
            }

        }
    }

    public void cekRuanganBawah(Ruangan ruangBaru) {
        for (Ruangan i: ruanganList) {
            if (i.getRuanganLocX() == ruangBaru.getRuanganLocX() && i.getRuanganLocY() == ruangBaru.getRuanganLocY() - 1) {
                ruangBaru.setRuanganBawah(i);
            }
        }
    }

    public void cekRuanganKanan(Ruangan ruangBaru) {
        for (Ruangan i: ruanganList) {
            if (i.getRuanganLocX() == ruangBaru.getRuanganLocX() + 1 && i.getRuanganLocY() == ruangBaru.getRuanganLocY()) {
                ruangBaru.setRuanganKanan(i);
            }
        }
    }

    public void cekRuanganKiri(Ruangan ruangBaru) {
        for (Ruangan i: ruanganList) {
            if  (i.getRuanganLocX() == ruangBaru.getRuanganLocX() - 1 && i.getRuanganLocY() == ruangBaru.getRuanganLocY()) {
                ruangBaru.setRuanganKiri(i);
            }
        }
    }

    public void displayRumah() {
        int a = 1;
        for (Ruangan i: ruanganList) {          
            System.out.printf(a +". "+ i.getNamaRuangan());
            System.out.println("(" + i.getRuanganLocX() + "," + i.getRuanganLocY() + ")");
            a++;
        }
    }
}
