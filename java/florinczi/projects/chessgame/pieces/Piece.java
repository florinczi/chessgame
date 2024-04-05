package florinczi.projects.chessgame.pieces;

import florinczi.projects.chessgame.Board;
import florinczi.projects.chessgame.Coordinates;

public abstract class Piece implements PieceAction{

   

   

    protected Piece (PlayerColor player, Board activeBoard){
        this.player = player;
        this.activeBoard = activeBoard;
    }



    private char shortType; // char representation of the piece on the board
  
    public char getShortType() {
        return shortType;
    }

    public void setShortType(char shortType) {
        this.shortType = shortType;
    }


    private Coordinates location; // where is the piece now

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    private PlayerColor player; //whose piece is it

    public PlayerColor getPlayer() {
        return player;
    }

    private Board activeBoard;

    public Board getActiveBoard() {
        return activeBoard;
    }

   

}
