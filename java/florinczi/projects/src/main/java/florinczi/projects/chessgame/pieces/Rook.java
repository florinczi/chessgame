package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;

import java.util.ArrayList;
import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;
import florinczi.projects.chessgame.util.Vector;

public class Rook extends Piece{

    public Rook(PlayerColor player, Coordinates location, Board board) {
        super(player, board); //adding color and engine ref

        if (player == PlayerColor.BLACK) super.setShortType('r');
        else super.setShortType('R'); // setting "visuals"

        super.setLocation(new Coordinates(location)); //setting inner coords
        board.putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location); //init move-probing location
        possibleMoves = new ArrayList<MoveCandidate>(1); //init move list
        this.hasMoved = false; //this is freshly spawned rook
    }

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        this.setActiveBoard(getActiveBoard().getEngine().getMainBoard()); // gets updated board via engine

        checkLineMove(new Vector(-1, 0)); //west
        checkLineMove(new Vector(1, 0)); //east
        checkLineMove(new Vector(0, 1)); //north
        checkLineMove(new Vector(0, -1)); //south

        return possibleMoves;
    }

    @Override
    public Piece clone(Coordinates coord, Board newBoard) {
        Coordinates newCoord = new Coordinates(coord);
        newBoard.removePiece(this.getLocation());
        Rook rook = new Rook(this.getPlayer(), newCoord, newBoard);
        rook.hasMoved = true;
        return rook;
    } 

    Coordinates newLocation;
    boolean hasMoved;
    

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

    @Override
    public float getValue() {
        return 5f;
    }

    

}
