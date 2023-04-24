import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ingame = false;
        boolean status = false;
        while (!status) {
            System.out.printf("masukkan command: ");
            String command = scan.nextLine();
            switch (command){
                case "start game" :
                    System.out.println("game dimulai");
                    break;
                case "help" :
                    System.out.println("isi help");
                    break;
                case "exit":
                    System.out.println("berhasil keluar");
                    status = true;
                    break;
                case "sim info":
                    System.out.println("info dari sim");
                    break;
                case "current loc":
                    System.out.println("lokasi sekarang");
                    break;
                case "inventory":
                    System.out.println("isi inventory");
                    break;
                case "upgrade house":
                    System.out.println("upgrade rumah");
                    break;
                case "move room":
                    System.out.println("pindah ruangan");
                    break;
                case "edit room":
                    System.out.println("ubah ruangan");
                    break;
                case "add sim":
                    System.out.println("tambah sim");
                    break;
                case "change sim":
                    System.out.println("ganti sim");
                    break;
                case "list object":
                    System.out.println("list");
                    break;
                case "go to object":
                    System.out.println("berpindah");
                    break;
                case "action":
                    System.out.println("list aksi");
                    break;
                default:
                    System.out.println("command salah");
                    break;
            }
        }
    }
}
