package florinczi.projects.chessgame;

import java.util.Objects;

import florinczi.projects.chessgame.pieces.SpecialMoves;

public class MoveCandidate {
    private Vector vector;

    private Coordinates coordinates;

    private SpecialMoves specialMove;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveCandidate that = (MoveCandidate) o;
        return Objects.equals(vector, that.vector) &&
                Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector, coordinates);
    }



    public MoveCandidate (Coordinates coordinates, Vector vector, SpecialMoves specialMove){
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vector);
        setSpecialMove(specialMove);
        
    }
    
    public MoveCandidate (Coordinates coordinates, Vector vector){
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vector);
        setSpecialMove(null);
        
    }

    public MoveCandidate (Coordinates coordinates, int vecX, int vecY){
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vecX, vecY);               
    }

   
    public MoveCandidate (Coordinates from, Coordinates to){
        this.coordinates = new Coordinates(from);
        this.vector = saveAsVector(to);
            }

    public Vector getVector() {
        return vector;
    }

    public SpecialMoves getSpecialMove() {
        return specialMove;
    }

    public void setSpecialMove(SpecialMoves specialMove) {
        this.specialMove = specialMove;
    }

    public Coordinates getCoord(){
        return coordinates;
    }

    public void addVector(){
        coordinates.addVector(this.vector);
    }

    public boolean isThisVectorValid(){
        return coordinates.isValidVector(vector);
    }

    public Vector saveAsVector(Coordinates destCoordinates){
        int vecX;
        int vecY;
               
        vecX = destCoordinates.getX() - this.coordinates.getX();
        vecY = destCoordinates.getY() - this.coordinates.getY();
        return new Vector(vecX, vecY);
    }
    
}
