import java.util.List;
public class World {
    private int panjang = 0;
    private int lebar = 0;
    private List<Rumah> listRumah;

    public World(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar=lebar;
    }

    public void addRumah(Rumah rumah){
        listRumah.add(rumah);
    }

    public void removeRumah(Rumah rumah){
        listRumah.remove(rumah);
    }

    public List<Rumah> getRumah(){
        return this.listRumah;
    }
}
