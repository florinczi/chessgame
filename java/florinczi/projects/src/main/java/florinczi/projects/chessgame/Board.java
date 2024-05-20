package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.*;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.EnPassant;
import florinczi.projects.chessgame.util.MoveCandidate;

import static florinczi.projects.chessgame.pieces.PlayerColor.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Board {
      
    private Map<Coordinates, Piece> boardmap;
    private King blackKing;
    private King whiteKing;
    private PlayerColor nowPlaying;
    private Engine engine;
    private EnPassant enPassant;
    private float evaluation;
    private List<MoveCandidate> whiteMoveList;
    private List <MoveCandidate> blackMoveList;
    private int turn;
    
    
  
    
    public int getTurn() {
        return turn;
    }
    public List<MoveCandidate> getMoveList(PlayerColor color) {
        if (whiteMoveList == null)
            getEngine().genBoardMoves(this);
        if (color == WHITE) 
            return whiteMoveList;    
        else
            return blackMoveList;
    }
    public List<MoveCandidate> getMoveList() {
        return getMoveList(nowPlaying);
    }

    public void setMoveList (List<MoveCandidate> list, PlayerColor color){
        if (color == WHITE)
            whiteMoveList = list;
        else    
            blackMoveList = list;
    }

  

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    public EnPassant getEnPassant() {
        return enPassant;
    }
 
    public void setEnPassant(MoveCandidate move, int turn) {
        enPassant = new EnPassant(move, turn);    
    }

    public boolean isInCheck(){
        return getEngine().getCheckChecker().checkChecks(this);
    }

    public Board(Engine engine) { 
        this.engine = engine;
        boardmap = new HashMap<>();
        this.evaluation = 0f;
        this.turn = 1;
    }

    public Board (Board board) {
        this.evaluation = 0f;
    
        this.boardmap = new HashMap<>(board.boardmap);
        this.nowPlaying = board.nowPlaying;
        this.engine = board.engine;
        this.setBlackKing(board.getBlackKing());
        this.setWhiteKing(board.getWhiteKing());
        this.turn = getTurn() + 1; 
        if (board.getEnPassant() != null )
            this.enPassant = board.getEnPassant();

    }

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
        
    public void putPiece (Piece piece, Coordinates coord){ 
               boardmap.put(coord, piece);
          
    }

    public void putClonedPiece (PieceAction pieceaction, Coordinates coord){ 
        if (getBoardmap().containsKey(coord))
            getBoardmap().replace(coord, pieceaction.clone(coord, this));
        else
            getBoardmap().put(coord, pieceaction.clone(coord, this));   
    }
  
    public Piece getPiece(Coordinates coord){
        return boardmap.get(coord);
    }
    public Piece removePiece(Coordinates coord){
        return boardmap.remove(coord);
    }
    
    public void changePlayers(){
        if(getNowPlaying() == WHITE){
            setNowPlaying(BLACK);
        }
        else
            setNowPlaying(WHITE);        
    }
}
