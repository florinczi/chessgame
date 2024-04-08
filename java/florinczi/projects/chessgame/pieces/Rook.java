package florinczi.projects.chessgame.pieces;

import java.util.List;
import java.util.Set;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;

public class Rook extends Piece{

    public Rook(PlayerColor player, Board activeBoard) {
        super(player, activeBoard);
        if (player == PlayerColor.BLACK) super.setShortType('r');
        else super.setShortType('R');
    }

    @Override
    public List<Coordinates> checkPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

}
