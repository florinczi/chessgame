package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.Pawn;

public class Engine {

    private Board mainBoard;
    private Menu menu;
    CheckChecker checkChecker;
    
    
    public CheckChecker getCheckChecker() {
        return checkChecker;
    }

    public Menu getMenu() {
        return menu;
    }

    public Engine(Menu menu) {

        this.menu = menu;
    }

    public void setMainBoard(Board mainBoard) {
        this.mainBoard = mainBoard;
        checkChecker = new CheckChecker();
    }

    public Board getMainBoard() {
        return mainBoard;
    }
   
    public void newGame(){
        setMainBoard(new Board(this));
        mainBoard.newGame();
    }


    public void movePieceHuman(MoveCandidate moveCandidate){
        Board testBoard = mainBoard.prepareMove(moveCandidate);
        if (testBoard == null){ //movePiece returns null when invalid move has been passed
            System.out.println("Invalid move.");
            return;
        }
        mainBoard = testBoard;
        if (mainBoard.getPiece(moveCandidate.getCoord()) instanceof Pawn && (moveCandidate.getCoord().getY() == 8 || moveCandidate.getCoord().getY() == 1)){
            ((Pawn)mainBoard.getPiece(moveCandidate.getCoord())).promoteHuman();
            }
        mainBoard.changePlayers();
        
    }


}
