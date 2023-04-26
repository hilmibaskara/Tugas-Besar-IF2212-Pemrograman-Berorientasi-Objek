import java.sql.Time;
public class Jam extends ObjekNonMakanan {
    private int waktu;

    public Jam(String tipe, String nama, int panjang, int lebar, int harga, int jumlah, Time waktu) {
        super(tipe, nama, panjang, lebar, harga, jumlah);
        this.waktu = waktu;
    }

    public int getWaktu() {
        return waktu;
    }
}