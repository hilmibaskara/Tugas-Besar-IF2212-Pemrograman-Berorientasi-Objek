package com.simplicity.SIM;


import com.simplicity.Rumah.*;
import com.simplicity.World.*;
import com.simplicity.Job.*;
import com.simplicity.Objek.*;

import java.util.concurrent.locks.ReentrantLock;
import java.lang.*;
public class SIM{
    private String namaLengkap;
    private Job pekerjaan;
    private int uang;
    private Inventory inventory;
    private float kekenyangan;
    private float mood;
    private float kesehatan;
    private String status;
    private int durasimulaimakan;
    private int durasimulaitidur;
    private int durasikerja;
    private int keluarkerja = -9999;
    private boolean buangair = false;
    private boolean mulaimakan = false;   
    private World world;
    private Rumah locRumahSim;
    private Ruangan locRuangSim;
    private String currentobj;
    private Object kunci = World.getInstance().getLock();
    public ReentrantLock lock = new ReentrantLock();
    private boolean isAlive;

    public SIM(String namaLengkap){
        this.namaLengkap = namaLengkap;
        this.uang = 2000;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.status = "idle";
        this.isAlive = true;
        int randomJob = (int) (Math.random() * 5) + 1;
        switch (randomJob) {
            case 1:
                this.pekerjaan = new BadutSulap();
                break;
            case 2:
                this.pekerjaan = new Koki();
                break;
            case 3:
                this.pekerjaan = new Polisi();
                break;
            case 4:
                this.pekerjaan = new Programmer();
                break;
            case 5:
                this.pekerjaan = new Dokter();
                break;
            default:
                break;
        }
    }

    public String getNamaLengkap() {
        return this.namaLengkap;
    }

    public void setWorld(World world) {
        this.world=world;
    }
    public World getWorld() {
       return this.world;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Job getPekerjaan() {
        return this.pekerjaan;
    }

    public void setPekerjaan(Job pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getUang() {
        return this.uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public float getKekenyangan() {
        return this.kekenyangan;
    }

    public void setKekenyangan(float kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public float getMood() {
        return this.mood;
    }

    public void setMood(float mood) {
        this.mood = mood;
    }

    public float getKesehatan() {
        return this.kesehatan;
    }

    public void setKesehatan(float kesehatan) {
        this.kesehatan = kesehatan;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Rumah getLocRumahSim(){
        return this.locRumahSim;
    }

    public void setLocRumahSim(Rumah namaRumah){
        this.locRumahSim = namaRumah;
    }

    public Ruangan getLocRuanganSim(){
        return this.locRuangSim;
    }

    public void setLocRuanganSim(Ruangan namaRuangan){
        this.locRuangSim = namaRuangan;
    }

    public String getBarang(){
        return this.currentobj;
    }

    public void setBarang(String namaBarang){
        this.currentobj = namaBarang;
    }

    public int getdurasimulaimakan(){
        return this.durasimulaimakan;
    }

    public void setdurasimulaimakan(int durasimulaimakan){
        this.durasimulaimakan = durasimulaimakan;
    }

    public int getdurasimulaitidur(){
        return this.durasimulaitidur;
    }

    public void setdurasimulaitidur(int durasimulaitidur){
        this.durasimulaitidur = durasimulaitidur;
    }

    public int getdurasikerja(){
        return this.durasikerja;
    }

    public void setdurasikerja(int durasikerja){
        this.durasikerja = durasikerja;
    }

    public void cekKesejahteraan(){
        if (mood > 100){
            mood = 100;
        }
        if (kekenyangan > 100){
            kekenyangan = 100;
        }
        if (kesehatan > 100){
            kesehatan = 100;
        }
        if (mood < 0){
            mood = 0;
        }
        if (kekenyangan < 0){
            kekenyangan = 0;
        }
        if (kesehatan < 0){
            kesehatan = 0;
        }
        if (mood == 0 && kesehatan == 0 && kekenyangan == 0){
            isAlive = false;
            System.out.println("SIM meninggal");
            System.exit(0);
        }else{
            isAlive = true;
        }
    }

    public void durasiTidurdanBuangAir(int time){
        int currentTime = World.getInstance().getDay() * 720 + World.getInstance().getTime() + time;
        
        if (currentTime - durasimulaimakan >= 240 && buangair == false && mulaimakan == true){
            kesehatan -= -5;
            mood -= 5;
            setdurasimulaimakan(durasimulaimakan + 240);
        }
        if (currentTime - durasimulaitidur >= 600 ){
            kesehatan -= 5;
            mood -= 5;
            setdurasimulaitidur(durasimulaitidur + 600);
        }
    }
    
    //implementasi aksi membeli objek
    public void buyObjek(Objek barang) {
        int hargaBarang = barang.getHarga();
        if (uang >= hargaBarang) {
            // Waktu pengiriman
            ObjectDuration durasikirim = new ObjectDuration(barang, this);
            durasikirim.start();
            // Kurangi uang sesuai harga barang
            uang -= hargaBarang;
        }
        else {
            System.out.print("Maaf, uang tidak cukup untuk membeli barang tersebut.");
        }
    }
    
    //implementasi aksi upgrade rumah
    public void upgradeRumah(Ruangan namaRuanganbaru, Ruangan ruangAcuan, String posisi){
        if (uang >= 1500) { // cek apakah uang sim mencukupi untuk upgrade rumah
            if (locRumahSim.getDaftarRuangan().size() >= 2) { // cek apakah sim sudah memiliki lebih dari 1 ruangan
                if (namaRuanganbaru.equals(ruangAcuan)) { // cek apakah ruang acuan yang dipilih ada di daftar ruangan sim
                    System.out.println("Pilih nama ruangan yang lain.");
                    return;
                }
            }
            // Mengurangi uang
            uang -= 1500;
            // implementasi timerupgraderumah
            UpgradeDuration durasibangun = new UpgradeDuration(namaRuanganbaru, ruangAcuan, posisi,this);
            durasibangun.start();
        } else {
            System.out.println("Uang sim tidak mencukupi untuk upgrade rumah.");
        }
    }
    
    //implementasi aksi mengganti pekerjaan
    public void changeJob(Job pekerjaanbaru){
        if(this.pekerjaan != null && this.pekerjaan != pekerjaanbaru && durasikerja >= 720){ //cek apakah sudah bekerja minimal 12 menit
            int gajiBaru = pekerjaanbaru.getGaji();
            int setengahGaji = gajiBaru / 2;
            if(this.uang >= setengahGaji){ //cek apakah SIM memiliki cukup uang untuk membayar setengah gaji
                this.uang -= setengahGaji; //mengurangi uang SIM sebesar setengah gaji pekerjaan baru
                this.pekerjaan = pekerjaanbaru; //mengganti pekerjaan SIM dengan pekerjaan baru
                setdurasikerja(0);
                this.keluarkerja = World.getInstance().getDay();
                System.out.println("Pekerjaan berhasil diganti menjadi " + pekerjaanbaru.getNamaPekerjaan() + ".");
            } else {
                System.out.println("Maaf, uang Anda tidak cukup untuk membayar setengah gaji pekerjaan baru.");
            }
        } else {
            System.out.println("Maaf, Anda belum bekerja minimal 12 menit.");
        }
    }

    //implementasi aksi kerja
    public void kerja(int durasi)  {
        if (status.equals("idle")) {
            if (durasi % 120 == 0) { // validasi kelipatan 120 detik
                lock.lock();
                status = "working";
                int currentDay = World.getInstance().getDay();
                if((currentDay-keluarkerja > 0)){
                    try{
                        for (int i = 0; i < durasi; i++) {
                            // Menunggu sampai waktu main selesai
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }finally{
                        lock.unlock();
                        kekenyangan = kekenyangan - (durasi/3);
                        mood = mood - (durasi/3);
                        durasiTidurdanBuangAir(durasi);
                        cekKesejahteraan();
                        status = "idle";
                        World.getInstance().setTime(World.getInstance().getTime()+durasi);   
                    }
                    synchronized (kunci) {
                        kunci.notifyAll();
                    }
                    this.durasikerja = durasikerja + durasi;
                    if (durasikerja % 240 == 0){
                        uang += pekerjaan.getGaji();
                    }
                }
            }
        } else {
            System.out.println("Durasi kerja harus kelipatan 120 detik.");
        }
    }

    // Implementasi aksi olahraga
    public void olahraga(int durasi) {
        if (status.equals("idle")) {
            if(durasi % 20 == 0){// validasi kelipatan 20 detik
                lock.lock();
                status = "olahraga";
                try{
                    for (int i = 0; i < durasi; i++) {
                        // Menunggu sampai waktu main selesai
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally{
                    lock.unlock();
                    kekenyangan -= (durasi/4);
                    kesehatan += (durasi/4);
                    mood += (durasi/2);
                    durasiTidurdanBuangAir(durasi);
                    cekKesejahteraan();
                    status = "idle";   
                    World.getInstance().setTime(World.getInstance().getTime()+durasi);
                }
                synchronized (kunci) {
                    kunci.notifyAll();
                }
            }else{
                System.out.println("Durasi olahraga harus merupakan kelipatan 20 detik.");
            }
        }
    }


    // Implementasi aksi tidur
    public void tidur(int durasiTidur) {
        if (status.equals("idle")) {
            if(currentobj.equals("KasurKingSize") || currentobj.equals("KasurQueenSize") || currentobj.equals("KasurSingle")){
                lock.lock();
                status = "tidur";
                try{
                    for (int i = 0; i < durasiTidur; i++) {
                        if (durasiTidur % 240 == 0) { 
                            mood += 30;
                            kesehatan += 20;
                        }
                        // Menunggu sampai waktu mandi selesai
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally{
                    lock.unlock();
                    cekKesejahteraan();
                    status = "idle";
                    World.getInstance().setTime(World.getInstance().getTime()+durasiTidur);   
                }
                synchronized (kunci) {
                    kunci.notifyAll();
                }
                int currentTime = world.getDay() * 720 + world.getTime();
                setdurasimulaitidur(currentTime);
                durasiTidurdanBuangAir(durasiTidur);
            }
        }
    }
    
    //implementasi aksi makan
    public void makan(ObjekMakanan makanan){
        if (status.equals("idle") && currentobj.equals("MejadanKursi")) {
            if (inventory.contains(makanan)) {
                inventory.removeObject(makanan);
                status = "makan";
                lock.lock();
                int waktuMakan = 30; // Lama waktu makan 
                try{
                    int counter = 0;
                    for (int i = 0; i < waktuMakan; i++) {
                        if (counter % 30 == 0){
                            kekenyangan += makanan.getKekenyangan();
                        }
                        // Menunggu sampai waktu mandi selesai
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                finally{
                    lock.unlock();
                    durasiTidurdanBuangAir(30);
                    cekKesejahteraan();
                    buangair = false;
                    mulaimakan = true;
                    status = "idle";
                    World.getInstance().setTime(World.getInstance().getTime()+waktuMakan);   
                }
                synchronized (kunci) {
                    kunci.notifyAll();
                }
            } else {
                System.out.println("Tidak ada makanan tersebut di inventory.");
                status = "idle";
                return;
            }
            int currentTime = world.getDay()*720 + world.getTime();
            setdurasimulaimakan(currentTime);
        }
    }
    

    public void masak(Masakan menu) {
        // Validasi bahan-bahan menu
        boolean bahanTersedia = true;
        for (ObjekMakanan bahan : menu.getListBahan()) {
            if (!inventory.contains(bahan)) {
                System.out.println("Maaf, " + bahan + " tidak tersedia dalam inventory.");
                bahanTersedia = false;
                break;
            }
        }
        // Jika semua bahan tersedia, maka mulai memasak
        if (bahanTersedia && currentobj.equals("komporgas") || currentobj.equals("koporlistrik")) {
            if (status.equals("idle")) {
                status = "masak";
                lock.lock();
                int waktuMasak = (int) (1.5 * menu.getKekenyangan());
                try{
                    for (int i = 0; i < waktuMasak; i++) {
                        // Menunggu sampai waktu main selesai
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (ObjekMakanan bahan : menu.getListBahan()) {
                        inventory.removeObject(bahan);
                    }
                    inventory.addObject(menu);
                }finally{
                    lock.unlock();
                    mood += 10;
                    durasiTidurdanBuangAir(waktuMasak);
                    cekKesejahteraan();
                    status = "idle";
                    World.getInstance().setTime(World.getInstance().getTime()+waktuMasak);   
                }
                synchronized (kunci) {
                    kunci.notifyAll();
                }
            }
        }
    }

    public void berkunjung(int x1, int y1, int x2, int y2) {
        double jarak = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2)); // menghitung jarak SIM ke rumah tujuan
        int waktu = (int) (jarak); // menghitung waktu yang dibutuhkan 
        if (status.equals("idle")) {
            status = "berkunjung";
            System.out.println(namaLengkap + " sedang berkunjung ke rumah lain (" + waktu + " detik)...");
            lock.lock();
            try{
                for (int i = 0; i < waktu; i++) {
                    mood += (waktu/30) * 10; 
                    kekenyangan -= (waktu/30) * 10; 
                    // Menunggu sampai waktu mandi selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktu);
                cekKesejahteraan();
                status = "idle";
                World.getInstance().setTime(World.getInstance().getTime()+waktu);   
            }
            synchronized (kunci) {
                kunci.notifyAll();
            }
        }
    }
    
    
    // implementasi aksi buang air
    public void buangAir() {
        if (status.equals("idle") && currentobj.equals("Toilet")) {
            status = "BuangAir";
            lock.lock();
            int waktuBuangAir = (int) (Math.random() * 21) + 10; // Waktu buang air secara random antara 10-30 detik
            try{
                int counter = 0;
                for (int i = 0; i < waktuBuangAir; i++) {
                    if (counter % 10 == 0) { // Setiap 10 detik, kekenyangan berkurang
                        kekenyangan -= 20;
                        mood += 10;
                        counter = 0;
                    }
                    counter++;
                    try {
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                cekKesejahteraan();
                status = "idle"; 
                World.getInstance().setTime(World.getInstance().getTime()+waktuBuangAir);  
            }
            synchronized (kunci) {
                kunci.notifyAll();
            }
            int currentTime = world.getDay() * 720 + world.getTime() + waktuBuangAir;
            buangair = true;
        }
    }

    //implementasi aksi melihat inventory
    public void melihatInventory(){
        inventory.printInventory();
    }
    
    //implementasi aksi memasang barang
    public void pasangBarang(ObjekNonMakanan namaBarang, int x, int y) {
        //menambahkan objek
        locRuangSim.tambahObjek(namaBarang, x, y);
        System.out.println("Barang " + namaBarang.getNama() + " telah dipasang pada posisi " + x + "," + y);
    }

    //implementasi aksi berpindah ruangan
    public void berpindahRuangan(Ruangan namaRuangan) {
        // Perbarui lokasi SIM dengan lokasi ruangan yang dituju
        this.locRuangSim = namaRuangan;
        System.out.println("Berhasil pindah ke " + namaRuangan.getNamaRuangan());
    }
    
    //implementasi aksi melihat waktu
    public void melihatWaktu(){
        if (currentobj.equals("Jam")) {
            int waktu = World.getInstance().getTime();
            System.out.println("Sekarang hari ke -  " + World.getInstance().getDay());
            System.out.println("Waktu Sekarang : " + waktu + " detik");
        }
        else{
            System.out.println("Maaf, pindah ke jam di ruangan ini dulu.");
        }
    }

    //implementasi aksi mandi
    public void mandi(){
        if (status.equals("idle")) {
            status = "mandi";
            lock.lock();
            int waktuMandi = 10;
            try{
                for (int i = 0; i < waktuMandi; i++) {
                    kekenyangan -= 0.5;
                    mood += 1;
                    // Menunggu sampai waktu mandi selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktuMandi);
                cekKesejahteraan();
                World.getInstance().setTime(World.getInstance().getTime()+waktuMandi);
                status = "idle";   
            }     
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }

    //implementasi aksi main game
    public void mainGame() {
        if (status.equals("idle")) {
            status = "mainGame";
            lock.lock();
            int waktuMain = 10;
            try{
                for (int i = 0; i < waktuMain; i++) {
                    mood += 1;
                    kekenyangan -= 0.5;
                    kesehatan -= 0.5;
                    // Menunggu sampai waktu main selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktuMain);
                cekKesejahteraan();
                World.getInstance().setTime(World.getInstance().getTime()+waktuMain);
                status = "idle";   
            }
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }

    //implementasi aksi meneonton film
    public void menontonFilm() {
        if (status.equals("idle")) {
            status = "menontonFilm";
            lock.lock();
            int waktuNonton = 10;
            try{
                for (int i = 0; i < waktuNonton; i++) {
                    mood += 1.5;
                    kekenyangan -= 0.5;
                    // Menunggu sampai waktu main selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktuNonton);
                cekKesejahteraan();
                status = "idle";
                World.getInstance().setTime(World.getInstance().getTime()+waktuNonton);   
            }
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }

    //implementasi aksi membaca
    public void membaca() {
        if (status.equals("idle")) {
            status = "membaca";
            lock.lock();
            int waktuMembaca = 10;
            try{
                for (int i = 0; i < waktuMembaca; i++) {
                    mood += 1;
                    kekenyangan -= 0.5;
                    // Menunggu sampai waktu main selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktuMembaca);
                cekKesejahteraan();
                status = "idle"; 
                World.getInstance().setTime(World.getInstance().getTime()+waktuMembaca);  
            }
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }

    //implementasi aksi membersihkan ruangan
    public void membersihkanRuangan() {
        if (status.equals("idle")) {
            status = "membersihkanRuangan";
            lock.lock();
            int waktumembersihkanRuangan = 10;
            try{
                for (int i = 0; i < waktumembersihkanRuangan; i++) {
                    mood += 1;
                    kekenyangan -= 1;
                    // Menunggu sampai waktu main selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktumembersihkanRuangan);
                cekKesejahteraan();
                status = "idle"; 
                World.getInstance().setTime(World.getInstance().getTime()+waktumembersihkanRuangan);  
            }
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }

    //implementasi aksi meditasi
    public void meditasi() {
        if (status.equals("idle")) {
            status = "meditasi";
            lock.lock();
            int waktuMeditasi = 10;
            try{
                for (int i = 0; i < waktuMeditasi; i++) {
                    kesehatan += 1;
                    kekenyangan -= 0.5;
                    // Menunggu sampai waktu main selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktuMeditasi);
                cekKesejahteraan();
                status = "idle"; 
                World.getInstance().setTime(World.getInstance().getTime()+waktuMeditasi);  
            }
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }

    //implementasi aksi belajar
    public void belajar() {
        if (status.equals("idle")) {
            status = "belajar";
            lock.lock();
            int waktuBelajar = 10;
            try{
                for (int i = 0; i < waktuBelajar; i++) {
                    mood += 0.5;
                    kekenyangan -= 0.5;
                    kesehatan -= 0.5;
                    // Menunggu sampai waktu main selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
                durasiTidurdanBuangAir(waktuBelajar);
                cekKesejahteraan();
                status = "idle";   
                World.getInstance().setTime(World.getInstance().getTime()+waktuBelajar);
            }
            synchronized (kunci) {
                kunci.notifyAll();
            } 
        }
    }
}