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
        System.out.println(boardmap.get(coord));
        System.out.println(boardmap.containsKey(coord));
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
        // Populate the board with other pieces
        // Black pieces
        // putPiece(new Rook(BLACK, this), new Coordinates(1, 8));
        // putPiece(new Knight(BLACK, this), new Coordinates(2, 8));
        // putPiece(new Bishop(BLACK, this), new Coordinates(3, 8));
        // putPiece(new Queen(BLACK, this), new Coordinates(4, 8));
        // putPiece(new King(BLACK, this), new Coordinates(5, 8));
        // putPiece(new Bishop(BLACK, this), new Coordinates(6, 8));
        // putPiece(new Knight(BLACK, this), new Coordinates(7, 8));
        // putPiece(new Rook(BLACK, this), new Coordinates(8, 8));

        // // White pieces
        // putPiece(new Rook(WHITE, this), new Coordinates(1, 1));
        // putPiece(new Knight(WHITE, this), new Coordinates(2, 1));
        // putPiece(new Bishop(WHITE, this), new Coordinates(3, 1));
        // putPiece(new Queen(WHITE, this), new Coordinates(4, 1));
        // putPiece(new King(WHITE, this), new Coordinates(5, 1));
        // putPiece(new Bishop(WHITE, this), new Coordinates(6, 1));
        // putPiece(new Knight(WHITE, this), new Coordinates(7, 1));
        // putPiece(new Rook(WHITE, this), new Coordinates(8, 1));
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
