package florinczi.projects.chessgame.util;

import java.util.Objects;

public class Vector {

    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector that = (Vector) o;
        return x == that.getX() &&
               y == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;           
    }

    public Vector(Vector vector){
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public Vector() {
    }
    
    public void rotate(){ //rotate vector 90 degrees clockwise
        x = x * -1;
        int z = x;
        x = y;
        y = z;
    }

    public void incrementDirection(){
        if (getX() > 0)
            x++;
        if (getY() > 0)
            y++;
        if (getX() < 0) 
            x--;
        if (getY() < 0)
            y--;    
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void set(int x, int y){
        this.x = x;
        this.y = y;

    }
}