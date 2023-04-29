import Objek.Objek;
import Objek.ObjekNonMakanan.*;
import java.util.ArrayList;

public class Ruangan {

    // atribut
    private static final int width = 6;
    private static final int length = 6;

    private String nama;
    private Objek[][] objectTile;

    private ArrayList<Objek> daftarObjek;

    private Ruangan ruanganAtas;
    private Ruangan ruanganBawah;
    private Ruangan ruanganKanan;
    private Ruangan ruanganKiri;

    // constructor
    public Ruangan(String nama, Ruangan atas, Ruangan bawah, Ruangan kanan, Ruangan kiri) {
        this.nama = nama;
        objectTile = new Objek[width][length];

        ruanganAtas = atas;
        ruanganBawah = bawah;
        ruanganKanan = kanan;
        ruanganKiri = kiri;
    }

    // getter nama ruangan
    public String getNamaRuangan() {
        return nama;
    }

    //getter daftar Objek
    public ArrayList<Objek> getDaftarObjek() {
        return daftarObjek;
    }

    // method nambah objek, x & y merupakan koordinat paling kiri atas
    public void tambahObjek(ObjekNonMakanan obj, int x, int y) {

        // status untuk mengecek apakah ada objek di tempat yang sama
        boolean status = true;

        // idx untuk mengecek apakah masih di dalam koordinat ruangan yang valid
        boolean idx_bound = true;

        // koordinat dikurang 1 soalnya koordinat matriks dari 0
        // kalau status false, artinya sudah ada objek lain yang menempati
        for (int i = x-1; i < obj.getPanjang()+x-1; i++) {
            for (int j = y-1; j < obj.getLebar()+y-1; j++) {
                if (i > 5 || j > 5) {
                    status = false;
                    idx_bound = false;
                } else {
                    if (objectTile[i][j] != null) {
                        status = false;
                    }
                }
            }
        }

        // status false = ada objek lain di tempat yang seharusnya ditaro
        if (!status) {
            System.out.println("Objek tidak dapat ditambahkan! Posisi sudah diisi oleh objek lain!");
            if (!idx_bound) {
                System.out.println("Koordinat tidak valid atau ruangan tidak cukup!");
            }
        } else {
            for (int i = x-1; i < obj.getPanjang()+x-1; i++) {
                for (int j = y-1; j < obj.getLebar()+y-1; j++) {
                    objectTile[i][j] = obj;
                }
            }
            daftarObjek.add(obj);
        }
    }

    // method buat remove Objek di ruangan.
    public void buangObjek(ObjekNonMakanan obj) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                objectTile[i][j] = null;
            }
        }
        daftarObjek.remove(obj);
    }

    // method buat mindahin objek di dalam ruangan.
    public void pindahObjek(ObjekNonMakanan obj, int x, int y) {
        // buang dulu sementara
        buangObjek(obj);    
        // mindahin
        tambahObjek(obj, x, y);
    }

    // method buat ngedisplay denah ruangan
    public void display() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                Objek obj = objectTile[x][y];
                if (obj == null) {
                    System.out.print("- \t");
                } else {
                    System.out.print(getFirstLetterOfWords(obj.getNama()) + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // getter sisi ruangan
    public Ruangan getRuanganAtas() {
        return this.ruanganAtas;
    }
    public Ruangan getRuanganBawah() {
        return this.ruanganBawah;
    }
    public Ruangan getRuanganKiri() {
        return this.ruanganKanan;
    }
    public Ruangan getRuanganKanan() {
        return this.ruanganKiri;
    }

    // setter sisi ruangan
    public void setRuanganAtas(Ruangan atas) {
        ruanganAtas = atas;
    }
    public void setRuanganBawah(Ruangan bawah) {
        ruanganBawah = bawah;
    }
    public void setRuanganKanan(Ruangan kanan) {
        ruanganKanan = kanan;
    }
    public void setRuanganKiri(Ruangan kiri) {
        ruanganKiri = kiri;
    }
    public void setPosisiRuangan(Ruangan atas, Ruangan bawah, Ruangan kanan, Ruangan kiri) {
        ruanganAtas = atas;
        ruanganBawah = bawah;
        ruanganKanan = kanan;
        ruanganKiri = kiri;
    }

    // buat bantu display ruangan
    public String getFirstLetterOfWords(String words) {
        String[] wordsArray = words.split(" ");
        String firstLetters = "";
        for (String word : wordsArray) {
            firstLetters += word.charAt(0);
        }
        return firstLetters;
    }


}
