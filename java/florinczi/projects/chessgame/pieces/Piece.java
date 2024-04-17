package florinczi.projects.chessgame.pieces;

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
    return true;
    }

    @Override
    public void movePiece(MoveCandidate move, Board newBoard) {

        newBoard.getBoardmap().remove(move.getCoord()); //removed the piece from new board
        move.addVector(); // adding vector
        if (move.getPromoteTo() != 0){
            newBoard.promotePawn(move.getPromoteTo(), this.getPlayer(), move.getCoord(), newBoard);
        }
        if (newBoard.getBoardmap().containsKey(move.getCoord())) {
            newBoard.replaceWClonedPiece(this, move.getCoord(), newBoard);
        }
        else {
            newBoard.putClonedPiece(this, move.getCoord(), newBoard);
        }
        this.activeBoard = newBoard;
        //now back to Engine
    }

    
}