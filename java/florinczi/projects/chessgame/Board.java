package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.*;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;

import static florinczi.projects.chessgame.pieces.PlayerColor.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Board {
      
    private Map<Coordinates, Piece> boardmap;
    private King blackKing;
    private King whiteKing;
    private PlayerColor nowPlaying;
    private Engine engine;
    private Coordinates enPassant;
    private PlayerColor enPassColor;
    private float evaluation;
    private Collection <MoveCandidate> moveList;
    
    
  

    public Collection<MoveCandidate> getMoveList() {
        if (moveList == null)
            moveList = getEngine().genBoardMoves(this);
        return moveList;
    }

    public void setMoveList(List<MoveCandidate> moveList) {
        this.moveList = moveList;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    public Coordinates getEnPassant() {
        return enPassant;
    }

    public PlayerColor getEnPassColor() {
        return enPassColor;
    }

    public void setEnPassant(Coordinates coord, PlayerColor color) {
        enPassant = new Coordinates(coord);
        enPassColor = color;
        if (enPassColor == BLACK)
            enPassant.setY(enPassant.getY() - 1);
        else   
            enPassant.setY(enPassant.getY() + 1);
    }

    public boolean isInCheck(){
        return getEngine().getCheckChecker().checkChecks(this);
    }

    public Board(Engine engine) { 
        this.engine = engine;
        boardmap = new HashMap<>();
        enPassant = new Coordinates();
        this.evaluation = 0f;
    }

    public Board (Board board) {
        this.evaluation = 0f;
    
        this.boardmap = new HashMap<>(board.boardmap);
        this.nowPlaying = board.nowPlaying;
        this.engine = board.engine;
        this.setBlackKing(board.getBlackKing());
        this.setWhiteKing(board.getWhiteKing());
        enPassant = new Coordinates();        
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

    public List <MoveCandidate> genMoves() {
        List <MoveCandidate> boardMoveList = new ArrayList<>();
        for (PieceAction p: getBoardmap().values()){
            boardMoveList.addAll(p.checkPossibleMoves());
        }
        return boardMoveList;
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
