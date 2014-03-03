package chess.model.pieces;

import chess.model.Color;
import chess.model.Position;

/**
 * Created by evadrone on 3/3/14.
 */
public interface Piece {

    public Color getColor();

    /**
     * Returns true if the new position is valid from the piece's perspective.
     *
     * @param p1 the original position
     * @param p2 the new position
     * @return true if the new position is valid from the piece's perspective; false otherwise
     */
    public boolean isValidMove(Position p1, Position p2);
}
