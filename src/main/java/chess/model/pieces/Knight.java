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
        return (abs(p2.getRank() - p1.getRank()) == 1 && abs(p2.getFile() - p1.getFile()) == 2)
                || (abs(p2.getRank() - p1.getRank()) == 2 && abs(p2.getFile() - p1.getFile()) == 1);
    }
}
