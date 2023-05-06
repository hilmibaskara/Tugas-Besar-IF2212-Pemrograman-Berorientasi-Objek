package com.simplicity.Objek;

import java.util.*;
public class Masakan extends ObjekMakanan{
    private List<ObjekMakanan> ListBahan;
    public Masakan(String nama, float kekenyangan, List<ObjekMakanan> ListBahan){
        super(nama,kekenyangan,99999);
        this.ListBahan = ListBahan;
    }
    public List<ObjekMakanan> getListBahan(){
        return ListBahan;
    }
}