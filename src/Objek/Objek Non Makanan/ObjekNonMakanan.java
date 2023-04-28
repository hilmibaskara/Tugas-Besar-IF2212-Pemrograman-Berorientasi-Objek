public class ObjekNonMakanan extends Objek{
    private int panjang;
    private int lebar;
    private int harga;
    public ObjekNonMakanan(String nama, int panjang, int lebar, int harga){
        super(nama);
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
    }
 
    public int getPanjang(){
        return panjang;
    }
    public int getLebar(){
        return lebar;
    }
}