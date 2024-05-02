package florinczi.projects.chessgame.util;

public class EnPassant {
    Coordinates actualPawn;
    Coordinates ghostPawn;

    public Coordinates getActualPawn() {
        return actualPawn;
    }

    public Coordinates getGhostPawn() {
        return ghostPawn;
    }

    public EnPassant (MoveCandidate mc){
        actualPawn = new Coordinates(mc.getDestination());
        ghostPawn = new Coordinates(mc.getCoord());
        if (mc.getVector().getY() > 0)
            ghostPawn.setY(3);
        else
            ghostPawn.setY(6);

    }

}
