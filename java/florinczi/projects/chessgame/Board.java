package florinczi.projects.chessgame;

import florinczi.projects.chessgame.pieces.*;
import static florinczi.projects.chessgame.pieces.PlayerColor.*;

import java.util.HashMap;
import java.util.Map;
public class Board {

    

    
    private Map<Coordinates, Piece> boardmap;

    public char printSquare(int x, int y) {
        Coordinates coord = new Coordinates(x, y);
        if (boardmap.get(coord) == null) return '.';
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

        for (int i = 0; i < 8; i++){
            putPiece(new Pawn(BLACK, this), new Coordinates(6, i));
            putPiece(new Pawn(WHITE, this), new Coordinates(1, i));
        }
        // Populate the board with other pieces
        // Black pieces
        putPiece(new Rook(BLACK, this), new Coordinates(7, 0));
        putPiece(new Knight(BLACK, this), new Coordinates(7, 1));
        putPiece(new Bishop(BLACK, this), new Coordinates(7, 2));
        putPiece(new Queen(BLACK, this), new Coordinates(7, 3));
        putPiece(new King(BLACK, this), new Coordinates(7, 4));
        putPiece(new Bishop(BLACK, this), new Coordinates(7, 5));
        putPiece(new Knight(BLACK, this), new Coordinates(7, 6));
        putPiece(new Rook(BLACK, this), new Coordinates(7, 7));

        // White pieces
        putPiece(new Rook(WHITE, this), new Coordinates(0, 0));
        putPiece(new Knight(WHITE, this), new Coordinates(0, 1));
        putPiece(new Bishop(WHITE, this), new Coordinates(0, 2));
        putPiece(new Queen(WHITE, this), new Coordinates(0, 3));
        putPiece(new King(WHITE, this), new Coordinates(0, 4));
        putPiece(new Bishop(WHITE, this), new Coordinates(0, 5));
        putPiece(new Knight(WHITE, this), new Coordinates(0, 6));
        putPiece(new Rook(WHITE, this), new Coordinates(0, 7));

    }

    private void putPiece (Piece piece, Coordinates coord){ 
        /* This method creates a new piece on designated coordinates on the HashMap.
         * Then it uses the same Coordinates object as the piece argument 
         */
        boardmap.put(coord, piece);
        piece.setLocation(coord);   
    }

    
    
    


}
