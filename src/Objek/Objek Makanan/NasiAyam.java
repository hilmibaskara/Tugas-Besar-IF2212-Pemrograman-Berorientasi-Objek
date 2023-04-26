import java.util.*;
public class NasiAyam extends Masakan{
    ArrayList<String> ListBahan = new ArrayList<String>();
    ListBahan.add("Nasi");
    ListBahan.add("Ayam");
    public NasiAyam(){
        super("Masakan","Nasi Ayam",16,ListBahan);
    }
}