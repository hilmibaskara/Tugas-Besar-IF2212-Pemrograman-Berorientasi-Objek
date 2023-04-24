import java.util.ArrayList;
import java.util.List;

public class SIM {
    private String namaLengkap;
    private Job pekerjaan;
    private int uang;
    private List<String> inventory;
    private float kekenyangan;
    private float mood;
    private float kesehatan;
    private String status;
    private Time currentTime;

    public SIM(String namaLengkap){
        this.namaLengkap = namaLengkap;
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.status = "idle";
        this.inventory = new ArrayList<String>();
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
        this.currentTime = currentTime;
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

    public List<String> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
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

    public void buyObjek(Objek barang){
        int hargaBarang = barang.hargaBarang(barang);
        if (uang >= hargaBarang) {
            // Kurangi uang sesuai harga barang
            uang -= hargaBarang;

            // Tentukan waktu pengiriman
            int waktuPengiriman = (int) (Math.random() * 5 + 1) * 30;

            // Tunggu waktu pengiriman
            try {
                Thread.sleep(waktuPengiriman * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Tambahkan barang ke inventory
            inventory.add(barang);
            System.out.println("Barang " + barang + " telah diterima dan masuk ke dalam inventory.");
        } else {
            System.out.println("Maaf, uang tidak cukup untuk membeli barang tersebut.");

    }

    public void upgradeRumah(String posisi, String ruangAcuan, String namaRuanganbaru){
        if (uang >= 1500) { // cek apakah uang sim mencukupi untuk upgrade rumah
            if (getRuangan().size() > 1) { // cek apakah sim sudah memiliki lebih dari 1 ruangan
                if (!getRuangan().contains(ruangAcuan)) { // cek apakah ruang acuan yang dipilih ada di daftar ruangan sim
                    System.out.println("Ruang acuan yang dipilih tidak ditemukan.");
                    return;
                }
            }
            // Menambah ruangan baru
            Ruangan ruanganBaru = new Ruangan(namaRuanganbaru);
            switch (posisi.toLowerCase()) {
                case "atas":
                    ruanganBaru.setPosisi(getRuangan().get(ruangAcuan).getX(), getRuangan().get(ruangAcuan).getY() - 1);
                    break;
                case "bawah":
                    ruanganBaru.setPosisi(getRuangan().get(ruangAcuan).getX(), getRuangan().get(ruangAcuan).getY() + 1);
                    break;
                case "kiri":
                    ruanganBaru.setPosisi(getRuangan().get(ruangAcuan).getX() - 1, getRuangan().get(ruangAcuan).getY());
                    break;
                case "kanan":
                    ruanganBaru.setPosisi(getRuangan().get(ruangAcuan).getX() + 1, getRuangan().get(ruangAcuan).getY());
                    break;
                default:
                    System.out.println("Posisi yang dimasukkan tidak valid.");
                    return;
            }
            
            getRuangan().put(namaRuanganbaru, ruanganBaru);
            uang -= 1500;
            
            // Memulai thread untuk waktu pembangunan rumah
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(18 * 60 * 1000); // 18 menit
                        System.out.println("Upgrade rumah selesai.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            
            System.out.println("Upgrade rumah sedang dalam proses pembangunan.");
        } else {
            System.out.println("Uang sim tidak mencukupi untuk upgrade rumah.");
        }
    }
    

    public void changeJob(Job pekerjaan){
        if(this.pekerjaan != null && this.pekerjaan.getWaktuBekerja() >= 720){ //cek apakah sudah bekerja minimal 12 menit
            int gajiBaru = pekerjaan.getGaji();
            int setengahGaji = gajiBaru / 2;
            if(this.uang >= setengahGaji){ //cek apakah SIM memiliki cukup uang untuk membayar setengah gaji
                this.uang -= setengahGaji; //mengurangi uang SIM sebesar setengah gaji pekerjaan baru
                this.pekerjaan = pekerjaan; //mengganti pekerjaan SIM dengan pekerjaan baru
                System.out.println("Pekerjaan berhasil diganti menjadi " + pekerjaan.getNamaPekerjaan() + ".");
            } else {
                System.out.println("Maaf, uang Anda tidak cukup untuk membayar setengah gaji pekerjaan baru.");
            }
        } else {
            System.out.println("Maaf, Anda belum bekerja minimal 12 menit.");
        }
    }

    public void kerja(int durasi) {
        if (status.equals("idle")) {
            if (durasi % 120 == 0) { // validasi kelipatan 120 detik
                status = "working";
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        while (status.equals("working")) {
                            try {
                                Thread.sleep(30000); // 30 detik
                                kekenyangan -= 10;
                                mood -= 10;
                                uang += pekerjaan.getGaji();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        status = "idle";
                    }
                });
                t.start();
            } else {
                System.out.println("Durasi kerja harus kelipatan 120 detik.");
            }
        }
    }
    

    // Implementasi aksi olahraga
    public void olahraga(int durasi){
        if (status.equals("idle")) {
            if(durasi % 20 == 0){
                status = "olahraga";
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        while (status.equals("olahraga")) {
                            try {
                                Thread.sleep(durasi * 1000); // konversi ke milidetik
                                kekenyangan -= 5;
                                kesehatan += 5;
                                mood += 10;
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
        if (status.equals("idle") && durasiTidur % 60 == 0) {
            status = "tidur";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    int waktuTidur = 0;
                    while (status.equals("tidur") && waktuTidur < durasiTidur) {
                        try {
                            Thread.sleep(1000); // 1 detik
                            waktuTidur++;
                            if (waktuTidur % 60 == 0) { // 1 menit
                                mood += 7.5f;
                                kesehatan += 5;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (waktuTidur < 180) { // 3 menit
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
    

    public void makan(Objek makanan){
        if (status.equals("idle")) {
            status = "makan";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("makan")){
                        if(inventory.contains(makanan.getNama())){
                            inventory.remove(makanan.getNama());
                            kekenyangan += makanan.getKekenyangan();
                            mood += makanan.getMood();
                            kesehatan += makanan.getKesehatan();
                        }
                        else{
                            System.out.println("Tidak ada makanan tersebut di inventory.");
                        }
                    }
                    status = "idle";
                }
            });
            t.start();
        }
    }

    public void masak(Objek menu) {
        // Validasi bahan-bahan menu
        boolean bahanTersedia = true;
        for (String bahan : menu.getBahan()) {
            if (!inventory.contains(bahan)) {
                System.out.println("Maaf, " + bahan + " tidak tersedia dalam inventory.");
                bahanTersedia = false;
                break;
            }
        }
        // Jika semua bahan tersedia, maka mulai memasak
        if (bahanTersedia) {
            if (status.equals("idle")) {
                status = "masak";
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        while (status.equals("masak")){
                            int waktuMasak = (int) (1.5 * kekenyangan * menu.getWaktu());
                            System.out.println(namaLengkap + " sedang memasak " + menu.getNama() + " (" + waktuMasak + " detik)...");
                            try {
                                Thread.sleep(waktuMasak * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // Hapus bahan-bahan yang digunakan dari inventory
                            for (String bahan : menu.getBahan()) {
                                inventory.remove(bahan);
                            }
                            inventory.add(menu.getNama());
                            kekenyangan += menu.getKekenyangan();
                            mood += 10;
                            System.out.println(namaLengkap + " berhasil memasak " + menu.getNama() + " dan kekenyangannya bertambah " + menu.getKekenyangan() + " poin.");
                        }
                        status = "idle";
                    }
                });
                t.start();
            }
        }
    }

    public void berkunjung(int x1, int y1, int x2, int y2, Time waktuBerkunjung){
        // Hitung jarak dan waktu yang diperlukan
        if (status.equals("idle")) {
            status = "berkunjung";
            final Time finalTime = waktuBerkunjung;
            Thread t = new Thread(new Runnable() {
                public void run() {
                while (status.equals("berkunjung")){
                    double jarak = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
                    int waktuKunjung = (int) (jarak * 10);
                    if (finalTime.getMinute() % 30 == 0 && finalTime.getSecond() == 0 && waktuKunjung > 0) {
                        mood += waktuKunjung / 30 * 10;
                        kekenyangan -= waktuKunjung / 30 * 10;
                        finalTime.setSecond(finalTime.convertToSecond() - (waktuKunjung * 1000));
                    }
                    // Menunggu sampai waktu kunjung selesai
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (finalTime.convertToSecond() == 0) {
                        break;
                    }
                    }
                status = "idle";
                System.out.println("Kunjungan selesai");
                }
            });
            t.start();
        }
    }
    

    public void buangAir(){
        if (status.equals("idle")) {
            status = "BuangAir";
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

    public void melihatInventory(){
        System.out.println("Inventory:");
        for (String item : this.inventory) {
            System.out.println("- " + item);
        }
    }
    
    public void pasangBarang(String namaBarang, int panjang, int lebar, int x, int y) {
        int[][] rumah = getRumah(); // Mendapatkan layout rumah
        boolean overlap = false;
        
        // Mengecek apakah posisi yang dipilih sudah ada barang di sana
        for (int i = x; i < x+panjang; i++) {
            for (int j = y; j < y+lebar; j++) {
                if (rumah[i][j] != 0) {
                    overlap = true;
                    break;
                }
            }
            if (overlap) {
                break;
            }
        }
        
        if (overlap) {
            System.out.println("Tidak bisa memasang barang di posisi yang dipilih, posisi sudah terisi.");
        } else if (panjang > 6 || lebar > 6) {
            System.out.println("Barang tidak muat dalam ruangan yang tersedia.");
        } else {
            Barang barangBaru = new Barang(namaBarang, panjang, lebar);
            // Menambahkan barang ke list barang yang dimiliki oleh pemain
            getBarang().add(barangBaru);
            
            // Memasang barang pada layout rumah
            for (int i = x; i < x+panjang; i++) {
                for (int j = y; j < y+lebar; j++) {
                    rumah[i][j] = getBarang().indexOf(barangBaru) + 1; // Menandai ruang yang dipakai oleh barang pada layout rumah
                }
            }
            System.out.println("Barang " + namaBarang + " telah dipasang pada posisi " + x + "," + y);
        }
    }

    public void pindahRuangan(Ruangan namaRuangan){
        System.out.println("Sedang teleport ke " + namaRuangan);
    }

    public void melihatWaktu(){
        System.out.println("Sekarang jam " + currentTime.displayHMS());
        System.out.println("Sisa waktu " + currentTime.remainingTime());
    }

    public void mandi(){
        if (status.equals("idle")) {
            status = "mandi";
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

    public void mainGame(){
        if (status.equals("idle")) {
            status = "mainGame";
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

    public void menontonFilm(){
        if (status.equals("idle")) {
            status = "menontonFilm";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("MenontonFilm")){
                        int waktuNonton = 10;
                        mood += 15;
                        kekenyangan -= 5;
                        // Menunggu sampai waktu nonton selesai
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

    public void membaca(){
        if (status.equals("idle")) {
            status = "membaca";
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

    public void membersihkanRuangan(){
        if (status.equals("idle")) {
            status = "membersihkanRuangan";
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

    public void meditasi(){
        if (status.equals("idle")) {
            status = "meditasi";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("meditasi")){
                        int waktuMeditasi = 10;
                        kekenyangan -= 5;
                        kesehatan += 10;
                        // Menunggu sampai waktu membersihkanRuangan selesai
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

    public void belajar(){
        if (status.equals("idle")) {
            status = "belajar";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("belajar")){
                        int waktuBelajar = 10;
                        mood += 5;
                        kekenyangan -= 5;
                        kesehatan -= 5;
                        // Menunggu sampai waktu membersihkanRuangan selesai
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

