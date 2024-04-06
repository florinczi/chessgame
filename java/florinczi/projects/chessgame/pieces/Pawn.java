package florinczi.projects.chessgame.pieces;

import java.util.HashSet;
import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Coordinates.Vector;

public class Pawn extends Piece{

   

    public Pawn(PlayerColor player, Board activeBoard) {
        super(player, activeBoard);
        if (player == PlayerColor.BLACK) super.setShortType('p');
        else super.setShortType('P');
        
    }

    boolean isFirstMove = true;

    void promote (Pawn pawn){
        //TODO important so the pawns don't fall off the board

    }

    @Override
    public Set<Coordinates> checkPossibleMoves() {
        Set<Coordinates> possibleMoves = new HashSet<>();
        int moveDirection = 1;
        if (getPlayer() == PlayerColor.BLACK) moveDirection = -1; // which way is the pawn going?
        Coordinates newLocation = new Coordinates(getLocation());
        Vector probe = newLocation.new Vector(0, moveDirection); //setting up for moving by one square
        
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation)) possibleMoves.add(new Coordinates(newLocation));
        if (newLocation.getY() == 7 || newLocation.getY() == 0) promote (this);


        if (isFirstMove){ //double move
            newLocation = getLocation();
            newLocation.addVector(probe);
            if (getActiveBoard().isSquareFree(newLocation)){
                newLocation.addVector(probe); //checks first square
                if (getActiveBoard().isSquareFree(newLocation)) 
                    possibleMoves.add(new Coordinates(newLocation)); //if the next one is free also, add to possible moves
            }
                       
        }
        
        newLocation = getLocation(); //now let's check captures
        probe.set(-1, moveDirection); // first left side of the board
        if (newLocation.isValidVector(probe)){
            newLocation.addVector(probe);
            if (!getActiveBoard().isSquareFree(newLocation))
                 possibleMoves.add(new Coordinates(newLocation, true));
        }

        newLocation = getLocation(); // now right side
        probe.set(1, moveDirection); 
        if (newLocation.isValidVector(probe)){
            newLocation.addVector(probe);
            if (!getActiveBoard().isSquareFree(newLocation)) possibleMoves.add(new Coordinates(newLocation, true));
        }

       return possibleMoves;
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
