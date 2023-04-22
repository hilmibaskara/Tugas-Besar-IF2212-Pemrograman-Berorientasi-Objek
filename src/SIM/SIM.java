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


    }

    public void upgradeRumah(World rumah){

    }

    public void changeJob(Job pekerjaan){

    }

    public void kerja(){
        if (status.equals("idle")) {
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
                }
            });
            t.start();
        }
    }

    // Implementasi aksi olahraga
    public void olahraga(){
        if (status.equals("idle")) {
            status = "olahraga";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("olahraga")) {
                        try {
                            Thread.sleep(20000); // 20 detik
                            kekenyangan -= 5;
                            kesehatan += 5;
                            mood += 10;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
        }
    }

    // Implementasi aksi tidur
    public void tidur(){
        if (status.equals("idle")) {
            status = "tidur";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    int waktuTidur = 0;
                    while (status.equals("tidur") && waktuTidur < 240) {
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
                    if (waktuTidur >= 240) { // 4 menit
                        kesehatan -= 5;
                        mood -= 5;
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
                }
            });
            t.start();
        }
    }

    public void masak(Objek menu) {
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
                        inventory.add(menu.getNama());
                        kekenyangan += menu.getKekenyangan();
                        mood += 10;
                        System.out.println(namaLengkap + " berhasil memasak " + menu.getNama() + " dan kekenyangannya bertambah " + menu.getKekenyangan() + " poin.");
                    }
                }
            });
            t.start();
        }
    }

    public void berkunjung(int x1, int y1, int x2, int y2){
        // Hitung jarak dan waktu yang diperlukan
        if (status.equals("idle")) {
            status = "berkunjung";
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (status.equals("Berkunjung")){
                        double jarak = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
                        int waktuKunjung = (int) (jarak * 10);
                        mood += waktuKunjung / 30;
                        kekenyangan -= waktuKunjung / 30;
                        // Menunggu sampai waktu kunjung selesai
                        try {
                            Thread.sleep(waktuKunjung * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
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
    
    public void memasangBarang(String barang){
        if (this.inventory.contains(barang)) {
            this.inventory.remove(barang);
            System.out.println("Barang berhasil dipasang di ruangan.");
        } else {
            System.out.println("Barang tidak ditemukan di inventory.");
        }
    }

    public void pindahRuangan(){
        this.status = "moving";
        System.out.println("Sedang berpindah ruangan.");
    }

    public void melihatWaktu(Time waktu){

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
                }
            });
            t.start();
        }
    }
}

