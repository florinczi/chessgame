package florinczi.projects.chessgame.pieces;


import static florinczi.projects.chessgame.pieces.SpecialMoves.*;

import java.util.ArrayList;

import java.util.List;

import florinczi.projects.chessgame.Board;

import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;

public abstract class Piece implements PieceAction{
  
    private char shortType; // char representation of the piece on the board

    private Coordinates location; // where is the piece now
  
    private PlayerColor player; //whose piece is it

    private Board activeBoard;

      

    public void setActiveBoard(Board activeBoard) {
        this.activeBoard = activeBoard;
    }

    public List<MoveCandidate> getPossibleMoves() {
        return possibleMoves;
    }

    

    protected List<MoveCandidate> possibleMoves;
 

    protected Piece (PlayerColor player, Board board){
        this.player = player;
        this.activeBoard = board;
        possibleMoves = new ArrayList<>(1);//init move list
        }

    public char getShortType() {
        return shortType;
    }

    public void setShortType(char shortType) {
        this.shortType = shortType;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public PlayerColor getPlayer() {
        return player;
    }

    public Board getActiveBoard() {
        return activeBoard;
    }
    
    public boolean isValidMove(MoveCandidate candidate) {
    int index = possibleMoves.indexOf(candidate);
    if (index == -1) 
        return false; 
    candidate.setSpecialMove(possibleMoves.get(index).getSpecialMove()); // gets special move flag from Piece's internal move list
    return true;
    }

    @Override
    public void movePiece(MoveCandidate move, Board newBoard) { //it would probably be nice to refactor special moves into specific classes
        MoveCandidate copyCoord = new MoveCandidate(move.getCoord(), move.getVector());
       
        if (copyCoord.getSpecialMove() == LONGCASTLE){
            ((King) newBoard.getPiece(copyCoord.getCoord())).longCastle(newBoard);        
            return;
        }

        if (copyCoord.getSpecialMove() == SHORTCASTLE){
            ((King) newBoard.getPiece(copyCoord.getCoord())).shortCastle(newBoard);        
            return;
        }

        if (copyCoord.getSpecialMove() == ENPASSANT){
            newBoard.removePiece(newBoard.getEnPassant());
        }

        if(copyCoord.getSpecialMove() == DOUBLE){
            newBoard.setEnPassant(copyCoord.getCoord(), newBoard.getPiece(copyCoord.getCoord()).getPlayer()); // setting en passant square using starting coord and player coord
        }

        copyCoord.consumeVector(); // adding vector

        if (copyCoord.getPromoteTo() != '0'){ //is the move a pawn promotion?
            newBoard.getEngine().promotePawn(copyCoord.getPromoteTo(), copyCoord.getCoord(), newBoard);
        }
        
        newBoard.putClonedPiece(this, copyCoord.getCoord());
        
       
        
    }

    
}