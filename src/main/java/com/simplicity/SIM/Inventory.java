package com.simplicity.SIM;

import com.simplicity.Objek.*;


import java.util.*;
public class Inventory {
    private SIM owner;
    private ArrayList<Objek> objects = new ArrayList<>();
    private boolean status;
    private int capacity;
    Scanner scan = new Scanner(System.in);
    
    public Inventory(SIM owner) {
        this.owner = owner;
        this.capacity = 100;
        this.status = false;
    }

    public SIM getOwner(){
        return owner;
    }

    public ArrayList<Objek> getObjects(){
        return objects;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void addObject(Objek objek){
        if (getCapacity() > 0){
            objects.add(objek);
            setCapacity(getCapacity()- 1);
        }
        else{
            System.out.println("Inventory penuh");
        }
    }

    public void removeObject(Objek objek){
        objects.remove(objek);
        setCapacity(getCapacity() + 1);
    }

    public void printInventory() {
        System.out.println("Inventory milik " + getOwner().getNamaLengkap() + ":");
        Map<Objek, Integer> objekCountMap = new HashMap<>();
        for (Objek objek : objects) {
            objekCountMap.put(objek, objekCountMap.getOrDefault(objek, 0) + 1);
        }
        int i = 1;
        for (Map.Entry<Objek, Integer> entry : objekCountMap.entrySet()) {
            Objek objek = entry.getKey();
            int amount = entry.getValue();
            System.out.println(i + ". " + objek.getNama() + " (" + amount + ")");
            i++;
        }
        if (i == 1) {
            System.out.println("Inventory kosong");
        }
        else{
            System.out.println("List Aksi:");
            System.out.println("1. Gunakan Objek");
            System.out.println("2. Leave");
            System.out.println("Masukan nomor aksi yang diinginkan:");
            
            int choice = scan.nextInt();
            
            if (choice ==1 ){
                i=1;
                System.out.println("List Objek: ");
                for (Map.Entry<Objek, Integer> entry : objekCountMap.entrySet()) {
                    Objek objek = entry.getKey();
                    int amount = entry.getValue();
                    System.out.println(i + ". " + objek.getNama() + " (" + amount + ")");
                    i++;
                }
                System.out.println("Pilih nomor objek yang ingin digunakan: ");
                int pasang = scan.nextInt();
                
                if (pasang<= 0 || pasang > i-1){
                    System.out.println("Pilihan salah");
                    
                }
                else{
                    Objek objek = (Objek) objekCountMap.keySet().toArray()[pasang - 1];
                    if (objek instanceof ObjekNonMakanan)
                    {
                        if (pasang> 0 && pasang <= i-1){
                            if(objek instanceof ObjekNonMakanan){
                                System.out.println("Memasang Objek");
                                System.out.printf("masukkan koordinat x: ");
                                int xpasang = scan.nextInt();
                                scan.nextLine();
                                System.out.printf("masukkan koordinat y: ");
                                int ypasang = scan.nextInt();
                                scan.nextLine();
                                if(objek.getNama().equals("Meja dan Kursi")){
                                    MejaDanKursi mejakursi = new MejaDanKursi();
                                    boolean stats = owner.pasangBarang(mejakursi,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                    
                                }
                                else if(objek.getNama().equals("Kompor Listrik")){
                                    KomporListrik komporlistrik = new KomporListrik();
                                    boolean stats = owner.pasangBarang(komporlistrik,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                                else if(objek.getNama().equals("Kompor Gas")){
                                    KomporGas komporgas = new KomporGas();
                                    boolean stats = owner.pasangBarang(komporgas,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                                else if(objek.getNama().equals("Toilet")){
                                    Toilet toilet = new Toilet();
                                    boolean stats = owner.pasangBarang(toilet,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                                else if(objek.getNama().equals("Jam")){
                                    Jam jam = new Jam();
                                    boolean stats = owner.pasangBarang(jam,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                                else if(objek.getNama().equals("Kasur Single")){
                                    KasurSingle kasursingle = new KasurSingle();
                                    boolean stats = owner.pasangBarang(kasursingle,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                                else if(objek.getNama().equals("Kasur King Size")){
                                    KasurKingSize kasurkingsize = new KasurKingSize();
                                    boolean stats = owner.pasangBarang(kasurkingsize,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                                else if(objek.getNama().equals("Kasur Queen Size")){
                                    KasurQueenSize kasurqueen = new KasurQueenSize();
                                    boolean stats = owner.pasangBarang(kasurqueen,xpasang,ypasang);
                                    if (stats){
                                        removeObject(objek);
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("command salah");
                        }
                    }
            
                    else if (objek instanceof Masakan)
                    {
                        owner.makan((ObjekMakanan)objek);
                    }
                    else {
                        System.out.println("List Aksi:");
                        System.out.println("1. makan");
                        System.out.println("2. masak");
                        System.out.println("Pilih aksi yang ingin dilakukan: ");
                        int pilihan = scan.nextInt();
                        System.out.println();
                        // System.out.println("3. buang");
                        if (pilihan==1){
                            owner.makan((ObjekMakanan)objek);
                        }
                        else if (pilihan == 2 ){
                            System.out.println("list menu masakan");
                            System.out.println("1. nasi ayam");
                            System.out.println("2. nasi kari");
                            System.out.println("3. kacang");
                            System.out.println("4. tumis sayur");
                            System.out.println("5. bistik");
                            System.out.printf("masukkan nomor masakan yang dipilih: ");
                            int masakan = scan.nextInt();
                          
                            
                            switch (masakan) {
                                case 1:
                                    NasiAyam nasiayam = new NasiAyam();
                                    owner.masak(nasiayam);
                                    break;
                                case 2:
                                    NasiKari nasikari = new NasiKari();
                                    owner.masak(nasikari);
                                    break;
                                case 3:
                                    SusuKacang susukacang = new SusuKacang();
                                    owner.masak(susukacang);
                                    break;
                                case 4:
                                    TumisSayur tumissayur = new TumisSayur();
                                    owner.masak(tumissayur);
                                    break;
                                case 5:
                                    Bistik bistik = new Bistik();
                                    owner.masak(bistik);
                                    break;
                                    default:
                                    System.out.println("command salah");
                                    break;
                            }
                 
                        }
                        else{
                            System.out.println("pilihan salah ");
                        }
                        
                    }
                }
                
            }
            else if(choice ==2  ){
                
            }
            else {
                System.out.println("command salah");
            }
        }

        

    }

    public int getObjectNumber(){
        return objects.size();
    }

    public boolean contains(Objek objek){
        return objects.contains(objek);
    }

    public int getObjekAmount(Objek objek) {
        int count = 0;
        for (Objek o : objects) {
            if (o.equals(objek)) {
                count++;
            }
        }
        return count;
    }

}
