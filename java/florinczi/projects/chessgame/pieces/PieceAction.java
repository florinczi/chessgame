package florinczi.projects.chessgame.pieces;

import java.util.Set;

import florinczi.projects.chessgame.Coordinates;

public interface PieceAction {

    boolean isValidMove(Coordinates coordinates);
    Set<Coordinates> checkPossibleMoves();
    void move();

}
