package florinczi.projects.chessgame.util;
import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.pieces.*;

public class BoardUtil {

    private BoardUtil() {
        throw new IllegalStateException("Utility class");
      }
   
    

    public static void newGameStd(Board board) {

        board.setNowPlaying(WHITE);
        
            
        for (int i = 1; i <= 8; i++){
            new Pawn(BLACK, new Coordinates(i, 7), board);
            new Pawn(WHITE, new Coordinates(i, 2), board);
        }
        new Rook(BLACK, new Coordinates(1, 8), board);
        new Knight(BLACK, new Coordinates(2, 8), board);
        new Bishop(BLACK, new Coordinates(3, 8), board);
        new Queen(BLACK, new Coordinates(4, 8), board);
        board.setBlackKing(new King(BLACK, new Coordinates(5, 8), board));
        new Bishop(BLACK, new Coordinates(6, 8), board);
        new Knight(BLACK, new Coordinates(7, 8), board);
        new Rook(BLACK, new Coordinates(8, 8), board);
        
        new Rook(WHITE, new Coordinates(1, 1), board);
        new Knight(WHITE, new Coordinates(2, 1), board);
        new Bishop(WHITE, new Coordinates(3, 1), board);
        new Queen(WHITE, new Coordinates(4, 1), board);
        board.setWhiteKing(new King(WHITE, new Coordinates(5, 1), board));
        new Bishop(WHITE, new Coordinates(6, 1), board);
        new Knight(WHITE, new Coordinates(7, 1), board);
        new Rook(WHITE, new Coordinates(8, 1), board);
    }

    public static void checkmateTest(Board board) {

        board.setNowPlaying(WHITE);
        
            
        for (int i = 1; i <= 8; i++){
            new Pawn(BLACK, new Coordinates(i, 7), board);
            new Pawn(WHITE, new Coordinates(i, 2), board);
        }
        new Rook(BLACK, new Coordinates(1, 8), board);
        new Knight(BLACK, new Coordinates(2, 8), board);
        new Bishop(BLACK, new Coordinates(3, 8), board);
        new Queen(BLACK, new Coordinates(4, 8), board);
        board.setBlackKing(new King(BLACK, new Coordinates(5, 8), board));
        new Bishop(BLACK, new Coordinates(6, 8), board);
        new Knight(BLACK, new Coordinates(7, 8), board);
        new Rook(BLACK, new Coordinates(8, 8), board);
        
        new Rook(WHITE, new Coordinates(1, 1), board);
        new Knight(WHITE, new Coordinates(2, 1), board);
        new Bishop(WHITE, new Coordinates(2, 3), board);
        new Queen(WHITE, new Coordinates(6, 3), board);
        board.setWhiteKing(new King(WHITE, new Coordinates(5, 1), board));
        new Bishop(WHITE, new Coordinates(6, 1), board);
        new Knight(WHITE, new Coordinates(7, 1), board);
        new Rook(WHITE, new Coordinates(8, 1), board);
    }


    public static void pawnPromotionCaptureTest(Board board) {

        board.setNowPlaying(WHITE);
            
        for (int i = 1; i <= 8; i++){
            new Pawn(BLACK, new Coordinates(i, 2), board);
            new Pawn(WHITE, new Coordinates(i, 7), board);
        }
        new Pawn (WHITE, new Coordinates(1, 6), board);
        new Rook(BLACK, new Coordinates(1, 8), board);
        new Knight(BLACK, new Coordinates(2, 8), board);
        new Bishop(BLACK, new Coordinates(3, 8), board);
        new Queen(BLACK, new Coordinates(4, 8), board);
        new King(BLACK, new Coordinates(5, 8), board);
        new Bishop(BLACK, new Coordinates(6, 8), board);
        new Knight(BLACK, new Coordinates(7, 8), board);
        new Rook(BLACK, new Coordinates(8, 8), board);
        
        new Rook(WHITE, new Coordinates(1, 1), board);
        new Knight(WHITE, new Coordinates(2, 1), board);
        new Bishop(WHITE, new Coordinates(3, 1), board);
        new Queen(WHITE, new Coordinates(4, 1), board);
        new King(WHITE, new Coordinates(5, 1), board);
        new Bishop(WHITE, new Coordinates(6, 1), board);
        new Knight(WHITE, new Coordinates(7, 1), board);
        new Rook(WHITE, new Coordinates(8, 1), board);
    }

    public static void castlingTest(Board board) {

        board.setNowPlaying(WHITE);
        
        new Rook(BLACK, new Coordinates(1, 8), board);
        new King(BLACK, new Coordinates(5, 8), board);
        new Bishop(BLACK, new Coordinates(6, 8), board);
        new Knight(BLACK, new Coordinates(7, 8), board);
        new Rook(BLACK, new Coordinates(8, 8), board);
        new Rook(WHITE, new Coordinates(1, 1), board);
        new King(WHITE, new Coordinates(5, 1), board);
        new Queen(WHITE, new Coordinates(4, 6), board);
        new Rook(WHITE, new Coordinates(8, 1), board);
    }

    public static void enPassantTest(Board board) {

        board.setNowPlaying(WHITE);
        
        for (int i = 1; i <= 8; i++){
            new Pawn(WHITE, new Coordinates(i, 2), board);
            if (i%2 == 0)
                continue;
            new Pawn(BLACK, new Coordinates(i, 4), board);
        }

    }


}
