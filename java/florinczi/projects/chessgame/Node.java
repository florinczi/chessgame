package florinczi.projects.chessgame;

import java.util.List;

import florinczi.projects.chessgame.pieces.Piece;
import florinczi.projects.chessgame.util.MoveCandidate;

public class Node {

    private int depth;
    private int targetDepth;


    public int getDepth() {
        return depth;
    }



    private MoveCandidate seed;
    private Board nodeBoard;
    private List<MoveCandidate> nodeMoves;
    private List<Node> children;
    private Node parent;
   


  
 public Board getNodeBoard() {
        return nodeBoard;
    }

    

    public Node(Node parent, MoveCandidate seed){
        this.depth = parent.getDepth() + 1;
        this.seed = seed;  
        this.targetDepth = targetDepth;
        genNodeBoard();
        genNodeMoves();           
    }

    public Node(Board board, int depth){
        this.depth = depth;
        this.nodeBoard = board;
    
    }

    public MoveCandidate minmax(MoveCandidate move, int depth){

        if (depth == 0)
            return move;
        
        return move;


    }


    private void genNodeMoves(){
        nodeMoves = nodeBoard.genMoves();
    }

    private void genNodeBoard(){
        nodeBoard = new Board(parent.getNodeBoard());
        Piece piece = parent.getNodeBoard().getPiece(seed.getCoord());
        piece.movePiece(seed, nodeBoard);
    }

    
}
