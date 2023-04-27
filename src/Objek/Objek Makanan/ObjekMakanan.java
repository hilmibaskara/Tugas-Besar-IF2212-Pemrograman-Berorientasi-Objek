public class ObjekMakanan extends Objek{
    
    private float kekenyangan;
    public ObjekMakanan(String tipe, String nama, float kekenyangan){
        super(tipe, nama);
        
        this.kekenyangan = kekenyangan;
    }

    public float getKekenyangan(){
        return kekenyangan;
    }
}