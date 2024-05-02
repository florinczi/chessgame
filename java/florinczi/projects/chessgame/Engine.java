package florinczi.projects.chessgame;
import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import florinczi.projects.chessgame.pieces.*;
import florinczi.projects.chessgame.util.BoardUtil;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;

public class Engine {

    private Board mainBoard;
    private Menu menu;
    CheckChecker checkChecker;
    private boolean whitePlayerAI = false;
    private boolean blackPlayerAI = false;
    private Collection <MoveCandidate> possibleMoves;
    boolean checkmate;
    private RootNode aiRootNode;
    public boolean isCheckmate() {
        return checkmate;
    }

    public boolean isStalemate() {
        return stalemate;
    }


    boolean stalemate;
    
    
    public CheckChecker getCheckChecker() {
        return checkChecker;
    }

    public Menu getMenu() {
        return menu;
    }

    public Engine(Menu menu) {
        this.possibleMoves = new HashSet<>();
        this.menu = menu;
        this.checkmate = false;
        this.stalemate = false;
    }

    public void setMainBoard(Board mainBoard) {
        this.mainBoard = mainBoard;
        checkChecker = new CheckChecker();
    }

    public Board getMainBoard() {
        return mainBoard;
    }
   
    public void newGame(int choice){
        switch (choice) {
            case 1:
                setMainBoard(new Board(this));
                BoardUtil.newGameStd(mainBoard);
                break;
            case 2:
                setMainBoard(new Board(this));
                BoardUtil.pawnPromotionCaptureTest(mainBoard);
                break;
            case 3:
                setMainBoard(new Board(this));
                BoardUtil.castlingTest(mainBoard);
                break;
            case 4:
                setMainBoard(new Board(this));
                BoardUtil.enPassantTest(mainBoard);
                break;
            case 5:
                setMainBoard(new Board(this));
                BoardUtil.checkmateTest(mainBoard);
                break;
            case 6:
                setMainBoard(new Board(this));
                BoardUtil.newGameStd(mainBoard);
                blackPlayerAI = true;
                break;    
            default:
                break;
        }
        
    }

    public boolean movePieceHuman(MoveCandidate moveCandidate){       
        Board testBoard = prepareMove(getMainBoard(), moveCandidate);
        if (testBoard == null) //movePiece returns null when invalid move has been passed
            return false;
        
        mainBoard = testBoard;
        if (mainBoard.getPiece(moveCandidate.getDestination()) instanceof Pawn && (moveCandidate.getDestination().getY() == 8 || moveCandidate.getDestination().getY() == 1)){
            ((Pawn)mainBoard.getPiece(moveCandidate.getDestination())).promoteHuman();
            }
        return true;
        
    }

    public Board prepareMove(Board board, MoveCandidate moveCandidate){ //This method checks if the move is valid
                
        Piece piece = board.getPiece(moveCandidate.getCoord());
        int index;
        

        if (piece == null){
            System.out.println("No piece on this coordinates");
            return null;
        }
    
        if (piece.getPlayer() != board.getNowPlaying()){
            System.out.println("Wrong player");
            return null;
        }
        index = board.getMoveList().indexOf(moveCandidate);
        if (index == -1){
            System.out.println("Invalid move");
            return null;
        }
        moveCandidate = board.getMoveList().get(index); // this line makes it so movecandidate contains original SpecialMove flag generated by Pieces

        Board testBoard = new Board(board);
        piece.movePiece(moveCandidate, testBoard); //move it with MoveCandidate on testBoard   
        piece.setActiveBoard(testBoard);
        return testBoard;
    }



    public void genBoardMoves(Board board){
        List <MoveCandidate> whiteList = new ArrayList<>();
        List <MoveCandidate> blackList = new ArrayList<>();
        board.getBoardmap().forEach((k, v) -> {
            if (v.getPlayer() == WHITE)
                whiteList.addAll(v.checkPossibleMoves());
            else
                blackList.addAll(v.checkPossibleMoves());
        });
        cullCheckMoves(blackList);
        cullCheckMoves(whiteList);
        board.setMoveList(whiteList, WHITE);       
        board.setMoveList(blackList, BLACK);
    }
 
    public boolean nextTurn(){
        
        if ((mainBoard.getNowPlaying() == WHITE && whitePlayerAI) || (mainBoard.getNowPlaying() == BLACK && blackPlayerAI))  {
            aiRootNode = new RootNode(mainBoard);
            mainBoard = aiRootNode.minmaxRoot();
            mainBoard.changePlayers();
            
        }
        else{ //this is when it's human's turn
            if (checkChecker.hasTheGameEnded(mainBoard)){ 
                checkmate = checkChecker.isCheckmate();
                stalemate = checkChecker.isStalemate();
                return false;
            }
            if(movePieceHuman(getMenu().getPlayerMove()))
                mainBoard.changePlayers();
        }
        return true;
    }

    public void cullCheckMoves(Collection <MoveCandidate> moveSet){
        Collection <MoveCandidate> clonedPossibleMoves = new HashSet<>(moveSet);

        for (MoveCandidate move: clonedPossibleMoves){
            
            Board testBoard = new Board(getMainBoard());
            testBoard.getPiece(move.getCoord()).movePiece(move, testBoard);
            if (getMainBoard().getEngine().getCheckChecker().checkChecks(testBoard))
                moveSet.remove(move);
        }            
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
