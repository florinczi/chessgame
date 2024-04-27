package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.ArrayList;
import java.util.Collection;
import florinczi.projects.chessgame.util.MoveCandidate;

public class RootNode {
 
    private static int DEPTH = 4;
    
    Board rootBoard;
    Collection <MoveCandidate> rootMoveList;

    public RootNode(Board board) {
        this.rootBoard = board;
        this.rootMoveList = board.getMoveList();
    }

    

    public Board minmaxRoot(){
        Board winner = null;
        float eval;
        if (rootBoard.getNowPlaying() == WHITE)
            eval = Float.NEGATIVE_INFINITY;
        else
            eval = Float.POSITIVE_INFINITY;

        for (MoveCandidate mc: rootMoveList){
            if (rootBoard.getNowPlaying() == WHITE){
                Board newboard = rootBoard.getEngine().prepareMove(rootBoard, mc);
                float maxeval = minmax(newboard, DEPTH);
                if (maxeval > eval){
                    eval = maxeval;
                    winner = newboard;
                }
            }
            else {
                Board newboard = rootBoard.getEngine().prepareMove(rootBoard, mc);
                float maxeval = minmax(newboard, DEPTH);
                if (maxeval < eval){
                    eval = maxeval;
                    winner = newboard;
                }
            }
        }
        return winner;

    }

    private float minmax (Board board, int depth){
        
        if (depth == 0 || board.getMoveList().isEmpty())
            return Evaluator.evaluate(board);
        else {
            Collection <MoveCandidate> moveList = board.getMoveList();
            Collection <Board> boardList = new ArrayList<>();
            
            moveList.forEach(move -> boardList.add(board.getEngine().prepareMove(board, move)));
            
            float result = 0;

            for (Board b: boardList){
            
                b.changePlayers();
                
                if (b.getNowPlaying() == WHITE){
                   float maxEv = Float.NEGATIVE_INFINITY; 
                   maxEv = Math.max(maxEv, minmax(board, depth-1));
                   result = maxEv;
                }
                else{
                   float minEv = Float.POSITIVE_INFINITY; 
                   minEv = Math.max(minEv, minmax(board, depth-1));
                   result = minEv;
                }
            }
            return result;
        }
    
    }
   

    
}
