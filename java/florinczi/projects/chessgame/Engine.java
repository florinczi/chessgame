package florinczi.projects.chessgame;
import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import florinczi.projects.chessgame.pieces.*;
import florinczi.projects.chessgame.util.BoardUtil;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;

public class Engine {

    private Board mainBoard;
    private Menu menu;
    private CheckChecker checkChecker;
    private boolean whitePlayerAI = false;
    private boolean blackPlayerAI = false;
    private Collection <MoveCandidate> possibleMoves;
    public boolean checkmate;
    public boolean stalemate;
    
    
    public CheckChecker getCheckChecker() {
        return checkChecker;
    }

    public Menu getMenu() {
        return menu;
    }

    public Engine(Menu menu) {
        this.possibleMoves = new ArrayList<>();
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
            default:
                break;
        }
        
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

    public Board prepareMove(Board board, MoveCandidate moveCandidate){ //This method checks if the move is valid
                
        Piece piece = board.getPiece(moveCandidate.getCoord());
        PieceAction pa = piece;
                
        if (piece == null){
            System.out.println("No piece on this coordinates");
            return null;
        }
    
        if (piece.getPlayer() != board.getNowPlaying()){
            System.out.println("Wrong player");
            return null;
        }
        if (!possibleMoves.contains(moveCandidate)){
            System.out.println("Invalid move");
            return null;
        }
        Board testBoard = new Board(board);
        piece.movePiece(moveCandidate, testBoard); //move it with MoveCandidate on testBoard   
        piece.setActiveBoard(testBoard);
        return testBoard;
    }



    public Collection <MoveCandidate> genBoardMoves(Board board){
        List <MoveCandidate> list = new ArrayList<>();
        board.getBoardmap().forEach((k, v) -> list.addAll(v.checkPossibleMoves()));
        return list;
    }

    public Collection <Board> genNextBoards(Board currentBoard, Collection <MoveCandidate> boardMoves){
        List <Board> nextBoards = new ArrayList<>();
        boardMoves.forEach(move -> {
            Board newBoard = new Board(currentBoard);
            currentBoard.getPiece(move.getCoord()).movePiece(move, newBoard);
            if (newBoard.isInCheck()) 
                boardMoves.remove(move);
            else
                nextBoards.add(newBoard);
        });
        return nextBoards;
    }

    public boolean nextTurn(){
        mainBoard.changePlayers();
        if ((mainBoard.getNowPlaying() == WHITE && whitePlayerAI) || (mainBoard.getNowPlaying() == BLACK && blackPlayerAI))  {
            // TODO here is entry point for AI takeover
        }
        else{ //this is when it's human's turn
            possibleMoves = genBoardMoves(mainBoard);
            cullCheckMoves();
            if (hasTheGameEnded()) 
                return false;
            movePieceHuman(getMenu().getPlayerMove());
        }
        return true;
    }

    private boolean hasTheGameEnded (){
        if (possibleMoves.isEmpty()){
            if (checkChecker.checkChecks(mainBoard))
                checkmate = true;
            else
                stalemate = true;
            return true;
        } 
        return false;
    }


    

    public void cullCheckMoves(){
        for (MoveCandidate ms: possibleMoves){
            Board testBoard = new Board(getMainBoard());
            testBoard.getPiece(ms.getCoord()).movePiece(ms, testBoard);
            if (getMainBoard().getEngine().getCheckChecker().checkChecks(testBoard))
                possibleMoves.remove(ms);
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
