public class Toilet extends ObjekNonMakanan{
    private boolean isAvailable;
    private String jenis;
    public Toilet (String tipe, String nama, int panjang, int lebar, int harga, int jumlah, boolean isAvailable, String jenis){
        super(tipe,nama,panjang,lebar,harga,jumlah);
        this.isAvailable = isAvailable;
        this.jenis = jenis;
    }
    public boolean getAvailability(){
        return isAvailable;
    }
    public String getJenis(){
        return jenis;
    }
    public void setJenis(String jenis){
        this.jenis = jenis;
    }
    public int getJumlah(){
        return super.getJumlah();
    }
}