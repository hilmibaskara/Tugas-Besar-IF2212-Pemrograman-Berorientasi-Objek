public class Kompor extends ObjekNonMakanan {
    private boolean isAvailable;

    public Kompor(String tipe, String nama, int panjang, int lebar, int harga, int jumlah, boolean isAvailable) {
        super(tipe, nama, panjang, lebar, harga, jumlah);
        this.isAvailable = isAvailable;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public int getJumlah(){
        return super.getJumlah();
    }
}