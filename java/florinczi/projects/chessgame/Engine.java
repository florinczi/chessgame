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
        Piece toMove = mainBoard.getPiece(from);
        if(toMove.checkPossibleMoves().contains(to)){
            System.out.printf("Found the move and moving to ");
            System.out.println(to);
           toMove.setLocation(to);
        }
    }
}
