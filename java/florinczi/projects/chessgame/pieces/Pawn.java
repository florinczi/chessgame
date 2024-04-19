package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.SpecialMoves.DOUBLE;
import static florinczi.projects.chessgame.pieces.SpecialMoves.PROMOTE;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;

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
        possibleMoves = new ArrayList<MoveCandidate>(1);//init move list
        }

    
    
    int moveDirection; // which way is the pawn going?
    Coordinates newLocation;
    Vector probe;
    


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
        System.out.println("Printing pawn Ln 92, possible moves");
        for (MoveCandidate mc: possibleMoves)
            System.out.println(mc);
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
        System.out.println(getLocation().getY());
        if (getLocation().getY() != 7 && getLocation().getY() != 2) //check if the pawn have moved already
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

        if (!getActiveBoard().isSquareFree(newLocation)){
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
    }
    

    private void rightSideCapture() {
        newLocation.set(getLocation()); 
        probe.set(1, moveDirection); 
        if (!newLocation.isValidVector(probe))
            return;
        newLocation.addVector(probe);
        
        if (!getActiveBoard().isSquareFree(newLocation)){
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
    }

   

    @Override
    public Piece clone(Coordinates coord, Board newBoard) {
        newBoard.removePiece(this.getLocation());
        Coordinates newCoord = new Coordinates(coord);
        return new Pawn(this.getPlayer(), newCoord, newBoard);
    }

    
    

   

}
