import java.util.*;
public class Masakan extends ObjekMakanan{
    private List<String> ListBahan;
    public Masakan(String tipe, String nama, float kekenyangan, List<String> ListBahan){
        super(tipe,nama,kekenyangan);
        this.ListBahan = ListBahan;
    }
    public List<String> getListBahan(){
        return ListBahan;
    }
}