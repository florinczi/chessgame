package florinczi.projects.chessgame.pieces;

import java.util.List;
import java.util.Set;

import florinczi.projects.chessgame.Coordinates;

public interface PieceAction {

    Coordinates isValidMove(Coordinates coordinates);
    List<Coordinates> checkPossibleMoves();
    void move();

}
