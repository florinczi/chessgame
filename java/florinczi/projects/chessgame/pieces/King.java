package florinczi.projects.chessgame.pieces;
import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;
import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import java.util.ArrayList;
import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;

public class King extends Piece{

    public King(PlayerColor player, Coordinates location, Board board) {
        super(player, board); //adding color and engine ref

        if (player == PlayerColor.BLACK) super.setShortType('k');
        else super.setShortType('K'); // setting "visuals"

        super.setLocation(new Coordinates(location)); //setting inner coords
        board.putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location); //init move-probing location
        possibleMoves = new ArrayList<MoveCandidate>(1); //init move list
        this.hasMoved = false; //freshly spawned king
          
    }


    private Coordinates newLocation; 
    boolean hasMoved;
    
    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        this.setActiveBoard(getActiveBoard().getEngine().getMainBoard()); // gets updated board via engine

        checkKingMove();
        for (MoveCandidate king: possibleMoves)
            System.out.println(king);
        return possibleMoves;
    }

    public void checkCastleLeft(){
        if (hasMoved)
            return;
        
        newLocation.set(1, getLocation().getY()); //just reusing newLocation to probe the Rooks
        Piece piece = getActiveBoard().getPiece(newLocation); 

        if (!(piece instanceof Rook))
            return;

        Rook rook = (Rook) piece;
        
        if (rook.hasMoved)
            return;
        
        for (int i = 2; i < 5; i++)
            {
                newLocation.set(i, getLocation().getY());
                if (!getActiveBoard().isSquareFree(newLocation)) //checking if square 2,3,4 are free of pieces
                    return;
            }

            
        

    }

    public void checkKingMove(){
        Vector vector = new Vector();
        
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++){ // cycle through every possible x and y in <-1, 1> range
                newLocation.set(getLocation());

                if (x == 0 && y == 0) //skip the vector(0,0) though
                    continue; 

                vector.set(x, y);

                if (newLocation.isValidVector(vector)){
                        newLocation.addVector(vector); //bounds check
                    
                    
                    if (getActiveBoard().isSquareFree(newLocation))
                        possibleMoves.add(new MoveCandidate(getLocation(), vector)); // if it's empty, add to list

                    else if (getActiveBoard().getPiece(newLocation).getPlayer() != getPlayer()) 
                        possibleMoves.add(new MoveCandidate(getLocation(), vector, CAPTURE)); // if it's capture, add to list            
                }
            } 

        } 

    }

    @Override
    public Piece clone(Coordinates coord, Board newBoard) {
        Coordinates newCoord = new Coordinates(coord);
        King king = new King(this.getPlayer(), newCoord, newBoard);
        if (king.getPlayer() == BLACK)
            newBoard.setBlackKing(king);
        else
            newBoard.setWhiteKing(king);
        king.hasMoved = true; //if it gets cloned - it gets moved
        return king;
    } 
    

}
