package chess.model.pieces;

import chess.model.Color;
import chess.model.Position;

import static java.lang.Math.abs;

/**
 * Created by evadrone on 3/3/14.
 */
public class Bishop implements Piece {

    private final Color color;

    public Bishop(Color color) {
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
        return abs(p2.getRow() - p1.getRow()) == abs(p2.getColumn() - p1.getColumn());
    }
}
