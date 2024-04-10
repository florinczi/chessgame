package florinczi.projects.chessgame.pieces;

import java.util.List;
import java.util.Set;

import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.MoveCandidate;

public interface PieceAction {

    boolean isValidMove(MoveCandidate coordinates);
    List<MoveCandidate> checkPossibleMoves();
    
    

}
