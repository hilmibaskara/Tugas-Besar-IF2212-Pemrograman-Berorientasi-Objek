package SIM;

import java.time.*;
import java.util.*;
import Objek.Objek;
import Ruangan.Ruangan;
import Rumah.Rumah;

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

    public Ruang getLocRuanganSim(){
        return this.locRuanganSim;
    }

    public void setLocRuanganSim(Ruang namaRuangan){
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
    public void buyObjek(Objek barang){
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
            if (locRumahSim.getDaftarRuangan() >= 2) { // cek apakah sim sudah memiliki lebih dari 1 ruangan
                if (namaRuanganbaru.equals(ruangAcuan)) { // cek apakah ruang acuan yang dipilih ada di daftar ruangan sim
                    System.out.println("Pilih nama ruangan yang lain.");
                    return;
                }
            }
            // Menambah ruangan baru
            Ruangan namaRuanganbaru = new Ruangan(namaRuanganbaru)
            locRumahSim.pasangRuanganBaru(namaRuanganbaru, ruangAcuan, posisi);
            uang -= 1500;

            // Memulai thread untuk waktu pembangunan rumah
            Thread t = new Thread(new Runnable() {  
                public void run() {
                    synchronized(this){
                        try {
                            if (status.equals("idle")) {
                                wait();
                            }
                            Thread.sleep(18 * 60 * 1000); // 18 menit
                            System.out.println("Upgrade rumah selesai.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
            
            System.out.println("Upgrade rumah sedang dalam proses pembangunan.");
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
    public void kerja(int durasi) {
        if (status.equals("idle")) {
            if (durasi % 120 == 0) { // validasi kelipatan 120 detik
                if (waktuMulaiBekerja == null || Duration.between(waktuPenggantianPekerjaan, LocalDateTime.now()).toSeconds() >= 720) { // jika belum pernah bekerja pada pekerjaan ini atau sudah 12 menit setelah penggantian pekerjaan
                    waktuMulaiBekerja = LocalDateTime.now();
                    status = "working";
                    synchronized (this) {
                        notify();
                    }
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            while (status.equals("working")) {
                                try {
                                    Thread.sleep(durasi * 1000); // konversi ke milidetik
                                    kekenyangan -= durasi/30 * 10;
                                    mood -= durasi/30 * 10;
                                    uang += pekerjaan.getGaji();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            status = "idle";
                        }
                    });
                    t.start();
                }
            } else {
                System.out.println("Durasi kerja harus kelipatan 120 detik.");
            }
        }
    }
    

    // Implementasi aksi olahraga
    public void olahraga(int durasi){
        if (status.equals("idle")) {
            if(durasi % 20 == 0){// validasi kelipatan 20 detik
                status = "olahraga";
                synchronized (this) {
                    notify();
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
        for (ObjekNonMakanan objek : locRuangSim.getObjekList()) {
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
        if (status.equals("idle") && currentobj.equals("KasurKingSize")) {
            if(currentobj.equals("KasurKingSize") || currentobj.equals("KasurQueenSize") || currentobj.equals("KasurSingle")){
                status = "tidur";
                synchronized (this) {
                    notify();
                }
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        int waktuTidur = 0;
                        while (status.equals("tidur") && waktuTidur < durasiTidur) {
                            try {
                                Thread.sleep(durasiTidur * 1000); 
                                waktuTidur++;
                                if (waktuTidur % 60 == 0) { // 1 menit
                                    mood += 7.5f;
                                    kesehatan += 5;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (waktuTidur < 180 * 1000) { // 3 menit
                            kesehatan -= 5;
                            mood -= 5;
                        } else { 
                            kesehatan += 20;
                            mood += 30;
                        }
                        status = "idle";
                    }
                });
                t.start();
            }
        }
    }
    
    //implementasi aksi makan
    public void makan(ObjekMakanan makanan) {
        // Cek keberadaan meja dan kursi
        boolean mejakursiTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getObjekList()) {
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
            status = "makan";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    int waktuMakan = (int) (Math.random() * 31) + 30; // Lama waktu makan secara random antara 30-60 detik
                    int counter = 0;
                    while (status.equals("makan")) {
                        if (counter == waktuMakan) { // Setiap 30 detik, kekenyangan bertambah
                            kekenyangan += makanan.getKekenyangan();
                            counter = 0;
                        }
                        if (inventory.contains(makanan)) {
                            inventory.removeObject(makanan);
                        } else {
                            System.out.println("Tidak ada makanan tersebut di inventory.");
                            status = "idle";
                            return;
                        }
                        counter++;
                        try {
                            Thread.sleep(waktuMakan * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }
    

    public void masak(Masakan menu) {
        // Cek keberadaan kompor
        boolean komporgasTersedia = false;
        boolean komporlistrikTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getObjekList()) {
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
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        while (status.equals("masak")){
                            int waktuMasak = (int) (1.5 * menu.getKekenyangan());
                            System.out.println(namaLengkap + " sedang memasak " + menu.getNama() + " (" + waktuMasak + " detik)...");
                            try {
                                Thread.sleep(waktuMasak * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // Hapus bahan-bahan yang digunakan dari inventory
                            for (ObjekMakanan bahan : menu.getListBahan()) {
                                inventory.removeObject(bahan);
                            }
                            inventory.addObject(menu);
                            mood += 10;
                            System.out.println(namaLengkap + " berhasil memasak " + menu.getNama() + " dan mood bertambah " + mood);
                        }
                        status = "idle";
                    }
                });
                t.start();
            }
        }
    }

    public void berkunjung(int x1, int y1, int x2, int y2) {
        double jarak = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2)); // menghitung jarak SIM ke rumah tujuan
        int waktu = (int) (jarak); // menghitung waktu yang dibutuhkan 
        if (status.equals("idle")) {
            status = "berkunjung";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    System.out.println(namaLengkap + " sedang berkunjung ke rumah lain (" + waktu + " detik)...");
                    try {
                        Thread.sleep(waktu * 1000); // menghabiskan waktu kunjungan
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mood += waktu/30 * 10; 
                    kekenyangan -= waktu/30 * 10; 
                    System.out.println(namaLengkap + " sampai ke rumah lain dan moodnya bertambah " + mood + " poin, namun kekenyangannya berkurang " + kekenyangan + " poin.");
                    status = "idle";
                }
            });
            t.start();
        }
    }
    
    
    // implementasi aksi buang air
    public void buangAir(){
        // Cek keberadaan toilet
        boolean ToiletTersedia = false;
        for (ObjekNonMakanan objek : locRuangSim.getObjekList()) {
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
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("BuangAir")){
                        if(kekenyangan > 0){
                            kekenyangan -= 20;
                            mood += 10;
                        }
                        else{
                            kesehatan -= 5;
                            mood -= 5;
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi melihat inventory
    public void melihatInventory(){
        inventory.printInventory();
    }
    
    //implementasi aksi memasang barang
    public void pasangBarang(ObjekNonMakanan namaBarang, int x, int y) {
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
        for (ObjekNonMakanan objek : locRuangSim.getObjekList()) {
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
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("mandi")){
                        int waktuMandi = 10;
                        mood += 10;
                        kekenyangan -= 5;
                        // Menunggu sampai waktu mandi selesai
                        try {
                            Thread.sleep(waktuMandi * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi main game
    public void mainGame(){
        if (status.equals("idle")) {
            status = "mainGame";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("mainGame")){
                        int waktuMain = 10;
                        mood += 10;
                        kekenyangan -= 5;
                        kesehatan -= 5;
                        // Menunggu sampai waktu main selesai
                        try {
                            Thread.sleep(waktuMain * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi meneonton film
    public void menontonFilm(){
        if (status.equals("idle")) {
            status = "menontonFilm";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("MenontonFilm")){
                        int waktuNonton = 10;
                        mood += 15;
                        kekenyangan -= 5;
                        // Menunggu sampai waktu menonton selesai
                        try {
                            Thread.sleep(waktuNonton * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi membaca
    public void membaca(){
        if (status.equals("idle")) {
            status = "membaca";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("membaca")){
                        int waktuMembaca = 10;
                        mood += 10;
                        kekenyangan -= 5;
                        // Menunggu sampai waktu membaca selesai
                        try {
                            Thread.sleep(waktuMembaca * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi membersihkan ruangan
    public void membersihkanRuangan(){
        if (status.equals("idle")) {
            status = "membersihkanRuangan";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("membersihkanRuangan")){
                        int waktumembersihkanRuangan = 10;
                        mood += 10;
                        kekenyangan -= 10;
                        // Menunggu sampai waktu membersihkanRuangan selesai
                        try {
                            Thread.sleep(waktumembersihkanRuangan * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi meditasi
    public void meditasi(){
        if (status.equals("idle")) {
            status = "meditasi";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("meditasi")){
                        int waktuMeditasi = 10;
                        kekenyangan -= 5;
                        kesehatan += 10;
                        // Menunggu sampai waktu meditasi selesai
                        try {
                            Thread.sleep(waktuMeditasi * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    //implementasi aksi belajar
    public void belajar(){
        if (status.equals("idle")) {
            status = "belajar";
            synchronized (this) {
                notify();
            }
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("belajar")){
                        int waktuBelajar = 10;
                        mood += 5;
                        kekenyangan -= 5;
                        kesehatan -= 5;
                        // Menunggu sampai waktu belajar selesai
                        try {
                            Thread.sleep(waktuBelajar * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }
}

