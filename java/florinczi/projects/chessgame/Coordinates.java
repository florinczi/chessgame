package florinczi.projects.chessgame;

import java.util.Objects;

public class Coordinates {
 
    private static final int MIN_X = 1;
    private static final int MAX_X = 8;
    private static final int MIN_Y = 1;
    private static final int MAX_Y = 8;
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        setX(x);
        setY(y);
    }
    public Coordinates (Coordinates coordinates){
        setX(coordinates.getX());
        setY(coordinates.getY());
    }
        

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
               y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
     
    public boolean isValidVector(Vector probe){
        int probeX = this.getX() + probe.getX();
        int probeY = this.getY() + probe.getY();
        return (probeX >= MIN_X && probeX <= MAX_X && probeY >= MIN_Y && probeY <= MAX_Y);
    }

    public void set (Coordinates coord){
        setX(coord.getX());
        setY(coord.getY());

    }

    public void setX(int x) {
        if (x < MIN_X || x > MAX_X) throw new IllegalArgumentException("x coordinate out of bound.");
        this.x = x;
    }

    public void setY(int y) {
        if (y < MIN_Y || y > MAX_Y) throw new IllegalArgumentException("y coordinate out of bound.");
        this.y = y;
    }

    public int getX() {
        return x;
    }
   
    public int getY() {
        return y;
    }
    public void addVector(Vector vector) {
                   
        if (!isValidVector(vector)) throw new IllegalArgumentException("Vector coordinates out of bound.");
        this.y = this.y + vector.getY();
        this.x = this.x + vector.getX();
    }      
}
