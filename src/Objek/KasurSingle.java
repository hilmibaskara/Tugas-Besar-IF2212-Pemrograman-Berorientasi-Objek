public class KasurSingle extends Kasur {
    public KasurSingle(String tipe, String nama, int panjang, int lebar, int harga, int jumlah, boolean isAvailable) {
        super(tipe, nama, panjang, lebar, harga, jumlah, isAvailable);
    }
    
    public int getJumlah() {
        return super.getJumlah();
    }
}