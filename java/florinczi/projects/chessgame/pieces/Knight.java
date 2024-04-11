package florinczi.projects.chessgame.pieces;

import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;

import java.util.ArrayList;
import java.util.List;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;

public class Knight extends Piece{

    public Knight(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine); //adding color and engine ref

        if (player == PlayerColor.BLACK) super.setShortType('n');
        else super.setShortType('N'); // setting "visuals"

        super.setLocation(new Coordinates(location)); //setting inner coords
        engine.getMainBoard().putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location); //init move-probing location
        possibleMoves = new ArrayList<MoveCandidate>(1); //init move list
    
    }

    Coordinates newLocation;
    

    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        setActiveBoard(getEngine().getMainBoard());
        checkHorseyMove();
        return possibleMoves;                
    }

    public void checkHorseyMove(){    
        Vector vector = new Vector(-1, 2);
        
        for (int i = 0; i < 8; i++){
            newLocation.set(getLocation());

            if (newLocation.isValidVector(vector)){ //bounds check

                newLocation.addVector(vector);

                if (getActiveBoard().isSquareFree(newLocation))
                    possibleMoves.add(new MoveCandidate(getLocation(), vector)); // is it capture?
                else
                    possibleMoves.add(new MoveCandidate(getLocation(), vector, CAPTURE));
                                
            }
            vector.rotate();

            if (i == 3)
                vector.setX(vector.getX() * -1); //after 4 rotations, mirror the vector
        }
    }
   

}
