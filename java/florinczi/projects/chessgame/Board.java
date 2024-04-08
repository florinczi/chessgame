package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.*;
import static florinczi.projects.chessgame.pieces.PlayerColor.*;

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
        return boardmap.get(coord) == null;
    }
    

    boolean whiteCastled = false;
    boolean blackCastled = false;
    PlayerColor nowPlaying = WHITE;


    public Board() {   //(int iteration, int depth)

        boardmap = new HashMap<>();

        for (int i = 1; i <= 8; i++){
            new Pawn(BLACK, new Coordinates(i, 7), this);
            new Pawn(WHITE, new Coordinates(i, 2), this);
        }
        // Populate the board with other pieces
        // Black pieces
        putPiece(new Rook(BLACK, this), new Coordinates(1, 8));
        putPiece(new Knight(BLACK, this), new Coordinates(2, 8));
        putPiece(new Bishop(BLACK, this), new Coordinates(3, 8));
        putPiece(new Queen(BLACK, this), new Coordinates(4, 8));
        putPiece(new King(BLACK, this), new Coordinates(5, 8));
        putPiece(new Bishop(BLACK, this), new Coordinates(6, 8));
        putPiece(new Knight(BLACK, this), new Coordinates(7, 8));
        putPiece(new Rook(BLACK, this), new Coordinates(8, 8));

        // White pieces
        putPiece(new Rook(WHITE, this), new Coordinates(1, 1));
        putPiece(new Knight(WHITE, this), new Coordinates(2, 1));
        putPiece(new Bishop(WHITE, this), new Coordinates(3, 1));
        putPiece(new Queen(WHITE, this), new Coordinates(4, 1));
        putPiece(new King(WHITE, this), new Coordinates(5, 1));
        putPiece(new Bishop(WHITE, this), new Coordinates(6, 1));
        putPiece(new Knight(WHITE, this), new Coordinates(7, 1));
        putPiece(new Rook(WHITE, this), new Coordinates(8, 1));

    }

    public void putPiece (Piece piece, Coordinates coord){ 
        /* This method creates a new piece on designated coordinates on the HashMap.
         * Then it uses the same Coordinates object as the piece argument.
         * This way when Coordinates is modified, both the Piece and the Board are aware of it.
         */
        boardmap.put(coord, piece);
          
    }

    public Piece getPiece(Coordinates coord){
        
        return boardmap.get(coord);
    }
    
    public void movePiece(Coordinates coord){
        int moveIndex;
        Piece piece = getPiece(coord);
        

    }
    


}
