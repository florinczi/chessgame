package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.SpecialMoves.LONGCASTLE;

import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;

import florinczi.projects.chessgame.MoveCandidate;

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
    public void movePiece(MoveCandidate move, Board newBoard) {
        if (move.getSpecialMove() == LONGCASTLE){
            ((King) newBoard.getPiece(move.getCoord())).longCastle(newBoard);        
            return;
        }

        move.addVector(); // adding vector
        if (move.getPromoteTo() != 0){ //is the move a pawn promotion?
            newBoard.getEngine().promotePawn(move.getPromoteTo(), move.getCoord(), newBoard);
        }
        
        newBoard.putClonedPiece(this, move.getCoord());
        
       
        
    }

    
}