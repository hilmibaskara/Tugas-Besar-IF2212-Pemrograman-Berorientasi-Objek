package com.simplicity.Rumah;

import java.util.*;
import com.simplicity.Objek.*;
public class Ruangan {

    // atribut
    private static final int width = 6;
    private static final int length = 6;

    private Point ruanganLoc;
    private String nama;
    private Objek[][] objectTile;

    private ArrayList<ObjekNonMakanan> daftarObjek = new ArrayList<>();
    private Map<ObjekNonMakanan, Point> objekMap = new HashMap<>();
    private Ruangan ruanganAtas;
    private Ruangan ruanganBawah;
    private Ruangan ruanganKanan;
    private Ruangan ruanganKiri;

    // constructor
    public Ruangan(String nama) {
        this.nama = nama;
        objectTile = new Objek[width][length];

        // nilai default untuk lokasinya (0,0) & ruangan di sisi-sisinya 0 semua
        ruanganLoc = new Point(0,0);
        ruanganAtas = null;
        ruanganBawah = null;
        ruanganKanan = null;
        ruanganKiri = null;
    }

    // getter nama ruangan
    public String getNamaRuangan() {
        return nama;
    }

    public Map<ObjekNonMakanan, Point> getObjekMap(){
        return objekMap;
    }

    //getter daftar Objek
    public ArrayList<ObjekNonMakanan> getDaftarObjek() {
        return daftarObjek;
    }
    
    public void printListObjekRuangan(){
        System.out.println("daftar ruangan");
        for (int i = 0; i < daftarObjek.size(); i++) {
            System.out.println((i+1) + ". " + daftarObjek.get(i).getNama() + " (" +objekMap.get(daftarObjek.get(i)).getX()+","+objekMap.get(daftarObjek.get(i)).getY()+")");
        }
    }

    // method nambah objek, x & y merupakan koordinat paling kiri atas
    public boolean tambahObjek(ObjekNonMakanan obj, int x, int y) {

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
            return status;
        } else {
            for (int i = x-1; i < obj.getPanjang()+x-1; i++) {
                for (int j = y-1; j < obj.getLebar()+y-1; j++) {
                    objectTile[i][j] = obj;
                }
            }
            daftarObjek.add(obj);
            Point point = new Point(x,y);
            objekMap.put(obj,point);
            return status;
        }
    }

    // method buat remove Objek di ruangan.
    public void buangObjek(ObjekNonMakanan obj) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (objectTile[i][j] == obj) {
                    objectTile[i][j] = null;
                }
            }
        }
        daftarObjek.remove(obj);
        objekMap.remove(obj);
    }

    // method buat mindahin objek di dalam ruangan.
    public void pindahObjek(ObjekNonMakanan obj, int x, int y) {
        // buang dulu sementara
        buangObjek(obj);    
        // mindahin
        tambahObjek(obj, x, y);
    }

    // method buat ngedisplay denah ruangan
    public void displayRuangan() {
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
        return this.ruanganKiri;
    }
    public Ruangan getRuanganKanan() {
        return this.ruanganKanan;
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

    public Point getRuanganLoc() {
        return this.ruanganLoc;
    }

    public int getRuanganLocX() {
        return this.ruanganLoc.getX();
    }

    public int getRuanganLocY() {
        return this.ruanganLoc.getY();
    }

    public void setRuanganLocX(int x) {
        this.ruanganLoc.setX(x);
    }

    public void setRuanganLocY(int y) {
        this.ruanganLoc.setY(y);
    }

    public void setRuanganLoc(int x, int y) {
        this.ruanganLoc = new Point(x, y);
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

