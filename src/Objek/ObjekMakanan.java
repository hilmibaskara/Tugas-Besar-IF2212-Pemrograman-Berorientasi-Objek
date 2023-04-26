public class ObjekMakanan extends Objek{
    private String nama;
    private float kekenyangan;
    public ObjekMakanan(String tipe, String nama, float kekenyangan){
        super(tipe);
        this.nama = nama;
        this.kekenyangan = kekenyangan;
    }
    public String getNama(){
        return nama;
    }
    public float getKekenyangan(){
        return kekenyangan;
    }
}