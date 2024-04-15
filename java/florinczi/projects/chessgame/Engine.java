package florinczi.projects.chessgame;

import java.util.List;
import java.util.LinkedList;

import florinczi.projects.chessgame.pieces.PieceAction;

public class Engine {

    private Board mainBoard;

    
    public void setMainBoard(Board mainBoard) {
        this.mainBoard = mainBoard;
    }

    public Board getMainBoard() {
        return mainBoard;
    }
   
    public void newGame(){
        setMainBoard(new Board(this));
        mainBoard.newGame();
    }


    public void movePiece(MoveCandidate moveCandidate){
        Board testBoard = mainBoard.prepareMove(moveCandidate);
        if (testBoard == null){ //movePiece returns null when invalid move has been passed
            System.out.println("Invalid move.");
            return;
        }
        mainBoard = testBoard;
        mainBoard.changePlayers();
        
    }


}
