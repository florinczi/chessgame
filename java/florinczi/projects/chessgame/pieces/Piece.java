package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;

import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;

public abstract class Piece implements PieceAction{
  
    private char shortType; // char representation of the piece on the board

    private Coordinates location; // where is the piece now
  
    private PlayerColor player; //whose piece is it

    private Board activeBoard;

    public void setActiveBoard(Board activeBoard) {
        this.activeBoard = activeBoard;
    }

    private Engine engine;


    public Engine getEngine() {
        return engine;
    }

    protected List<MoveCandidate> possibleMoves;

    protected Piece (PlayerColor player, Engine engine){
        this.player = player;
        this.engine = engine;
        this.activeBoard = engine.getMainBoard();
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
        if (move.getSpecialMove() == CAPTURE) {
            newBoard.replaceWClonedPiece((PieceAction) this, move.getCoord());
        }
        else {
            newBoard.putClonedPiece((PieceAction) this, move.getCoord());
        }
        
    }

    
}