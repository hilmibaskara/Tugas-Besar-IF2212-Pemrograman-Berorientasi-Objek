public class KasurKingSize extends Kasur {
    public KasurKingSize(String tipe, String nama, int panjang, int lebar, int harga, int jumlah, boolean isAvailable) {
        super(tipe, nama, panjang, lebar, harga, jumlah, isAvailable);
    }
    
    public int getJumlah() {
        return super.getJumlah();
    }
}