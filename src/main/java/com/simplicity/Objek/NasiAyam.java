package com.simplicity.Objek;

import java.util.*;
public class NasiAyam extends Masakan{
    static ArrayList<ObjekMakanan> ListBahan = new ArrayList<ObjekMakanan>();
    public NasiAyam(){
        super("Nasi Ayam",16,ListBahan);
        ListBahan.add(new Nasi());
        ListBahan.add(new Ayam());
    }
}