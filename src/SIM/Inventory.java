import Objek.Objek;
import SIM.SIM;

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

    public void printInventory(){
        System.out.println("Inventory " + getOwner().getNamaLengkap());
        for (Objek objek : objects){
            System.out.println(objek.getNama());
        }
    }

    public int getObjectNumber(){
        return objects.size();
    }

    public boolean contains(Objek objek){
        return objects.contains(objek);
    }
}