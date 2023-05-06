package com.simplicity.SIM;

import com.simplicity.Rumah.*;
import com.simplicity.World.*;

import java.lang.*;

public class UpgradeDuration extends Thread{
    private SIM sim;
    private Ruangan ruangbaru;
    private Ruangan ruangacuan;
    private String posisi;
    private Object kunci = World.getInstance().getLock();  

    public UpgradeDuration(Ruangan ruangbaru, Ruangan ruangacuan, String posisi, SIM sim){
        this.ruangbaru = ruangbaru;
        this.ruangacuan = ruangacuan;
        this.posisi = posisi;
        this.sim = sim;
    }

    public void run() {
        boolean tick = true;
        int waktuSelesai = World.getInstance().getDay() + World.getInstance().getTime() + 10;
        while (tick) {
            synchronized (kunci) {
                try {
                    kunci.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (World.getInstance().getDay() * 720 + World.getInstance().getTime() >= waktuSelesai) {
                tick = false;
            }
        }
        // Menambah ruangan baru
        sim.getLocRumahSim().pasangRuanganBaru(ruangbaru, ruangacuan, posisi);
    }
}