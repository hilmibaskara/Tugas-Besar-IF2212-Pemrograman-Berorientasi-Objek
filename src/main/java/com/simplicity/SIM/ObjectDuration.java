package com.simplicity.SIM;

import com.simplicity.Objek.*;
import com.simplicity.World.*;

import java.lang.*;

public class ObjectDuration extends Thread{
    private SIM sim;
    private Objek barang;
    private Object kunci = World.getInstance().getLock();
    
    public ObjectDuration(Objek barang, SIM sim) {
        this.barang = barang;
        this.sim = sim;
    }

    public void run(){
        boolean tick = true;
        int waktuPengiriman = 10; //(int) (Math.random() * 5 + 1) * 30;
        int waktuSelesai = World.getInstance().getDay()*720 + World.getInstance().getTime() + waktuPengiriman;
        while (tick){
            synchronized(kunci){
                try{
                    kunci.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (World.getInstance().getDay()*720 + World.getInstance().getTime() >= waktuSelesai){
                tick = false;
            }
        }
        synchronized(sim.getInventory()){
            // Tambahkan barang ke inventory
            sim.getInventory().addObject(barang);

        }
    }
}
