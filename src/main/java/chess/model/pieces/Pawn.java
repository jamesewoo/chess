package chess.model.pieces;

import chess.model.Color;
import chess.model.Position;

import static chess.model.Color.WHITE;

/**
 * Created by evadrone on 3/3/14.
 */
public class Pawn implements Piece {

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Returns true if the move is valid.  Initial moves, captures, and en passant are not taken into account.
     *
     * @param p1 the original position
     * @param p2 the new position
     * @return true if the move is valid
     */
    @Override
    public boolean isValidMove(Position p1, Position p2) {
        if (p2.getColumn() == p1.getColumn()) {
            if (color == WHITE) {
                return p1.getRow() - p2.getRow() == 1;
            } else {
                return p2.getRow() - p1.getRow() == 1;
            }
        }
        return false;
    }
}
