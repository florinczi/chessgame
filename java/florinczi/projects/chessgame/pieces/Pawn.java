package florinczi.projects.chessgame.pieces;

import java.util.HashSet;
import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Coordinates.Vector;

public class Pawn extends Piece{

   

    public Pawn(PlayerColor player, Coordinates location, Board activeBoard) {
        super(player, activeBoard);
        if (player == PlayerColor.BLACK){
            super.setShortType('p');
            moveDirection = -1;
        }
        else{
            super.setShortType('P');
            moveDirection = 1;
        } 
        super.setLocation(location);
        activeBoard.putPiece(this, location);
        newLocation = new Coordinates(location);
        probe = newLocation.new Vector(0, 0);
        possibleMoves = new HashSet<>();
        activeBoard.putPiece(this, location);
    }

    boolean isFirstMove = true;
    Set<Coordinates> possibleMoves;
    int moveDirection; // which way is the pawn going?
    Coordinates newLocation;
    Vector probe;


    void promote (Pawn pawn){
        //TODO important so the pawns don't fall off the board

    }

    @Override
    public Set<Coordinates> checkPossibleMoves() {
        possibleMoves.clear();
        newLocation = getLocation();
        singleMove();        
        if (isFirstMove)
            doubleMove();
        leftSideCapture();
        rightSideCapture();

       return possibleMoves;
    }

    private void singleMove() {
        probe.set(0, moveDirection); //setting up for moving by one square
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation))
            possibleMoves.add(new Coordinates(newLocation));
    }

    private void doubleMove() {
        newLocation = getLocation();
        newLocation.addVector(probe);
        if (!getActiveBoard().isSquareFree(newLocation))
            return;
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation)) 
            possibleMoves.add(new Coordinates(newLocation)); 
        
    }

    private void leftSideCapture() {
        newLocation = getLocation(); 
        probe.set(-1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        if (!getActiveBoard().isSquareFree(newLocation))
            possibleMoves.add(new Coordinates(newLocation, true));
        
    }

    private void rightSideCapture() {
        newLocation = getLocation(); 
        probe.set(1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        if (!getActiveBoard().isSquareFree(newLocation))
            possibleMoves.add(new Coordinates(newLocation, true));
        
    }

    @Override
    public boolean isValidMove(Coordinates coordinates) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

}
