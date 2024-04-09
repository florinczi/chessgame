package florinczi.projects.chessgame.pieces;

import java.util.List;
import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;

public class Knight extends Piece{

    public Knight(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine);
        if (player == PlayerColor.BLACK) super.setShortType('n');
        else super.setShortType('N');
    }

  

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkPossibleMoves'");
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
