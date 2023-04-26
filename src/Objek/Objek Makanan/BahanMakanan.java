public class BahanMakanan extends ObjekMakanan{
    private int harga;
    public BahanMakanan(String tipe, String nama, float kekenyangan, int harga){
        super(tipe,nama,kekenyangan);
        this.harga = harga;
    }
}