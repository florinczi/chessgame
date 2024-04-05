package florinczi.projects.chessgame.pieces;

import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;

public class Bishop extends Piece{

    public Bishop(PlayerColor player, Board activeBoard) {
        super(player, activeBoard);
        if (player == PlayerColor.BLACK) super.setShortType('b');
        else super.setShortType('B');
    }

    @Override
    public Set<Coordinates> checkPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isValidMove(Coordinates coordinates) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

}
