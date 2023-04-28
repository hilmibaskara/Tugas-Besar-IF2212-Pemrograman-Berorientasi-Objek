import java.util.*;
public class Masakan extends ObjekMakanan{
    private List<String> ListBahan;
    public Masakan(String nama, float kekenyangan, List<String> ListBahan){
        super(nama,kekenyangan);
        this.ListBahan = ListBahan;
    }
    public List<String> getListBahan(){
        return ListBahan;
    }
}