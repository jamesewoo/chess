package chess.model.pieces;

import chess.model.Color;
import chess.model.Position;

import static java.lang.Math.abs;

/**
 * Created by evadrone on 3/3/14.
 */
public class Knight implements Piece {

    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidMove(Position p1, Position p2) {
        return (abs(p2.getRow() - p1.getRow()) == 1 && abs(p2.getColumn() - p1.getColumn()) == 2)
                || (abs(p2.getRow() - p1.getRow()) == 2 && abs(p2.getColumn() - p1.getColumn()) == 1);
    }
}
