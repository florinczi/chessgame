package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.*;
import static florinczi.projects.chessgame.pieces.PlayerColor.*;
import static florinczi.projects.chessgame.pieces.SpecialMoves.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class Board {

    

    
    private Map<Coordinates, Piece> boardmap;
    private List<MoveCandidate> moveList;
    private King blackKing;
    private King whiteKing;
    private PlayerColor nowPlaying;
    private Engine engine;

   

    public Engine getEngine() {
        return engine;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(King blackKing) {
        this.blackKing = blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(King whiteKing) {
        this.whiteKing = whiteKing;
    }

    public char printSquare(int x, int y) {
        Coordinates coord = new Coordinates(x, y);
        if (boardmap.get(coord) == null) 
            return '.';
        return boardmap.get(coord).getShortType();
    }

    public boolean isSquareFree(Coordinates coord){
        return (!boardmap.containsKey(coord));
    }
    


    public Map<Coordinates, Piece> getBoardmap() {
        return boardmap;
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
        moveList = new ArrayList<>();

    }
   
    public List <MoveCandidate> genMoves(){
        List <MoveCandidate> boardMoveList = new ArrayList<>();
        for (PieceAction p: getBoardmap().values()){
            boardMoveList.addAll(p.checkPossibleMoves());
        }
        return boardMoveList;
    }

    public void newGame() {
       
        nowPlaying = WHITE;
            
        for (int i = 1; i <= 8; i++){
            new Pawn(BLACK, new Coordinates(i, 7), this);
            new Pawn(WHITE, new Coordinates(i, 2), this);
        }
        
        new Rook(BLACK, new Coordinates(1, 8), this);
        new Knight(BLACK, new Coordinates(2, 8), this);
        new Bishop(BLACK, new Coordinates(3, 8), this);
        new Queen(BLACK, new Coordinates(4, 8), this);
        new King(BLACK, new Coordinates(5, 8), this);
        new Bishop(BLACK, new Coordinates(6, 8), this);
        new Knight(BLACK, new Coordinates(7, 8), this);
        new Rook(BLACK, new Coordinates(8, 8), this);
        
        new Rook(WHITE, new Coordinates(1, 1), this);
        new Knight(WHITE, new Coordinates(2, 1), this);
        new Bishop(WHITE, new Coordinates(3, 1), this);
        new Queen(WHITE, new Coordinates(4, 1), this);
        new King(WHITE, new Coordinates(5, 1), this);
        new Bishop(WHITE, new Coordinates(6, 1), this);
        new Knight(WHITE, new Coordinates(7, 1), this);
        new Rook(WHITE, new Coordinates(8, 1), this);
    }
    

    public Board (Board board)
    {
        this.boardmap = new HashMap<>(board.boardmap);
        this.nowPlaying = board.nowPlaying;
        this.engine = board.engine;
        
    }

    public void putPiece (Piece piece, Coordinates coord){ 
               boardmap.put(coord, piece);
          
    }

    public void putClonedPiece (PieceAction piece, Coordinates coord, Board newBoard){ 
        
        getBoardmap().put(coord, piece.clone(coord, newBoard));
   
    }

    public void replaceWClonedPiece (PieceAction pieceaction, Coordinates coord, Board newBoard){ 
        
        getBoardmap().replace(coord, pieceaction.clone(coord, newBoard));
   
    }

    public Piece getPiece(Coordinates coord){
        return boardmap.get(coord);
    }
    
    public Board prepareMove(MoveCandidate moveCandidate){
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
        
        piece.movePiece(moveCandidate, testBoard); //move it with MoveCandidate on testBoard
              
    
        
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
