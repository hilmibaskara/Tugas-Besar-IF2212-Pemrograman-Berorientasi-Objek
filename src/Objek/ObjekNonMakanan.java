public class ObjekNonMakanan extends Objek{
    private String nama;
    private int panjang;
    private int lebar;
    private int harga;
    private int jumlah;
    public ObjekNonMakanan(String tipe, String nama, int panjang, int lebar, int harga, int jumlah){
        super(tipe);
        this.nama = nama;
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
        this.jumlah = jumlah;
    }
    public String getNama(){
        return nama;
    }
    public int getPanjang(){
        return panjang;
    }
    public int getLebar(){
        return lebar;
    }
    public int getJumlah(){
        return jumlah;
    }
}