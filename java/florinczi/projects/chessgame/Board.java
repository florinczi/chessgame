package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.*;
import static florinczi.projects.chessgame.pieces.PlayerColor.*;
import static florinczi.projects.chessgame.pieces.SpecialMoves.*;
import java.util.HashMap;
import java.util.Map;
public class Board {

    

    
    private Map<Coordinates, Piece> boardmap;
   

    public char printSquare(int x, int y) {
        Coordinates coord = new Coordinates(x, y);
        if (boardmap.get(coord) == null) 
            return '.';
        return boardmap.get(coord).getShortType();
    }

    public boolean isSquareFree(Coordinates coord){
        return (!boardmap.containsKey(coord));
    }
    

    private boolean whiteCastled;
    private boolean blackCastled;
    private PlayerColor nowPlaying;
    private Engine engine;


    public boolean isWhiteCastled() {
        return whiteCastled;
    }

    public Map<Coordinates, Piece> getBoardmap() {
        return boardmap;
    }

    public void setWhiteCastled(boolean whiteCastled) {
        this.whiteCastled = whiteCastled;
    }

    public boolean isBlackCastled() {
        return blackCastled;
    }

    public void setBlackCastled(boolean blackCastled) {
        this.blackCastled = blackCastled;
    }

    public PlayerColor getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(PlayerColor nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public Board(Engine engine) {   //(int iteration, int depth)

        this.engine = engine;
        boardmap = new HashMap<>();

    }

    public void newGame() {
        whiteCastled = false;
        blackCastled = false;
        nowPlaying = WHITE;
        
        


        for (int i = 1; i <= 8; i++){
            new Pawn(BLACK, new Coordinates(i, 7), engine);
            new Pawn(WHITE, new Coordinates(i, 2), engine);
        }
        
        // // Black pieces
        
        new Rook(BLACK, new Coordinates(1, 8), engine);
        new Knight(BLACK, new Coordinates(2, 8), engine);
        new Bishop(BLACK, new Coordinates(3, 8), engine);
        new Queen(BLACK, new Coordinates(4, 8), engine);
        new King(BLACK, new Coordinates(5, 8), engine);
        new Bishop(BLACK, new Coordinates(6, 8), engine);
        new Knight(BLACK, new Coordinates(7, 8), engine);
        new Rook(BLACK, new Coordinates(8, 8), engine);
        
        // // White pieces
        new Rook(WHITE, new Coordinates(1, 1), engine);
        new Knight(WHITE, new Coordinates(2, 1), engine);
        new Bishop(WHITE, new Coordinates(3, 1), engine);
        new Queen(WHITE, new Coordinates(4, 1), engine);
        new King(WHITE, new Coordinates(5, 1), engine);
        new Bishop(WHITE, new Coordinates(6, 1), engine);
        new Knight(WHITE, new Coordinates(7, 1), engine);
        new Rook(WHITE, new Coordinates(8, 1), engine);
    }
    

    public Board (Board board)
    {
        this.boardmap = new HashMap<>(board.boardmap);
        this.blackCastled = board.whiteCastled;
        this.whiteCastled = board.whiteCastled;
        this.nowPlaying = board.nowPlaying;
        this.engine = board.engine;
    }

    public void putPiece (Piece piece, Coordinates coord){ 
               boardmap.put(coord, piece);
          
    }

    public Piece getPiece(Coordinates coord){
        return boardmap.get(coord);
    }

    public PlayerColor isKingInCheck(){
        return null; //TODO
    }
    
    public Board movePiece(MoveCandidate moveCandidate){
        Coordinates coord = moveCandidate.getCoord();
       
        Piece piece = getPiece(moveCandidate.getCoord());
        PieceAction pa = piece;
        
        
        if (piece == null){
            System.out.println("No piece on this coordinates");
            return null;
        }
        pa.checkPossibleMoves();

        if (piece.getPlayer() != getNowPlaying())
            return null;
        if (!piece.isValidMove(moveCandidate))
            return null;

        Board testBoard = new Board(this);
        
        testBoard.boardmap.remove(moveCandidate.getCoord());
        moveCandidate.addVector();
        if (moveCandidate.getSpecialMove() == CAPTURE) {
            testBoard.boardmap.replace(coord, piece);
        }
        else    
            testBoard.boardmap.put(coord, piece); 
        if (testBoard.isKingInCheck() != null){
            //TODO check checks
        }
        piece.setLocation(coord);
        return testBoard;
    }
    
    public void changePlayers(){
        if(getNowPlaying() == WHITE){
            setNowPlaying(BLACK);
        }
        else
            setNowPlaying(WHITE);
        
    }


}
