package florinczi.projects.chessgame;

public class Engine {

    private Board mainBoard;

    public Engine() {
        mainBoard = new Board();
    }

    public void setMainBoard(Board mainBoard) {
        this.mainBoard = mainBoard;
    }

    public Board getMainBoard() {
        return mainBoard;
    }

    

    public void newGame(){
        mainBoard = new Board();
    }

    public void movePiece(MoveCandidate moveCandidate){
        Board testBoard = mainBoard.movePiece(moveCandidate);
        if (testBoard == null){
            System.out.println("Invalid move.");
            return;
        }
        mainBoard = testBoard;
        
        
    }
}
