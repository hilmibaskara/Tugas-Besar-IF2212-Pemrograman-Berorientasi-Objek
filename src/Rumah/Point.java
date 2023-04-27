package Rumah;

public class Point {
    private int x, y;
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void displayPoint(Point point){
        System.out.printf("(" + point.getX(), "," + point.getY() + ")");
    }
}
