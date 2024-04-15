package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;

import java.util.ArrayList;
import java.util.List;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;

public class Rook extends Piece{

    public Rook(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine); //adding color and engine ref

        if (player == PlayerColor.BLACK) super.setShortType('r');
        else super.setShortType('R'); // setting "visuals"

        super.setLocation(new Coordinates(location)); //setting inner coords
        engine.getMainBoard().putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location); //init move-probing location
        possibleMoves = new ArrayList<MoveCandidate>(1); //init move list
    }

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        setActiveBoard(getEngine().getMainBoard());
        checkLineMove(new Vector(-1, 0)); //west
        checkLineMove(new Vector(1, 0)); //east
        checkLineMove(new Vector(0, 1)); //north
        checkLineMove(new Vector(0, -1)); //south

        return possibleMoves;
    }

    @Override
    public Piece clone(Coordinates coord) {
        Coordinates newCoord = new Coordinates(coord);
        return new Rook(this.getPlayer(), newCoord, this.getEngine());
    } 

    Coordinates newLocation;
    boolean hasMoved = false;
    

    public boolean hasMoved() {
        return hasMoved;
    }

    public void checkLineMove(Vector vector){

        boolean collision = false;
        while (!collision){ //incrementing and adding moves until a Piece detected
            newLocation.set(getLocation());
            if (!newLocation.isValidVector(vector))
                return;

            newLocation.addVector(vector);

            if (getActiveBoard().isSquareFree(newLocation)){
                possibleMoves.add(new MoveCandidate(getLocation(), vector));
                vector.incrementDirection();
            }
            else
                collision = true;           
        }
        
        if(getActiveBoard().getPiece(newLocation).getPlayer() != getPlayer()) //if the Piece is the opposite color, this is also a valid move (capture)
            possibleMoves.add(new MoveCandidate(getLocation(), vector, CAPTURE)); 


    }


    

}
