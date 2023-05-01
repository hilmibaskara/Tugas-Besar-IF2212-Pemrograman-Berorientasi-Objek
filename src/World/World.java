import java.util.*;
public class World {
    private int panjang = 64;
    private int lebar = 64;
    private int day = 0;
    private int time = 0;
    private List<Rumah> listRumah = new  ArrayList<>();
    private static World world = new World();
    private boolean[][] map;
    
    public World() {
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
        return listRumah;
    }

    static World getWorld(){
        return world;
    }
    public void printListRumah(){
        System.out.println("daftar rumah");
        for (int i = 0; i < listRumah.size(); i++) {
            System.out.println((i+1) + ". " + listRumah.get(i) + listRumah.get(i).getNamaOwner());
        }
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

    public int getDay(){
        return day;
    }

    public int getTime(){
        return time;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setTime(int time){
        this.time=time;
    }

    public void addTime(int time){
        int total = this.time + time;
        if(total>720){
            setDay((day+total)/720);
            setTime(total%720);
        }
        else{
            setTime(total);
        }
    }

}
