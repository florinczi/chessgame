package florinczi.projects.chessgame.pieces;

import java.util.List;
import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;

public class Knight extends Piece{

    public Knight(PlayerColor player, Board activeBoard) {
        super(player, activeBoard);
        if (player == PlayerColor.BLACK) super.setShortType('n');
        else super.setShortType('N');
    }

  

    @Override
    public List<Coordinates> checkPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkPossibleMoves'");
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
