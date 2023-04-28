public class ObjekMakanan extends Objek{
    
    private float kekenyangan;
    public ObjekMakanan(String nama, float kekenyangan){
        super(nama);
        
        this.kekenyangan = kekenyangan;
    }

    public float getKekenyangan(){
        return kekenyangan;
    }
}