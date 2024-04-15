package florinczi.projects.chessgame.pieces;
import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;
import java.util.ArrayList;
import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;

public class Queen extends Piece{

    public Queen(PlayerColor player, Coordinates location, Board board) {
        super(player, board); //adding color and engine ref

        if (player == PlayerColor.BLACK) super.setShortType('q');
        else super.setShortType('Q'); // setting "visuals"

        super.setLocation(new Coordinates(location)); //setting inner coords
        board.putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location); //init move-probing location
        possibleMoves = new ArrayList<MoveCandidate>(1); //init move list
    
    }

    private Coordinates newLocation;

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        this.setActiveBoard(getActiveBoard().getEngine().getMainBoard()); // gets updated board via engine

        checkLineMove(new Vector(-1, 0)); //west
        checkLineMove(new Vector(1, 0)); //east
        checkLineMove(new Vector(0, 1)); //north
        checkLineMove(new Vector(0, -1)); //south
        checkLineMove(new Vector(-1, -1)); //southwest
        checkLineMove(new Vector(1, 1)); //northeast
        checkLineMove(new Vector(-1, 1)); //northwest
        checkLineMove(new Vector(1, -1)); //southeast
        return possibleMoves;
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
    public Piece clone(Coordinates coord, Board newBoard) {
        Coordinates newCoord = new Coordinates(coord);
        return new Queen(this.getPlayer(), newCoord, newBoard);
    } 

}
