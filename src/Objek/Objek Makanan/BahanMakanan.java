public class BahanMakanan extends ObjekMakanan{
    private int harga;
    public BahanMakanan(String nama, float kekenyangan, int harga){
        super(nama,kekenyangan);
        this.harga = harga;
    }
    public int getHarga(){
        return harga;
    }
}