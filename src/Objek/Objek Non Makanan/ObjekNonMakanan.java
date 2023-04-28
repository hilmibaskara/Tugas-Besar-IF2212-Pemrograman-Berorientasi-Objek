import Objek.Objek;
public class ObjekNonMakanan extends Objek{
    private int panjang;
    private int lebar;
    public ObjekNonMakanan(String nama, int panjang, int lebar, int harga){
        super(nama,harga);
        this.panjang = panjang;
        this.lebar = lebar;
    }
 
    public int getPanjang(){
        return panjang;
    }
    public int getLebar(){
        return lebar;
    }
}