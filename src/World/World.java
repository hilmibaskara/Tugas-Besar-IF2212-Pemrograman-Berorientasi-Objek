import java.util.List;
public class World {
    private int panjang = 0;
    private int lebar = 0;
    private List<Rumah> listRumah;
    private boolean[][] map;
    
    public World(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar=lebar;
        map = new boolean[panjang][lebar];
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
    
    public int sisaLahan(){
        return (64*64) - listRumah.size();
    }

    public void setObject(int x, int y, boolean bool) {
        map[x][y] = bool;
    }

    public boolean getObject(int x, int y) {
        return map[x][y];
    }
}
