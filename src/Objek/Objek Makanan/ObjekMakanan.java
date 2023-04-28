public class ObjekMakanan extends Objek{
    
    private float kekenyangan;
    public ObjekMakanan(String nama, float kekenyangan, int harga){
        super(nama,harga);
        
        this.kekenyangan = kekenyangan;
    }

    public float getKekenyangan(){
        return kekenyangan;
    }
}