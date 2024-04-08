package florinczi.projects.chessgame;

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