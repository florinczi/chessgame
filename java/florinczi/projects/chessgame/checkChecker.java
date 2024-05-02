package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;
import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.Collection;

import florinczi.projects.chessgame.pieces.*;
import florinczi.projects.chessgame.util.Coordinates;
import florinczi.projects.chessgame.util.MoveCandidate;
import florinczi.projects.chessgame.util.Vector;

public class CheckChecker {



   /*
    Can be further enhanced by limiting the checks according to pieces actually onboard 


    */


   public CheckChecker(){
      this.king = new Coordinates();
      this.probe = new Coordinates();
      this.vector = new Vector();
      checkmate = false;
      stalemate = false;
   }

   private Board activeBoard;
   private PlayerColor checkedPlayer;
   private Vector vector;
   private Coordinates probe;
   private Coordinates king;
   private boolean checkmate;
   private boolean stalemate;

   public boolean isCheckmate() {
      return checkmate;
   }

   public boolean isStalemate() {
      return stalemate;
   }

   public boolean checkChecks (Coordinates coord, PlayerColor targetColor, Board board){ //is the target checked?
      
      king.set(coord);
      checkedPlayer = targetColor;
      activeBoard = board;
      if (checkKnightChecks())
         return true;
      if (checkPawnChecks())
         return true;
      if (checkLinesAndDiags())
         return true;

    return false;
   }

   public boolean checkChecks (Board board){ 
      checkedPlayer = board.getNowPlaying();
      

      if (checkedPlayer == WHITE)
         king.set(board.getWhiteKing().getLocation());
      else
         king.set(board.getBlackKing().getLocation());

      activeBoard = board;
      if (checkKnightChecks())
         return true;
      if (checkPawnChecks())
         return true;
      if (checkLinesAndDiags())
         return true;

    return false;
   }

   private boolean checkKnightChecks(){    
      vector.set(-1, 2); //initial vector
      
         for (int i = 0; i < 8; i++){
               probe.set(king);
      
            if (probe.isValidVector(vector)){ //bounds check
               probe.addVector(vector);
               
               if (activeBoard.getPiece(probe) instanceof Knight && activeBoard.getPiece(probe).getPlayer() != checkedPlayer)
                  return true;                                  
               }
      
               vector.rotate();
               if (i == 3)
                  vector.setX(vector.getX() * -1); //after 4 rotations, mirror the vector
         }
         return false;
      }

   private boolean checkPawnChecks(){
      int direction = 1;
      if (checkedPlayer == BLACK)
         direction = -1;
   
      probe.set(king);
      vector.set(-1, direction);
   
      if (probe.isValidVector(vector)){ //bounds check
         probe.addVector(vector);
         if (activeBoard.getPiece(probe) instanceof Pawn && activeBoard.getPiece(probe).getPlayer() != checkedPlayer)
            return true;
   
      }
      
      probe.set(king);
      vector.set(1, direction);
   
      if (probe.isValidVector(vector)){ //bounds check
         probe.addVector(vector);
         if (activeBoard.getPiece(probe) instanceof Pawn && activeBoard.getPiece(probe).getPlayer() != checkedPlayer)
            return true;
   
      }
      
      return false;
   }

   private boolean checkLineMove(Vector vector){

      boolean collision = false;
      int range = 0;
      while (!collision){ //incrementing and adding moves until a Piece detected
         probe.set(king);
         if (!probe.isValidVector(vector))
            return false;
         probe.addVector(vector);
         range++;

         if (activeBoard.isSquareFree(probe))
            vector.incrementDirection();
         else{
            collision = true;
            if(range < 2 && activeBoard.getPiece(probe) instanceof King && activeBoard.getPiece(probe).getPlayer() != checkedPlayer){ //this checks only in range of 1
            return true;  
            }         
         }
      }  

      if(activeBoard.getPiece(probe).getPlayer() != checkedPlayer){
         if (Math.abs(vector.getX()) != Math.abs(vector.getY())) // checks if the move is orthogonal
            return (activeBoard.getPiece(probe) instanceof Queen || activeBoard.getPiece(probe) instanceof Rook); //if so, only queen and rook are the danger, as we've already checked for king
         else
            return (activeBoard.getPiece(probe) instanceof Queen || activeBoard.getPiece(probe) instanceof Bishop); //this is the case for diagonal movement

      }
      return false;
   }

   private boolean checkLinesAndDiags(){

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) { //iterate through all directions
             if (i == 0 && j == 0) continue; // Skip the king's own position
             vector.set(i, j);
             if (checkLineMove(vector)) //check this direction
                  return true;
         }
     }
     return false;
   }

public boolean hasTheGameEnded (Board board){
   checkmate = false;
   stalemate = false;
    if (board.getMoveList().isEmpty()){
        if (checkChecks(board))
            checkmate = true;
        else
            stalemate = true;
        return true;
    } 
    return false;
}
}
