package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.Piece;

public class Engine {

    private Board mainBoard;

    public Engine() {
        mainBoard = new Board();
    }

    public Board getMainBoard() {
        return mainBoard;
    }

    public void newGame(){
        mainBoard = new Board();
    }

    public void movePiece(Coordinates from, Coordinates to){
        Piece toMove = getMainBoard().getPiece(from);
        int moveIndex;
        
    }
}
