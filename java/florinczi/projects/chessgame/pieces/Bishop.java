package florinczi.projects.chessgame.pieces;

import java.util.List;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;

public class Bishop extends Piece{

    public Bishop(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine);
        if (player == PlayerColor.BLACK) super.setShortType('b');
        else super.setShortType('B');
    }

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

   
    

}
