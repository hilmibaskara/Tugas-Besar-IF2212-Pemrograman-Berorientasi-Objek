package com.simplicity.SIM;

import com.simplicity.Objek.*;


import java.util.*;
public class Inventory {
    private SIM owner;
    private ArrayList<Objek> objects;
    private boolean status;
    private int capacity;

    public Inventory(SIM owner) {
        this.owner = owner;
        this.capacity = 100;
        this.objects = new ArrayList<Objek>();
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
        System.out.println("Inventory milik " + getOwner() + ":");
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
