package florinczi.projects.chessgame;
import florinczi.projects.chessgame.pieces.*;
import florinczi.projects.chessgame.util.BoardUtil;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;

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
        BoardUtil.newGameStd(mainBoard);
    }

    public void newGame2(){
        setMainBoard(new Board(this));
        BoardUtil.pawnPromotionCaptureTest(mainBoard);
    }

    public void newGame3(){
        setMainBoard(new Board(this));
        BoardUtil.castlingTest(mainBoard);
    }

    public void newGame4(){
        setMainBoard(new Board(this));
        BoardUtil.enPassantTest(mainBoard);
    }


    public void movePieceHuman(MoveCandidate moveCandidate){
        Board testBoard = prepareMove(getMainBoard(), moveCandidate);
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

    public Board prepareMove(Board board, MoveCandidate moveCandidate){ //This method checks if the
                
        Piece piece = board.getPiece(moveCandidate.getCoord());
        PieceAction pa = piece;
                
        if (piece == null){
            System.out.println("No piece on this coordinates");
            return null;
        }
        pa.checkPossibleMoves();
    
        if (piece.getPlayer() != board.getNowPlaying()){
            System.out.println("Wrong player");
            return null;
        }
        if (!piece.isValidMove(moveCandidate)){
            System.out.println("Invalid move");
            return null;
        }
        Board testBoard = new Board(board);
        piece.movePiece(moveCandidate, testBoard); //move it with MoveCandidate on testBoard   
        piece.setActiveBoard(testBoard);
        return testBoard;
    }

    public void promotePawn (char choice, Coordinates coord, Board newBoard){ 
        Piece piece;
        PlayerColor pc = newBoard.getPiece(coord).getPlayer();
        switch (choice){
            case 'q': piece = new Queen(pc, coord, newBoard);
            break;
            case 'r': piece = new Rook(pc, coord, newBoard);
            break;
            case 'b': piece = new Bishop(pc, coord, newBoard);
            break;
            case 'n': piece = new Knight(pc, coord, newBoard);
            break;
            default: piece = new Queen(pc, coord, newBoard);
        }
        newBoard.putPiece(piece, coord);
       
    }


}
