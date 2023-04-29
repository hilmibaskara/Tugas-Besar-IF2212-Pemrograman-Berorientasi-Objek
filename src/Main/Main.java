import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ingame = false;
        boolean status = false;
        ArrayList<SIM> sims = new ArrayList<>();
        SIM sim;
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

                        World dunia = new World(64,64);
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

                        Rumah rumah= new Rumah(locX,locY,sim.getNamaLengkap());
                        dunia.addRumah(rumah);
                        Ruangan ruangan = new Ruangan();
                        rumah.addRuangan();
                        KasurSingle kasur = new KasurSingle();
                        KomporGas kompor = new KomporGas();
                        Toilet toilet = new Toilet();
                        MejaDanKursi mejakursi = new MejaDanKursi();
                        Jam jam = new Jam();
                        //ruangan.addObject(kasur);
                        //ruangan.addObject(kompor);
                        //ruangan.addObject(toilet);
                        //ruangan.addObject(mejakursi);
                        //ruangan.addObject(jam);
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
                    System.out.println("Masukkan posisi ruangan yang akan dibuat: ");
                    String posisi = scan.nextLine();
                    System.out.println("Masukkan ruangan acuan: ");
                    String ruangAcuan = scan.nextLine();
                    System.out.println("Masukkan nama ruangan baru: ");
                    String namaRuanganbaru = scan.nextLine();
                    sim.upgradeRumah(posisi,ruangAcuan,namaRuanganbaru);
                    break;
                case "move room":
                    System.out.println("pindah ruangan");
                    break;
                case "edit room":
                    System.out.println("ubah ruangan");
                    break;
                case "add sim":
                    System.out.printf("Masukkan nama lengkap SIM :");
                    command = scan.nextLine();
                    for (int i = 0; i < sims.size(); i++) {
                        if (!command.equals(sims.get(i).getNamaLengkap())){
                            System.out.println("SIM berhasil ditambahkan");
                        }
                        else{
                            System.out.println("nama SIM sudah digunakan");
                        }
                    }
                    break;
                case "change sim":
                    System.out.println("daftar SIM");
                    for (int i = 0; i < sims.size(); i++) {
                        System.out.println((i+1) + ". " + sims.get(i));
                    }
                    System.out.print("Pilih nomor Sim yang ingin dimainkan: ");
                    int input = scan.nextInt();
                    if (input > 0 && input <= sims.size()) {
                        SIM sim = sims.get(input-1);
                        System.out.println("Anda sekarang bermain sebagai Sim " + sim.getNamaLengkap());
                    }
                    else{
                        System.out.println("out of range");
                    }
                    break;
                case "list object":
                    System.out.println("list objek");
                    
                    break;
                case "go to object":
                    System.out.println("berpindah");
                    break;
                case "action tambahan":
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
                                        sim.buyObjek(KasurSingle kasur);
                                        break;
                                    case "2":
                                        sim.buyObjek(KasurQueenSize kasur);
                                        break;
                                    case "3":
                                        sim.buyObjek(KasurKingSize kasur);
                                        break;
                                    case "4":
                                        sim.buyObjek(Toilet toilet);
                                        break;
                                    case "5":
                                        sim.buyObjek(KomporGas kompor);
                                        break;
                                    case "6":
                                        sim.buyObjek(KomporListrik kompor);
                                        break;
                                    case "7":
                                        sim.buyObjek(MejaDanKursi mejakursi);
                                        break;
                                    case "8":
                                        sim.buyObjek(Jam jam);
                                        break;
                                    case "9":
                                        sim.buyObjek(Nasi nasi);
                                        break;
                                    case "10":
                                        sim.buyObjek(Kentang kentang);
                                        break;
                                    case "11":
                                        sim.buyObjek(Ayam ayam);
                                        break;
                                    case "12":
                                        sim.buyObjek(Sapi sapi);
                                        break;
                                    case "13":
                                        sim.buyObjek(Wortel wortel);
                                        break;
                                    case "14":
                                        sim.buyObjek(Bayam bayam);
                                        break;
                                    case "15":
                                        sim.buyObjek(Kentang kentang);
                                        break;
                                    case "16":
                                        sim.buyObjek(Susu susu);
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
                                durasi = scan.nextInt();
                                sim.olahraga(durasi);
                                break;
                            case "masak":
                                break;
                            case "berkunjung":
                                break;
                            case "buang air":
                                break;
                            case "pasang barang":
                                break;
                            case "melihat waktu":
                                break;
                            case "main game":
                                break;
                            case "menonton film":
                                break;
                            case "membaca":
                                break;
                            case "bersihkan ruangan":
                                break;
                            case "meditasi":
                                break;
                            case "belajar":
                                break;
                            default:
                                System.out.println("command salah");
                                break;
                        }
                    break;
                default:
                    System.out.println("command salah");
                    System.out.println("ketik \"help\" untuk melihat list command");
                    break;
            }
        }
    }
}
