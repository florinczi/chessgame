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
        vector = new Vector();
    
    }

    private Coordinates newLocation;
    private Vector vector;

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        this.setActiveBoard(getActiveBoard().getEngine().getMainBoard()); // gets updated board via engine

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) { //iterate through all directions
                if (i == 0 && j == 0) continue; // Skip the king's own position
                vector.set(i, j);
                checkLineMove(vector); //check this direction
            }
        }
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
        newBoard.removePiece(this.getLocation());
        Coordinates newCoord = new Coordinates(coord);
        return new Queen(this.getPlayer(), newCoord, newBoard);
    } 

}
