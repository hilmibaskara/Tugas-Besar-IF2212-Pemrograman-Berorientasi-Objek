package SIM;

import java.time.*;
import java.util.*;
import Objek.Objek;
import Ruangan.Ruangan;
import Rumah.Rumah;

import java.time.*;
import java.util.*;
import java.lang.*;

public class SIM {
    private String namaLengkap;
    private Job pekerjaan;
    private int uang;
    private Inventory inventory;
    private float kekenyangan;
    private float mood;
    private float kesehatan;
    private String status;
    private Time currentTime;
    private LocalDateTime waktuMulaiBekerja;
    private LocalDateTime waktuMakanTerakhir;
    private LocalDate waktuPenggantianPekerjaan;
    private Rumah locRumahSim;
    private Ruangan locRuangSim;
    private String currentobj;

    public SIM(String namaLengkap){
        this.namaLengkap = namaLengkap;
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.status = "idle";
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

    //implementasi menghitung waktu bekerja
    private int getDurasiBekerja() {
        if (waktuMulaiBekerja == null) {
            return 0;
        } else {
            LocalDateTime waktuSekarang = LocalDateTime.now();
            Duration durasi = Duration.between(waktuMulaiBekerja, waktuSekarang);
            return (int) durasi.toSeconds();
        }
    }

    //implementasi aksi membeli objek
    public void buyObjek(Objek barang) {
        int hargaBarang = barang.getHarga();
        if (uang >= hargaBarang) {
            // Kurangi uang sesuai harga barang
            uang -= hargaBarang;

            // Tentukan waktu pengiriman
            int waktuPengiriman = (int) (Math.random() * 5 + 1) * 30;

            // Tunggu waktu pengiriman
            synchronized (this) {
                try {
                    if (status.equals("idle")) {
                        wait();
                    }
                    Thread.sleep(waktuPengiriman * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Tambahkan barang ke inventory
            inventory.addObject(barang);
            System.out.println("Barang " + barang + " telah diterima dan masuk ke dalam inventory.");
        }
        else {
            System.out.println("Maaf, uang tidak cukup untuk membeli barang tersebut.");
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
            // Menambah ruangan baru
            locRumahSim.pasangRuanganBaru(namaRuanganbaru, ruangAcuan, posisi);
            uang -= 1500;
            synchronized(this){
                try {
                    if (status.equals("idle")) {
                        wait();
                    }
                    Thread.sleep(18 * 60 * 1000); // 18 menit
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    status = "idle";   
                }
            }      
        } else {
            System.out.println("Uang sim tidak mencukupi untuk upgrade rumah.");
        }
    }
    
    //implementasi aksi mengganti pekerjaan
    public void changeJob(Job pekerjaanbaru){
        if(this.pekerjaan != null && getDurasiBekerja() >= 720 && this.pekerjaan != pekerjaanbaru){ //cek apakah sudah bekerja minimal 12 menit
            int gajiBaru = pekerjaanbaru.getGaji();
            int setengahGaji = gajiBaru / 2;
            if(this.uang >= setengahGaji){ //cek apakah SIM memiliki cukup uang untuk membayar setengah gaji
                this.uang -= setengahGaji; //mengurangi uang SIM sebesar setengah gaji pekerjaan baru
                this.pekerjaan = pekerjaanbaru; //mengganti pekerjaan SIM dengan pekerjaan baru
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
                if (waktuMulaiBekerja == null || Duration.between(waktuPenggantianPekerjaan, LocalDateTime.now()).toSeconds() >= 720) { // jika belum pernah bekerja pada pekerjaan ini atau sudah 12 menit setelah penggantian pekerjaan
                    waktuMulaiBekerja = LocalDateTime.now();
                    status = "working";
                    synchronized (this) {
                        notify();
                    }
                    try{
                        for (int i = 0; i < durasi; i++) {
                            if (durasi % 30 == 0){
                                kekenyangan -= 10;
                                mood -= 10;
                            }
                            // Menunggu sampai waktu main selesai
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }finally{
                        uang += pekerjaan.getGaji();
                        status = "idle";   
                    }
                }
            } else {
                System.out.println("Durasi kerja harus kelipatan 120 detik.");
            }
        }
    }
    

    // Implementasi aksi olahraga
    public void olahraga(int durasi) {
        if (status.equals("idle")) {
            if(durasi % 20 == 0){// validasi kelipatan 20 detik
                status = "olahraga";
                synchronized (this) {
                    notify();
                }
                try{
                    for (int i = 0; i < durasi; i++) {
                        if(durasi % 20 == 0){
                            kekenyangan -= 5;
                            kesehatan += 5;
                            mood += 10;
                        }
                        // Menunggu sampai waktu main selesai
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally{
                    status = "idle";   
                }
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        while (status.equals("olahraga")) {
                            try {
                                Thread.sleep(durasi * 1000); // konversi ke milidetik
                                kekenyangan -= durasi/20 * 5;
                                kesehatan += durasi/20 * 5;
                                mood += durasi/20 * 10;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        status = "idle";
                    }
                });
                t.start();
            }else{
                System.out.println("Durasi olahraga harus merupakan kelipatan 20 detik.");
            }
        }
    }


    // Implementasi aksi tidur
    public void tidur(int durasiTidur) {
        //cek keberadaan kasur
        boolean KasurKingSizeTersedia= false;
        boolean KasurQueenSizeTersedia= false;
        boolean KasurSingleTersedia = false;
        for (Objek objek : locRuangSim.getDaftarObjek()) {
            if (objek instanceof KasurKingSize) {
                KasurKingSizeTersedia = true;
                break;
            }
            else if(objek instanceof KasurQueenSize){
                KasurQueenSizeTersedia= true;
                break;
            }
            else if(objek instanceof KasurSingle){
                KasurSingleTersedia = true;
                break;
            }
        }
        if (!KasurKingSizeTersedia) {
            System.out.println("Maaf, tidak terdapat KasurKingSize di ruangan ini.");
            return;
        }
        if (!KasurQueenSizeTersedia){
            System.out.println("Maaf, tidak terdapat KasurQueenSize di ruangan ini.");
            return;
        }
        if(!KasurSingleTersedia){
            System.out.println("Maaf, tidak terdapat KasurSingle di ruangan ini.");
            return;
        }
        if (status.equals("idle")) {
            if(currentobj.equals("KasurKingSize") || currentobj.equals("KasurQueenSize") || currentobj.equals("KasurSingle")){
                status = "tidur";
                synchronized (this) {
                    notify();
                }
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
                    status = "idle";   
                }
            }
        }
    }
    
    //implementasi aksi makan
    public void makan(ObjekMakanan makanan){
        // Cek keberadaan meja dan kursi
        boolean mejakursiTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getDaftarObjek()) {
            if (objek instanceof MejaDanKursi) {
                mejakursiTersedia = true;
                break;
            }
        }
        if (!mejakursiTersedia) {
            System.out.println("Maaf, tidak terdapat meja dan kursi di ruangan ini.");
            return;
        }
        if (status.equals("idle") && currentobj.equals("MejadanKursi")) {
            if (inventory.contains(makanan)) {
                inventory.removeObject(makanan);
            } else {
                System.out.println("Tidak ada makanan tersebut di inventory.");
                status = "idle";
                return;
            }
            status = "makan";
            synchronized (this) {
                notify();
            }
            try{
                int waktuMakan = (int) (Math.random() * 31) + 30; // Lama waktu makan secara random antara 30-60 detik
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
                // Setelah makan selesai, cek durasi sejak selesai makan sampai buang air
                long durasiMakan = System.currentTimeMillis()/1000 - waktuMakanTerakhir.toEpochSecond(ZoneOffset.UTC);
                if (durasiMakan > 240) { // Jika sudah lewat 4 menit, kurangi mood dan kesehatan
                    int kurang = (int) (durasiMakan / 240); // Hitung berapa kali kurang dalam interval 4 menit
                    mood -= 5 * kurang;
                    kesehatan -= 5 * kurang;
                }
            }finally{
                status = "idle";   
            }
        }
    }
    

    public void masak(Masakan menu) {
        // Cek keberadaan kompor
        boolean komporgasTersedia = false;
        boolean komporlistrikTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getDaftarObjek()) {
            if (objek instanceof KomporGas) {
                komporgasTersedia = true;
                break;
            }
            else if (objek instanceof KomporListrik) {
                komporlistrikTersedia = true;
                break;
            }
        }
        if (!komporgasTersedia) {
            System.out.println("Maaf, tidak terdapat kompor di ruangan ini.");
            return;
        }
        if (!komporlistrikTersedia) {
            System.out.println("Maaf, tidak terdapat kompor di ruangan ini.");
            return;
        }
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
                synchronized (this) {
                    notify();
                }
                try{
                    int waktuMasak = (int) (1.5 * menu.getKekenyangan());
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
                    mood += 10;
                    status = "idle";   
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
            synchronized (this) {
                notify();
            }
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
                status = "idle";   
            }
        }
    }
    
    
    // implementasi aksi buang air
    public void buangAir() {
        // Cek keberadaan toilet
        boolean ToiletTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getDaftarObjek()) {
            if (objek instanceof Toilet) {
                ToiletTersedia = true;
                break;
            }
        }
        if (!ToiletTersedia) {
            System.out.println("Maaf, tidak terdapat toilet di ruangan ini.");
            return;
        }
        if (status.equals("idle") && currentobj.equals("Toilet")) {
            status = "BuangAir";
            synchronized (this) {
                notify();
            }
            try{
                int waktuBuangAir = (int) (Math.random() * 21) + 10; // Waktu buang air secara random antara 10-30 detik
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
                long durasiMakanDanBuangAir = System.currentTimeMillis() - waktuMakanTerakhir.toEpochSecond(ZoneOffset.UTC);
                if (durasiMakanDanBuangAir > 240000) { // Jika sudah lewat 4 menit, kurangi mood dan kesehatan
                    int kurang = (int) (durasiMakanDanBuangAir / 240000); // Hitung berapa kali kurang dalam interval 4 menit
                    mood -= 5 * kurang;
                    kesehatan -= 5 * kurang;
                }
            }finally{
                status = "idle";   
            }
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
        System.out.println("Barang " + namaBarang + " telah dipasang pada posisi " + x + "," + y);
    }

    //implementasi aksi berpindah ruangan
    public void berpindahRuangan(Ruangan namaRuangan) {
        // Perbarui lokasi SIM dengan lokasi ruangan yang dituju
        this.locRuangSim = namaRuangan;
        System.out.println("Berhasil pindah ke " + namaRuangan.getNamaRuangan());
    }
    
    //implementasi aksi melihat waktu
    public void melihatWaktu(){
        // Cek keberadaan toilet
        boolean jamTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getDaftarObjek()) {
            if (objek instanceof Jam) {
                jamTersedia = true;
                break;
            }
        }
        if (!jamTersedia && currentobj.equals("Jam")) {
            System.out.println("Maaf, tidak terdapat jam di ruangan ini.");
            return;
        }
        System.out.println("Sekarang jam " + currentTime.displayHMS());
        System.out.println("Sisa waktu " + currentTime.remainingTime());
    }

    //implementasi aksi mandi
    public void mandi(){
        if (status.equals("idle")) {
            status = "mandi";
            synchronized (this) {
                notify();
            }
            try{
                float waktuMandi = 10;
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
                status = "idle";   
            }      
        }
    }

    //implementasi aksi main game
    public void mainGame() {
        if (status.equals("idle")) {
            status = "mainGame";
            synchronized (this) {
                notify();
            }
            try{
                int waktuMain = 10;
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
                status = "idle";   
            }
        }
    }

    //implementasi aksi meneonton film
    public void menontonFilm() {
        if (status.equals("idle")) {
            status = "menontonFilm";
            synchronized (this) {
                notify();
            }
            try{
                int waktuNonton = 10;
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
                status = "idle";   
            }
        }
    }

    //implementasi aksi membaca
    public void membaca() {
        if (status.equals("idle")) {
            status = "membaca";
            synchronized (this) {
                notify();
            }
            try{
                int waktuMembaca = 10;
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
                status = "idle";   
            }
        }
    }

    //implementasi aksi membersihkan ruangan
    public void membersihkanRuangan() {
        if (status.equals("idle")) {
            status = "membersihkanRuangan";
            synchronized (this) {
                notify();
            }
            try{
                int waktumembersihkanRuangan = 10;
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
                status = "idle";   
            }
        }
    }

    //implementasi aksi meditasi
    public void meditasi() {
        if (status.equals("idle")) {
            status = "meditasi";
            synchronized (this) {
                notify();
            }
            try{
                int waktuMeditasi = 10;
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
                status = "idle";   
            }
        }
    }

    //implementasi aksi belajar
    public void belajar() {
        if (status.equals("idle")) {
            status = "belajar";
            synchronized (this) {
                notify();
            }
            try{
                int waktuBelajar = 10;
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
                status = "idle";   
            }
        }
    }
}
