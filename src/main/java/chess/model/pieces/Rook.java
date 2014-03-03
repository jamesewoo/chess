package chess.model.pieces;

import chess.model.Color;
import chess.model.Position;

/**
 * Created by evadrone on 3/3/14.
 */
public class Rook implements Piece {

    private final Color color;

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position p1, Position p2) {
        if (p2.equals(p1)) {
            return false;
        }
        return p2.getRow() == p1.getRow()
                || p2.getColumn() == p1.getColumn();
    }
}
