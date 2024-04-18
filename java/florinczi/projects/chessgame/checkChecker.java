package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.BLACK;

import florinczi.projects.chessgame.pieces.Bishop;
import florinczi.projects.chessgame.pieces.King;
import florinczi.projects.chessgame.pieces.Knight;
import florinczi.projects.chessgame.pieces.Pawn;
import florinczi.projects.chessgame.pieces.PlayerColor;
import florinczi.projects.chessgame.pieces.Queen;
import florinczi.projects.chessgame.pieces.Rook;

public class CheckChecker {

   public CheckChecker(){
      this.king = new Coordinates(0,0);
      this.probe = new Coordinates(0,0);
      this.vector = new Vector();
   }

   Board activeBoard;
   PlayerColor checkedPlayer;
   Vector vector;
   Coordinates probe;
   Coordinates king;

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
      while (!collision){ //incrementing and adding moves until a Piece detected
         probe.set(king);
         if (!probe.isValidVector(vector))
            probe.addVector(vector);

         if (activeBoard.isSquareFree(probe))
            vector.incrementDirection();
         else
            collision = true;           
      }
      
      if(activeBoard.getPiece(probe).getPlayer() != checkedPlayer &&
         vector.getX() + vector.getY() % 2 == 0 &&                   //modulo is 0 for moves across files as rank ie not diagonal
         (activeBoard.getPiece(probe) instanceof Queen ||
         activeBoard.getPiece(probe) instanceof Rook ||
         activeBoard.getPiece(probe) instanceof King))
            return true;

      if(activeBoard.getPiece(probe).getPlayer() != checkedPlayer &&
      vector.getX() + vector.getY() % 2 == 1 &&                   //modulo is 1 for diagonal moves
      (activeBoard.getPiece(probe) instanceof Queen ||
      activeBoard.getPiece(probe) instanceof Bishop ||
      activeBoard.getPiece(probe) instanceof King))
         return true;     

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

  

}
