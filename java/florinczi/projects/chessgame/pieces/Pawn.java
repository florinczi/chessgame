package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;
import static florinczi.projects.chessgame.pieces.SpecialMoves.DOUBLE;
import static florinczi.projects.chessgame.pieces.SpecialMoves.PROMOTE;
import static florinczi.projects.chessgame.pieces.SpecialMoves.ENPASSANT;


import java.util.List;
import java.util.Scanner;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;
import florinczi.projects.chessgame.util.Vector;

public class Pawn extends Piece{

   

    



    public Pawn(PlayerColor player, Coordinates location, Board board) {
        super(player, board); //adding color and engine ref
        if (player == PlayerColor.BLACK){
            super.setShortType('p');
            moveDirection = -1;
        }
        else{
            super.setShortType('P');
            moveDirection = 1;
        } // setting "visuals"
        super.setLocation(new Coordinates(location)); //setting inner coords
        board.putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location);
        probe = new Vector(0, 0);  //init move-probing location and vector
        
        if ((player == WHITE && location.getY() == 2) || (player == BLACK && location.getY() == 7))
            hasMoved = false;
        else 
            hasMoved = true;
        }

    
    
    private int moveDirection; // which way is the pawn going?
    private Coordinates newLocation;
    private Vector probe;
    private boolean hasMoved;
    


    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void promoteHuman (){
        boolean success = false;
        while (!success){
                Scanner scanner = getActiveBoard().getEngine().getMenu().getScanner(); // :D
                System.out.println("Choose promotion for the pawn:");
                System.out.println("q for Queen, n for Knight, b for Bishop, r for Rook :");
                String input;
                input = scanner.nextLine();
                System.out.println("Promoted to");
                                     
                switch (input.charAt(0)){
                    case 'q':
                        getActiveBoard().getBoardmap().replace(getLocation(), new Queen(getPlayer(), getLocation(), getActiveBoard()));
                        System.out.println("Promoted to Queen!");
                        success = true;
                        break;
                    case 'n':
                        getActiveBoard().getBoardmap().replace(getLocation(), new Knight(getPlayer(), getLocation(), getActiveBoard()));
                        System.out.println("Promoted to Knight!");
                        success = true;
                        break;
                    case 'b':
                        getActiveBoard().getBoardmap().replace(getLocation(), new Bishop(getPlayer(), getLocation(), getActiveBoard()));
                        System.out.println("Promoted to Bishop!");
                        success = true;
                        break;
                    case 'r':
                        getActiveBoard().getBoardmap().replace(getLocation(), new Rook(getPlayer(), getLocation(), getActiveBoard()));
                        System.out.println("Promoted to Rook!");
                        success = true;
                        break;
                    default:
                        System.out.println("Wrong input, try again:");
                
            }        
        }

    }
    
    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        this.setActiveBoard(getActiveBoard().getEngine().getMainBoard()); // gets updated board via engine
        singleMove();        
        doubleMove();
        leftSideCapture();
        rightSideCapture();
        return possibleMoves;
    }

    private void singleMove() {
        newLocation.set(getLocation());
        probe.set(0, moveDirection);
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation)){
            if (newLocation.getY() == 8 || newLocation.getY() == 1){               
                possibleMoves.add(new MoveCandidate(getLocation(), probe, PROMOTE, 'q'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, PROMOTE, 'r'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, PROMOTE, 'b'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, PROMOTE, 'n'));
            }
            else
                 possibleMoves.add(new MoveCandidate(getLocation(), probe));
        }
    }

    private void doubleMove() {
        if (hasMoved) //check if the pawn have moved already
            return;
        newLocation.set(getLocation());
        newLocation.addVector(probe);
        if (!getActiveBoard().isSquareFree(newLocation))
            return;
        newLocation.addVector(probe);
        if (getActiveBoard().isSquareFree(newLocation)) 
            possibleMoves.add(new MoveCandidate(this.getLocation(), new Vector(0, moveDirection * 2), DOUBLE)); 
        
    }

    private void leftSideCapture() {
        newLocation.set(getLocation()); 
        probe.set(-1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        
        if (!getActiveBoard().isSquareFree(newLocation) && getActiveBoard().getPiece(newLocation).getPlayer() != getPlayer()){
            if (newLocation.getY() == 8 || newLocation.getY() == 1) {
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'q'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'r'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'b'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'n'));
            }
            else
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.CAPTURE));
            
        }

        if (getActiveBoard().getEnPassant() != null && getActiveBoard().getEnPassant().getGhostPawn().equals(newLocation))
            possibleMoves.add(new MoveCandidate(getLocation(), probe, ENPASSANT));
    }
    

    private void rightSideCapture() {
        newLocation.set(getLocation()); 
        probe.set(1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        
        if (!getActiveBoard().isSquareFree(newLocation) && getActiveBoard().getPiece(newLocation).getPlayer() != getPlayer()){
            if (newLocation.getY() == 8 || newLocation.getY() == 1) {
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'q'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'r'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'b'));
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.PROMOTE, 'n'));
            }
            else{
                possibleMoves.add(new MoveCandidate(getLocation(), probe, SpecialMoves.CAPTURE));
            }
        }

        if (getActiveBoard().getEnPassant() != null && getActiveBoard().getEnPassant().getGhostPawn().equals(newLocation))
            possibleMoves.add(new MoveCandidate(getLocation(), probe, ENPASSANT)); 
        
    }

   

    @Override
    public Piece clone(Coordinates coord, Board newBoard) {
        newBoard.removePiece(this.getLocation());
        Coordinates newCoord = new Coordinates(coord);
        Pawn newPawn = new Pawn(this.getPlayer(), newCoord, newBoard);
        newPawn.setHasMoved(true);
        return newPawn;
    }

    @Override
    public float getValue() {
        return 1f;
    }
    

   

}
