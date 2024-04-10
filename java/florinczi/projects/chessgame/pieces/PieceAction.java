package florinczi.projects.chessgame.pieces;

import java.util.List;
import florinczi.projects.chessgame.MoveCandidate;

public interface PieceAction {

    boolean isValidMove(MoveCandidate coordinates);
    List<MoveCandidate> checkPossibleMoves();
    
    

}
