package florinczi.projects.chessgame;

import static florinczi.projects.chessgame.pieces.PlayerColor.WHITE;

import florinczi.projects.chessgame.pieces.Piece;

public class Evaluator {

    private Evaluator() {
        throw new IllegalStateException("Utility class");
      }

    public static float evaluate (Board board){
        float eval=0f;

        if (board.getEngine().getCheckChecker().hasTheGameEnded(board.getMoveList(), board)){
            if (board.getEngine().getCheckChecker().isCheckmate()) {
                if (board.getNowPlaying() == WHITE)
                    return -100000f;
                else
                    return 100000f;
            }
            else
                return 0f;
        }

        
        for (Piece piece: board.getBoardmap().values()){
            if ( piece.getPlayer() == WHITE)
                eval += piece.getValue();
            else
                eval -= piece.getValue();
        }

        eval += evaluateMobility(board);


        return eval;
    }

    private static float evaluateMobility(Board board){
        float ev = 0;
        ev += board.getNowPlaying() == WHITE?
        board.getEngine().genBoardMoves(board).size() : -board.getEngine().genBoardMoves(board).size();
        board.changePlayers();
        ev += board.getNowPlaying() == WHITE?
        board.getEngine().genBoardMoves(board).size() : -board.getEngine().genBoardMoves(board).size();
        board.changePlayers();

        return ev;
    }
    
}
