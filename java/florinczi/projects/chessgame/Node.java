package florinczi.projects.chessgame;

import java.util.List;

import florinczi.projects.chessgame.pieces.Piece;

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

    public Node(Board rootBoard){
        this.depth = 0;
        this.nodeBoard = rootBoard;    
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
