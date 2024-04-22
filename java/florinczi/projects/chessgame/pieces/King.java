package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.SpecialMoves.LONGCASTLE;
import static florinczi.projects.chessgame.pieces.SpecialMoves.SHORTCASTLE;
import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import java.util.ArrayList;
import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.CheckChecker;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;
import florinczi.projects.chessgame.util.Vector;

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
        this.checkChecker = getActiveBoard().getEngine().getCheckChecker();
          
    }


    private Coordinates newLocation; 
    boolean hasMoved;
    CheckChecker checkChecker;
    
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
        checkCastleLong();
        checkCastleShort();
        System.out.println("Printing possible moves from King ln.51");
        for (MoveCandidate king: possibleMoves)
            System.out.println(king);
        return possibleMoves;
    }

    public void checkCastleLong(){
        if (hasMoved)
            return;
        
        newLocation.set(1, getLocation().getY()); //just reusing newLocation to probe the Rooks
        Piece piece = getActiveBoard().getPiece(newLocation); 

        if (!(piece instanceof Rook))
            return;

        if (((Rook)piece).hasMoved)
            return;
        
        for (int i = 2; i < 5; i++)
            {
                newLocation.set(i, getLocation().getY());
                if (!getActiveBoard().isSquareFree(newLocation)) //checking if square 2,3,4 are free of pieces
                    return;
            }

        Coordinates checkCoordinates = new Coordinates(); 
        
        if (getPlayer() == BLACK)
            checkCoordinates.setY(8);
        else
            checkCoordinates.setY(1);

        for (int i = 3; i < 6; i++)
        {
            checkCoordinates.setX(i);
            if(checkChecker.checkChecks(checkCoordinates, getPlayer(), getActiveBoard()))//tu jets stop
                return;
            
        }
        possibleMoves.add(new MoveCandidate(getLocation(), new Vector(-2, 0), LONGCASTLE));
    }

    public void checkCastleShort(){
        if (hasMoved)
            return;
        
        newLocation.set(8, getLocation().getY()); //just reusing newLocation to probe the Rooks
        Piece piece = getActiveBoard().getPiece(newLocation); 

        if (!(piece instanceof Rook))
            return;

        if (((Rook)piece).hasMoved)
            return;
        
        newLocation.set(6, getLocation().getY());
        if (!getActiveBoard().isSquareFree(newLocation)) //checking if square x=6,x=7 are free of pieces
            return;

        newLocation.set(7, getLocation().getY());
        if (!getActiveBoard().isSquareFree(newLocation)) //checking if square x=6,x=7 are free of pieces
            return;

        Coordinates checkCoordinates = new Coordinates(getLocation()); 
        
        for (int i = 5; i < 8; i++)
        {
            checkCoordinates.setX(i);
            if(checkChecker.checkChecks(checkCoordinates, getPlayer(), getActiveBoard()))
                return;
            
        }
        possibleMoves.add(new MoveCandidate(getLocation(), new Vector(2, 0), SHORTCASTLE));
    }

    public void checkKingMove(){
        Vector vector = new Vector();
        
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++){ // cycle through every possible x and y in <-1, 1> range
                newLocation.set(getLocation());

                if (x == 0 && y == 0) //skip the vector(0,0) though
                    continue; 

                vector.set(x, y);

                if (!newLocation.isValidVector(vector))  //bounds check
                    continue;

                newLocation.addVector(vector);
                    
                    
                if (getActiveBoard().isSquareFree(newLocation) || getActiveBoard().getPiece(newLocation).getPlayer() != getPlayer())
                    possibleMoves.add(new MoveCandidate(getLocation(), vector)); // if it's empty, or an enemy piece is on it, add to the move list      
                
            } 

        } 

    }

    @Override
    public Piece clone(Coordinates coord, Board newBoard) {
        Coordinates newCoord = new Coordinates(coord);
        newBoard.removePiece(this.getLocation());
        King king = new King(this.getPlayer(), newCoord, newBoard);
        if (king.getPlayer() == BLACK)
            newBoard.setBlackKing(king);
        else
            newBoard.setWhiteKing(king);
        king.hasMoved = true; //if it gets cloned - it means it gets moved
        return king;
    } 

    public void longCastle(Board newBoard){
        newLocation.set(getLocation());        
        newLocation.setX(3);
        newBoard.putClonedPiece(this, newLocation); // set the king
        newLocation.setX(1);
        Rook rook = (Rook) newBoard.getPiece(newLocation);
        newLocation.setX(4);
        newBoard.putClonedPiece(rook, newLocation); // set the rook



    }

    public void shortCastle(Board newBoard) {
        newLocation.set(getLocation());        
        newLocation.setX(7);
        newBoard.putClonedPiece(this, newLocation); // set the king
        newLocation.setX(8);
        Rook rook = (Rook) newBoard.getPiece(newLocation);
        newLocation.setX(6);
        newBoard.putClonedPiece(rook, newLocation); // set the rook
    }
    

}
