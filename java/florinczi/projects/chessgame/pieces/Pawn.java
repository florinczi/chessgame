package florinczi.projects.chessgame.pieces;

import java.util.ArrayList;
import java.util.List;
import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;
import florinczi.projects.chessgame.pieces.SpecialMoves;

public class Pawn extends Piece{

   

    public Pawn(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine);
        if (player == PlayerColor.BLACK){
            super.setShortType('p');
            moveDirection = -1;
        }
        else{
            super.setShortType('P');
            moveDirection = 1;
        } 
       super.setLocation(new Coordinates(location));
        engine.getMainBoard().putPiece(this, location);
        newLocation = new Coordinates(location);
        probe = new Vector(0, 0);
        possibleMoves = new ArrayList<MoveCandidate>(1);
        }

    boolean isFirstMove = true;
    
    int moveDirection; // which way is the pawn going?
    Coordinates newLocation;
    Vector probe;
    


    void promote (Pawn pawn){
        //TODO important so the pawns don't fall off the board

    }

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        setActiveBoard(getEngine().getMainBoard());
        singleMove();        
        if (isFirstMove)
            doubleMove();
        leftSideCapture();
        rightSideCapture();

       return possibleMoves;
    }

    private void singleMove() {
        newLocation.set(getLocation());
        probe.set(0, moveDirection);
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation))
            possibleMoves.add(new MoveCandidate(getLocation(), probe));
    }

    private void doubleMove() {
        newLocation.set(getLocation());
        newLocation.addVector(probe);
        if (!getActiveBoard().isSquareFree(newLocation))
            return;
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation)) 
            possibleMoves.add(new MoveCandidate(this.getLocation(), new Vector(0, moveDirection * 2))); 
        
    }

    private void leftSideCapture() {
        newLocation.set(getLocation()); 
        probe.set(-1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        if (!getActiveBoard().isSquareFree(newLocation))
            possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.CAPTURE));
        
    }

    private void rightSideCapture() {
        newLocation.set(getLocation()); 
        probe.set(1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        System.out.println(getActiveBoard().isSquareFree(newLocation));
        if (!getActiveBoard().isSquareFree(newLocation))
            possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.CAPTURE));
        
    }

    
    

    @Override
    public void move() {

        // TODO Auto-generated method stub
        
    }

}
