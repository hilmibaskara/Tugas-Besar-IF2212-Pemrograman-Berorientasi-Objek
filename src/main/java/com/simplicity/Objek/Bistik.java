package com.simplicity.Objek;

import java.util.*;
public class Bistik extends Masakan{
    static ArrayList<ObjekMakanan> ListBahan = new ArrayList<ObjekMakanan>();
    public Bistik(){
        super("Bistik",22,ListBahan);
        ListBahan.add(new Kentang());
        ListBahan.add(new Sapi());
    }
}