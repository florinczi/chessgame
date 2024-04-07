package florinczi.projects.chessgame.pieces;

import java.util.Set;

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
    Set<Coordinates> possibleMoves;
    public Board getActiveBoard() {
        return activeBoard;
    }

    public Coordinates calculateVector(Coordinates destCoordinates){
        int x;
        int y;
               
        x = destCoordinates.getX() - getLocation().getX();
        y = destCoordinates.getY() -getLocation().getY();
        return new Coordinates(getLocation(), x, y);
    }

    public boolean isValidMove(Coordinates coordinates){
        return possibleMoves.contains(coordinates);
    }
}
