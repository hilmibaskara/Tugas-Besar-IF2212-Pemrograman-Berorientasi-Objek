import java.util.*;
public class SusuKacang extends Masakan{
    static ArrayList<ObjekMakanan> ListBahan = new ArrayList<ObjekMakanan>();
    public SusuKacang(){
        super("Susu Kacang",5,ListBahan);
        ListBahan.add(new Susu());
        ListBahan.add(new Kacang());
    }
}