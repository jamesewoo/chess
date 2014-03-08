package chess.model.pieces;

import chess.model.Board;
import chess.model.BoardAware;
import chess.model.Color;
import chess.model.Position;

import static java.lang.Math.abs;

/**
 * Created by evadrone on 3/3/14.
 */
public class King implements Piece, BoardAware {

    private final Color color;
    private Board board;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position p1, Position p2) {
        // TODO add castle logic
        return abs(p2.getRank() - p1.getRank()) == 1
                || abs(p2.getFile() - p1.getFile()) == 1;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }
}
