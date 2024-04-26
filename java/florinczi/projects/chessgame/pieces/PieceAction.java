package florinczi.projects.chessgame.pieces;

import java.util.List;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;

public interface PieceAction {

    boolean isValidMove(MoveCandidate coordinates);
    List<MoveCandidate> checkPossibleMoves();
    void movePiece(MoveCandidate move, Board newBoard);
    Piece clone(Coordinates coord, Board newBoard);
     
    

}
