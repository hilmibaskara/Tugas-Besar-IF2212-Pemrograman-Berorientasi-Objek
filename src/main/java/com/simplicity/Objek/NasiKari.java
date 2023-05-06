package com.simplicity.Objek;

import java.util.*;
public class NasiKari extends Masakan{
    static ArrayList<ObjekMakanan> ListBahan = new ArrayList<ObjekMakanan>();
    public NasiKari(){
        super("Nasi Kari",30,ListBahan);
        ListBahan.add(new Nasi());
        ListBahan.add(new Kentang());
        ListBahan.add(new Wortel());
        ListBahan.add(new Ayam());
    }
}