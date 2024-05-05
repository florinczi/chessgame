package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.ArrayList;
import java.util.Collection;
import florinczi.projects.chessgame.util.MoveCandidate;

public class RootNode {
 
    private static int DEPTH = 3;
    
    Board rootBoard;
    Collection <MoveCandidate> rootMoveList;
    int nodeCount;

    public RootNode(Board board) {
        this.rootBoard = board;
        this.rootMoveList = board.getMoveList(board.getNowPlaying());
        nodeCount = 0;
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
            //System.out.printf("Considering move %s now%n", mc);
            
        }
        //System.out.printf("Chosen this move with eval %.2f%n", eval);
        return winner;

    }

    private float minmax (Board board, int depth){
        
        if (depth == 0 || board.getMoveList().isEmpty()){
            float ev;
            nodeCount++;
            ev = Evaluator.evaluate(board);
            //Menu.printBoard(board);
            //System.out.printf("Node %d, evaluation %.2f%n. Board %s%n", nodeCount, ev, board);
            return ev;
        }
        else {
            //System.out.printf("Depth %d now...%n", depth);
            nodeCount++;
            Collection <MoveCandidate> moveList = board.getMoveList(board.getNowPlaying());
            Collection <Board> boardList = new ArrayList<>();
            
            moveList.forEach(move -> boardList.add(board.getEngine().prepareMove(board, move)));
            
            float result = 0;

            for (Board b: boardList){
            
                b.changePlayers();
                
                if (b.getNowPlaying() == WHITE){
                   float maxEv = Float.NEGATIVE_INFINITY; 
                   maxEv = Math.max(maxEv, minmax(b, depth-1));
                   result = maxEv;
                }
                else{
                   float minEv = Float.POSITIVE_INFINITY; 
                   minEv = Math.min(minEv, minmax(b, depth-1));
                   result = minEv;
                }
            }
            return result;
        }
    
    }
   

    
}
