import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ingame = false;
        boolean status = false;
        ArrayList<SIM> sims = new ArrayList<>();
        SIM sim;
        Rumah rumah;
        World dunia;
        int count = 0;
        while (!status) {
            System.out.printf("masukkan command: ");
            String command = scan.nextLine();
            switch (command){
                case "start game" :
                    System.out.println("game dimulai");
                    if (count == 0) {
                        System.out.println("mulai");
                        count = 1;
                        ingame = true;

                        dunia = new World(64,64);
                        for (int x = 0; x < 64; x++) {
                            for (int y = 0; y < 64; y++) {
                                dunia.setObject(x,y,false);
                            }
                        }
                        System.out.printf("masukkan nama lengkap SIM: ");
                        String nama = scan.nextLine();
                        sim = new SIM(nama);
                        int locX;
                        int locY;
                        for (int x = 0; x < 64; x++) {
                            for (int y = 0; y < 64; y++) {
                                if (!dunia.getObject(x,y)){
                                    locX = x;
                                    locY = y;
                                    dunia.setObject(x,y,true);
                                    break;
                                }
                            }
                        }
                        rumah= new Rumah(locX,locY,sim.getNamaLengkap());
                        dunia.addRumah(rumah);
                        sim.setLocRuanganSim(rumah.getKamar());
                        sim.setlocRumahSim(rumah);
                        sim.setBarang("-");
                        KasurSingle kasur = new KasurSingle();
                        KomporGas kompor = new KomporGas();
                        Toilet toilet = new Toilet();
                        MejaDanKursi mejakursi = new MejaDanKursi();
                        Jam jam = new Jam();
                        sim.pasangBarang(kasur,0,0);
                        sim.pasangBarang(kompor,0,5);
                        sim.pasangBarang(toilet,5,5);
                        sim.pasangBarang(mejakursi,1,2);
                        sim.pasangBarang(jam,3,5);
                    }
                    else{
                        System.out.println("game sudah dimulai");
                    }
                    break;
                case "help" :
                    System.out.println("List Command");
                    System.out.println("1. start game");
                    System.out.println("2. help");
                    System.out.println("3. exit");
                    System.out.println("4. sim info");
                    System.out.println("5. current loc");
                    System.out.println("6. inventory");
                    System.out.println("7. upgrade house");
                    System.out.println("8. move room");
                    System.out.println("9. edit room");
                    System.out.println("10. add sim");
                    System.out.println("11. change sim");
                    System.out.println("12. list object");
                    System.out.println("13. go to object");
                    System.out.println("14. action tambahan");
                    break;
                case "exit":
                    System.out.println("berhasil keluar");
                    status = true;
                    break;
                case "sim info":
                    if (ingame){
                        System.out.println("info dari sim");
                        System.out.println("1. Nama Lengkap: " + sim.getNamaLengkap());
                        System.out.println("2. Pekerjaan: " + sim.getPekerjaan());
                        System.out.println("3. Uang: " + sim.getUang());
                        System.out.println("4. Kekenyangan: " + sim.getKekenyangan() );
                        System.out.println("5. Mood: " + sim.getMood() );
                        System.out.println("6. Kesehatan: "+ sim.getKesehatan() );
                    }
                    else{
                        System.out.println("permainan belum dimulai");
                    }
                    break;
                case "current loc":
                    if (ingame){
                        System.out.println("lokasi sekarang");
                        System.out.printf("rumah : rumah " + sim.getLocRumahSim().getNameOwner());
                        System.out.println("");
                        System.out.printf("ruangan : " + sim.getLocRuanganSim().getNamaRuangan());
                        System.out.println("");
                    }
                    else{
                        System.out.println("permainan belum dimulai");
                    }
                    break;
                case "inventory":
                    if (ingame){
                        sim.melihatInventory();
                    }
                    else{
                        System.out.println("permainan belum dimulai");
                    }
                    break;
                case "upgrade house":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("Masukkan posisi ruangan yang akan dibuat: ");
                        String posisi = scan.nextLine();
                        System.out.println("Masukkan ruangan acuan: ");
                        String ruangAcuan = scan.nextLine();
                        System.out.println("Masukkan nama ruangan baru: ");
                        String namaRuanganbaru = scan.nextLine();
                        sim.upgradeRumah(posisi,ruangAcuan,namaRuanganbaru);
                    }
                    else{
                        System.out.println("permainan belum dimulai atau SIM tidak berada di rumah");
                    }
                    break;
                case "move room":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("list ruangan");
                        rumah.displayRumah();
                        int noruangan = scan.nextInt();
                        System.out.printf("masukkan nomor ruangan : ");
                        if(noruangan<=rumah.getDaftarRuangan() && noruangan>0){
                            sim.setLocRuanganSim(rumah.getDaftarRuangan().get(noruangan-1));
                        }
                    }
                    else{
                        System.out.println("permainan belum dimulai atau SIM tidak berada di rumah");
                    }
                    break;
                case "edit room":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("1. beli barang");
                        System.out.println("2. pindah lokasi barang");
                        System.out.printf("masukkan nomor pilihan: ");
                        int editroom = scan.nextInt();
                        switch(editroom){
                            case 1 :
                                System.out.println("list barang dan harga");
                                System.out.println("1. kasur single 50");
                                System.out.println("2. kasur queen size 100");
                                System.out.println("3. kasur king size 150");
                                System.out.println("4. toilet 50");
                                System.out.println("5. kompor gas 100");
                                System.out.println("6. kompor listrik 200");
                                System.out.println("7. meja dan kursi 50");
                                System.out.println("8. jam 10");
                                System.out.printf("masukkan nomor pilihan: ");
                                String beli = scan.nextLine();
                                switch(beli){
                                    case "1":
                                        KasurSingle kasursingle = new KasurSingle();
                                        sim.buyObjek(kasursingle);
                                        break;
                                    case "2":
                                        KasurQueenSize kasurqueen = new KasurQueenSize();
                                        sim.buyObjek(kasurqueen);
                                        break;
                                    case "3":
                                        KasurKingSize kasurking = new KasurKingSize();
                                        sim.buyObjek(kasurking);
                                        break;
                                    case "4":
                                        Toilet toilet = new Toilet();
                                        sim.buyObjek(toilet);
                                        break;
                                    case "5":
                                        KomporGas komporgas = new KomporGas();
                                        sim.buyObjek(kompor);
                                        break;
                                    case "6":
                                        KomporListrik komporlistrik = new KomporListrik();
                                        sim.buyObjek(komporlistrik);
                                        break;
                                    case "7":
                                        MejaDanKursi mejakursi = new MejaDanKursi();
                                        sim.buyObjek(mejakursi);
                                        break;
                                    case "8":
                                        Jam jam = new Jam();
                                        sim.buyObjek(jam);
                                        break;
                                    default:
                                    System.out.println("command salah");
                                break;
                            case 2 :
                                sim.getLocRuanganSim().displayRuangan();
                                sim.getLocRuanganSim().printListObjekRuangan();
                                int pindahbarang = scan.nextInt();
                                if (pindahbarang > 0 && pindahbarang <= sim.getLocRuanganSim().getDaftarObjek().size()) {
                                    System.out.printf("masukkan x baru : ");
                                    int xbaru =scan.nextInt();
                                    System.out.printf("masukkan y baru : ");
                                    int ybaru = scan.nextInt();
                                    sim.getLocRuanganSim().pindahObjek(sim.getLocRuanganSim().getDaftarObjek().get(pindahbarang-1),xbaru,ybaru);
                                }
                                sim.getLocRuanganSim().pindahObjek(); 
                                break;
                            default:
                                System.out.println("command salah");
                                break;
                        }
                    }
                    else{
                        System.out.println("permainan belum dimulai atau SIM tidak berada di ruangan");
                    }
                    break;
                case "add sim":
                    if (ingame){
                        System.out.printf("Masukkan nama lengkap SIM :");
                        command = scan.nextLine();
                        int ada = 0;
                        for (int i = 0; i < sims.size(); i++) {
                            if (!command.equals(sims.get(i).getNamaLengkap())){
                            }
                            else{
                                System.out.println("nama SIM sudah digunakan");
                                ada = 1;
                                break;
                            }
                        }
                        if (dunia.sisaLahan()==0){
                            System.out.println("Tidak ada lahan yang tersisa");
                        }
                        if (ada == 0 && dunia.sisaLahan()>0){
                            for (int x = 0; x < 64; x++) {
                                for (int y = 0; y < 64; y++) {
                                    if (!dunia.getObject(x,y)){
                                        int locX = x;
                                        int locY = y;
                                        dunia.setObject(x,y,true);
                                        break;
                                    }
                                }
                            }
                            SIM simbaru = new SIM(nama);
                            sims.add(simbaru);
                            Rumah rumahbaru= new Rumah(locX,locY,command);
                            dunia.addRumah(rumahbaru);
                            System.out.println("SIM berhasil ditambahkan");
                        }
                    }
                    else{
                        System.out.println("permainan belum dimulai");
                    }
                    break;
                case "change sim":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("daftar SIM");
                        for (int i = 0; i < sims.size(); i++) {
                            System.out.println((i+1) + ". " + sims.get(i).getNamaLengkap());
                        }
                        System.out.print("Pilih nomor Sim yang ingin dimainkan: ");
                        int input = scan.nextInt();
                        if (input > 0 && input <= sims.size()) {
                            SIM sim = sims.get(input-1);
                            rumah = dunia.getRumah().get(input-1);
                            System.out.println("Anda sekarang bermain sebagai Sim " + sim.getNamaLengkap());
                        }
                        else{
                            System.out.println("command salah");
                        }
                    }
                    else{
                        System.out.println("permainan belum dimulai");
                    }
                    break;
                case "list object":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("list objek");
                        sim.getLocRuanganSim().printListObjekRuangan();
                        sim.getLocRuanganSim().displayRuangan();
                    }
                    else{
                        System.out.println("permainan belum dimulai atau SIM tidak berada di ruangan");
                    }
                    break;
                case "go to object":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("list objek");
                        sim.getLocRuanganSim().printListObjekRuangan();
                        System.out.println("masukkan no objek yang ingin dituju");
                        int objektujuan = scan.nextInt();
                        if (objektujuan > 0 && objektujuan <= sim.getLocRuanganSim().getDaftarObjek().size()){
                            if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof KasurSingle){
                                sim.setBarang("KasurSingle");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof KasurKingSize){
                                sim.setBarang("KasurKingSize");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof KasurQueenSize){
                                sim.setBarang("KasurQueenSize");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof KomporGas){
                                sim.setBarang("komporgas");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof KomporListrik){
                                sim.setBarang("komporlistrik");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof Toilet){
                                sim.setBarang("Toilet");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof Jam){
                                sim.setBarang("Jam");
                            }
                            else if(sim.getLocRuanganSim().getDaftarObjek().get(objektujuan-1) instanceof MejaDanKursi){
                                sim.setBarang("MejadanKursi");
                            }
                            System.out.println("berhasil pindah ke objek tujuan");
                        }
                        else{
                            System.out.println("command salah");
                        }
                    }
                    else{
                        System.out.println("permainan belum dimulai atau SIM tidak berada di ruangan");
                    }
                    break;
                case "action tambahan":
                    if (ingame && sim.getStatus().equals("idle")){
                        System.out.println("list aksi");
                        System.out.println("1. beli objek");
                        System.out.println("2. change job");
                        System.out.println("3. kerja");
                        System.out.println("4. olahraga");
                        System.out.println("5. tidur");
                        System.out.println("6. makan");
                        System.out.println("7. masak");
                        System.out.println("8. berkunjung");
                        System.out.println("9. buang air");
                        System.out.println("10. pasang barang");
                        System.out.println("11. melihat waktu");
                        System.out.println("12. mandi");
                        System.out.println("13. main game");
                        System.out.println("14. menonton film");
                        System.out.println("15. membaca");
                        System.out.println("16. bersihkan ruangan");
                        System.out.println("17. meditasi");
                        System.out.println("18. belajar");
                        System.out.printf("masukkan aksi yang ingin dilakukan: ");
                        String aksi = scan.nextLine();
                        switch (aksi){
                            case "beli objek":
                                System.out.println("list barang dan harga");
                                System.out.println("1. kasur single 50");
                                System.out.println("2. kasur queen size 100");
                                System.out.println("3. kasur king size 150");
                                System.out.println("4. toilet 50");
                                System.out.println("5. kompor gas 100");
                                System.out.println("6. kompor listrik 200");
                                System.out.println("7. meja dan kursi 50");
                                System.out.println("8. jam 10");
                                System.out.println("9. nasi 5");
                                System.out.println("10. kentang 3");
                                System.out.println("11. ayam 10");
                                System.out.println("12. sapi 12");
                                System.out.println("13. wortel 3");
                                System.out.println("14. bayam 3");
                                System.out.println("15. kacang 2");
                                System.out.println("16. susu 2");
                                System.out.printf("masukkan nomor pilihan barang yang ingin dibeli");
                                String pilihan = scan.nextLine();
                                switch(pilihan){
                                    case "1":
                                        KasurSingle kasursingle = new KasurSingle();
                                        sim.buyObjek(kasursingle);
                                        break;
                                    case "2":
                                        KasurQueenSize kasurqueen = new KasurQueenSize();
                                        sim.buyObjek(kasurqueen);
                                        break;
                                    case "3":
                                        KasurKingSize kasurking = new KasurKingSize();
                                        sim.buyObjek(kasurking);
                                        break;
                                    case "4":
                                        Toilet toilet = new Toilet();
                                        sim.buyObjek(toilet);
                                        break;
                                    case "5":
                                        KomporGas komporgas = new KomporGas();
                                        sim.buyObjek(kompor);
                                        break;
                                    case "6":
                                        KomporListrik komporlistrik = new KomporListrik();
                                        sim.buyObjek(komporlistrik);
                                        break;
                                    case "7":
                                        MejaDanKursi mejakursi = new MejaDanKursi();
                                        sim.buyObjek(mejakursi);
                                        break;
                                    case "8":
                                        Jam jam = new Jam();
                                        sim.buyObjek(jam);
                                        break;
                                    case "9":
                                        Nasi nasi = new Nasi();
                                        sim.buyObjek(nasi);
                                        break;
                                    case "10":
                                        Kentang kentang = new Kentang();
                                        sim.buyObjek(kentang);
                                        break;
                                    case "11":
                                        Ayam ayam = new Ayam();
                                        sim.buyObjek(ayam);
                                        break;
                                    case "12":
                                        Sapi sapi = new Sapi();
                                        sim.buyObjek(sapi);
                                        break;
                                    case "13":
                                        Wortel wortel = new Wortel();
                                        sim.buyObjek(wortel);
                                        break;
                                    case "14":
                                        Bayam bayam = new Bayam();
                                        sim.buyObjek(bayam);
                                        break;
                                    case "15":
                                        Kentang kentang = new Kentang();
                                        sim.buyObjek(kentang);
                                        break;
                                    case "16":
                                        Susu susu = new Susu();
                                        sim.buyObjek(susu);
                                        break;
                                    default:
                                        System.out.println("command salah");
                                        break;
                                }       
                                break;
                                case "change job":
                                    System.out.println("list pekerjaan");
                                    System.out.println("1. badut sulap");
                                    System.out.println("2. dokter");
                                    System.out.println("3. koki");
                                    System.out.println("4. polisi");
                                    System.out.println("5. programmer");
                                    System.out.printf("masukkan nomor pekerjaan yang dipilih: ");
                                    String pekerjaan = scan.nextLine();
                                    switch (pekerjaan){
                                        case "1":
                                            BadutSulap badut = new BadutSulap();
                                            sim.changeJob(badut);
                                            break;
                                        case "2":
                                            Dokter dokter = new Dokter();
                                            sim.changeJob(dokter);
                                            break;
                                        case "3":
                                            Koki koki = new Koki();
                                            sim.changeJob(koki);
                                            break;
                                        case "4":
                                            Polisi polisi = new Polisi();
                                            sim.changeJob(polisi);
                                            break;
                                        case "5":
                                            Programmer programmer = new Programmer();
                                            sim.changeJob(programmer);
                                            break;
                                        default:
                                            System.out.println("command salah");
                                            break;
                                    }
                                    break;
                                case "kerja":
                                    int durasi = scan.nextInt();
                                    sim.kerja(durasi);
                                    break;
                                case "olahraga":
                                    durasi = scan.nextInt();
                                    sim.olahraga(durasi);
                                    break;
                                case "tidur":
                                    durasi = scan.nextInt();
                                    sim.tidur(durasi);
                                    break;
                                case "makan":
                                    System.out.println("list makanan");
                                    System.out.println("1. nasi");
                                    System.out.println("2. kentang");
                                    System.out.println("3. ayam");
                                    System.out.println("4. sapi");
                                    System.out.println("5. wortel");
                                    System.out.println("6. bayam");
                                    System.out.println("7. kacang");
                                    System.out.println("8. susu");
                                    System.out.println("9. nasi ayam");
                                    System.out.println("10. nasi kari");
                                    System.out.println("11. kacang");
                                    System.out.println("12. tumis sayur");
                                    System.out.println("13. bistik");
                                    System.out.printf("masukkan nomor makanan yang dipilih: ");
                                    String makanan = scan.nextLine();
                                    switch (makanan){
                                        case "1":
                                            Nasi nasi = new Nasi();
                                            sim.makan(nasi);
                                            break;
                                        case "2":
                                            Kentang kentang = new Kentang();
                                            sim.makan(kentang);
                                            break;
                                        case "3":
                                            Ayam ayam = new Ayam();
                                            sim.makan(ayam);
                                            break;
                                        case "4":
                                            Sapi sapi = new Sapi();
                                            sim.makan(sapi);
                                            break;
                                        case "5":
                                            Wortel wortel = new Wortel();
                                            sim.makan(wortel);
                                            break;
                                        case "6":
                                            Bayam bayam = new Bayam();
                                            sim.makan(bayam);
                                            break;
                                        case "7":
                                            Kentang kentang = new Kentang();
                                            sim.makan(kentang);
                                            break;
                                        case "8":
                                            Susu susu = new Susu();
                                            sim.makan(susu);
                                            break;
                                        case "9":
                                            NasiAyam nasiayam = new NasiAyam();
                                            sim.makan(nasiayam);
                                            break;
                                        case "10":
                                            NasiKari nasikari = new NasiKari();
                                            sim.makan(nasikari);
                                            break;
                                        case "11":
                                            SusuKacang susukacang = new SusuKacang();
                                            sim.makan(susukacang);
                                            break;
                                        case "12":
                                            TumisSayur tumissayur = new TumisSayur();
                                            sim.makan(tumissayur);
                                            break;
                                        case "13":
                                            Bistik bistik = new Bistik();
                                            sim.makan(bistik);
                                            break;
                                        default:
                                            System.out.println("command salah");
                                            break;
                                    }
                                    break;
                                case "masak":
                                    System.out.println("list menu masakan");
                                    System.out.println("1. nasi ayam");
                                    System.out.println("2. nasi kari");
                                    System.out.println("3. kacang");
                                    System.out.println("4. tumis sayur");
                                    System.out.println("5. bistik");
                                    System.out.printf("masukkan nomor masakan yang dipilih: ");
                                    String masakan = scan.nextLine();
                                    switch (masakan) {
                                        case "1":
                                            NasiAyam nasiayam = new NasiAyam();
                                            sim.masak(nasiayam);
                                            break;
                                        case "2":
                                            NasiKari nasikari = new NasiKari();
                                            sim.masak(nasikari);
                                            break;
                                        case "3":
                                            SusuKacang susukacang = new SusuKacang();
                                            sim.masak(susukacang);
                                            break;
                                        case "4":
                                            TumisSayur tumissayur = new TumisSayur();
                                            sim.masak(tumissayur);
                                            break;
                                        case "5":
                                            Bistik bistik = new Bistik();
                                            sim.masak(bistik);
                                            break;
                                        default:
                                            System.out.println("command salah");
                                            break;
                                    }
                                    break;
                                case "berkunjung":
                                    dunia.printListRumah();
                                    System.out.printf("masukkan nomor rumah yang dipilih: ");
                                    String tujuan = scan.nextInt();
                                    int x1,x2,y1,y2;
                                    Rumah rumahtujuan = dunia.getRumah().get(tujuan-1);
                                    x1 = rumah.getHouseLocX();
                                    x2 = rumahtujuan.getHouseLocX();
                                    y1 = rumah.getHouseLocY();
                                    y2 = rumahtujuan.getHouseLocY();
                                    sim.berkunjung(x1,y1,x2,y2);
                                    break;
                                case "buang air":
                                    sim.buangAir();
                                    break;
                                case "pasang barang":
                                    break;
                                case "melihat waktu":
                                    sim.melihatWaktu();
                                    break;
                                case "main game":
                                    sim.mainGame();
                                    break;
                                case "menonton film":
                                    sim.menontonFilm();
                                    break;
                                case "membaca":
                                    sim.membaca();
                                    break;
                                case "bersihkan ruangan":
                                    sim.membersihkanRuangan();
                                    break;
                                case "meditasi":
                                    sim.meditasi();
                                    break;
                                case "belajar":
                                    sim.belajar();
                                    break;
                                case "mandi":
                                    sim.mandi();
                                    break;
                                default:
                                    System.out.println("command salah");
                                    break;
                            }
                    }
                    else{
                        System.out.println("permainan belum dimulai atau SIM tidak berada di ruangan");
                    }
                    break;
                }
                default:
                    System.out.println("command salah");
                    System.out.println("ketik \"help\" untuk melihat list command");
                    break;      
        }
        }
    }
}
