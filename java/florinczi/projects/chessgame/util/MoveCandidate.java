package florinczi.projects.chessgame.util;

import java.util.Objects;

import florinczi.projects.chessgame.pieces.SpecialMoves;

public class MoveCandidate {
    private Vector vector;

    private Coordinates coordinates;
    private Coordinates destination;
    private char promoteTo;
    public char getPromoteTo() {
        return promoteTo;
    }

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

    @Override
    public String toString() {
        System.out.printf("CordX: %d, CordY: %d, Vector(%d,%d)%n", coordinates.getX(), coordinates.getY(), vector.getX(), vector.getY());
        return super.toString();
    }



    public MoveCandidate (Coordinates coordinates, Vector vector, SpecialMoves specialMove){
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vector);
        setSpecialMove(specialMove);
        this.promoteTo = '0';
        
    }
    
    public MoveCandidate (Coordinates coordinates, Vector vector, SpecialMoves specialMove, char promoteTo){
        this.promoteTo = promoteTo;
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vector);
        setSpecialMove(specialMove);
        
    }
    
    public MoveCandidate (Coordinates coordinates, Vector vector){
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vector);
        setSpecialMove(null);
        this.promoteTo = '0';
        
    }

    public MoveCandidate (Coordinates coordinates, int vecX, int vecY){
        this.coordinates = new Coordinates(coordinates);
        this.vector = new Vector(vecX, vecY);
        this.promoteTo = '0';               
    }

   
    public MoveCandidate (Coordinates from, Coordinates to){
        this.coordinates = new Coordinates(from);
        this.vector = saveAsVector(to);
        this.promoteTo = '0';
            
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

    public Coordinates getDestination(){
        if (destination == null){
            destination = new Coordinates(coordinates);
            destination.addVector(this.vector);
        }
        return destination;
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
