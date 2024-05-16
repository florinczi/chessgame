package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import java.util.ArrayList;
import java.util.Collection;
import florinczi.projects.chessgame.util.MoveCandidate;

public class RootNode {
 
    private static final int DEPTH = 3;
    
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
        int rootBoardno = 0;
        if (rootBoard.getNowPlaying() == WHITE)
            eval = Float.NEGATIVE_INFINITY;
        else
            eval = Float.POSITIVE_INFINITY;


        for (MoveCandidate mc: rootMoveList){
            System.out.printf("Evaluating root board number %d with move %s%n", rootBoardno++, mc);
            Board newboard = rootBoard.getEngine().prepareMove(rootBoard, mc);
            float branchEval = minmax(newboard, DEPTH);
            System.out.printf("Evaluated with %.2f%n", branchEval);
            if (rootBoard.getNowPlaying() == WHITE && branchEval > eval){
                winner = newboard;
                eval = branchEval;

            }
            else if (branchEval < eval){
                winner = newboard;
                eval = branchEval;
            }

            //System.out.printf("Considering move %s now%n", mc);
            
        }
        System.out.printf("Chosen this move with expected eval %.2f%n. Analysed %d nodes", eval,nodeCount);

        return winner;

    }

    private float minmax (Board board, int depth){
        
        if (depth == 0 || board.getMoveList().isEmpty()){
            float ev;
            nodeCount++;
            ev = Evaluator.evaluate(board);
            //Menu.printBoard(board);
            System.out.printf("Node %d, evaluation %.2f%n. Board %s%n", nodeCount, ev, board);
            return ev;
        }
        else {

            nodeCount++;
            Collection <Board> boardList = new ArrayList<>();
            
            board.getMoveList().forEach(move -> boardList.add(board.getEngine().prepareMove(board, move)));
            
            float result = 0;

            for (Board b: boardList){
            

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
