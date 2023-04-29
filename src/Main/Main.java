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
                    System.out.println("14. action");
                    break;
                case "exit":
                    System.out.println("berhasil keluar");
                    status = true;
                    break;
                case "sim info":
                    System.out.println("info dari sim");
                    System.out.println("1. Nama Lengkap: " + sim.getNamaLengkap());
                    System.out.println("2. Pekerjaan: " + sim.getPekerjaan());
                    System.out.println("3. Uang: " + sim.getUang());
                    System.out.println("4. Kekenyangan: " + sim.getKekenyangan() );
                    System.out.println("5. Mood: " + sim.getMood() );
                    System.out.println("6. Kesehatan: "+ sim.getKesehatan() );
                    System.out.println("7. Status: "+ sim.getStatus() );
                    break;
                case "current loc":
                    System.out.println("lokasi sekarang");
                    break;
                case "inventory":
                    sim.melihatInventory();
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
                case "action":
                    System.out.println("list aksi");
                    break;
                default:
                    System.out.println("command salah");
                    System.out.println("ketik \"help\" untuk melihat list command");
                    break;
            }
        }
    }
}
