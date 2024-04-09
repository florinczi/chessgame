package florinczi.projects.chessgame.pieces;

import java.util.List;
import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;

public class Rook extends Piece{

    public Rook(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine);
        if (player == PlayerColor.BLACK) super.setShortType('r');
        else super.setShortType('R');
    }

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

}
