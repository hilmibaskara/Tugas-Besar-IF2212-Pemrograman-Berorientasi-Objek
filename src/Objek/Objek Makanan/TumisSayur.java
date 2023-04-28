import java.util.*;
public class TumisSayur extends Masakan{
    static ArrayList<ObjekMakanan> ListBahan = new ArrayList<ObjekMakanan>();
    public TumisSayur(){
        super("Tumis Sayur",5,ListBahan);
        ListBahan.add(new Wortel());
        ListBahan.add(new Bayam());
    }
}