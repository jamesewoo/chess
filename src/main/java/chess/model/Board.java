package chess.model;

import chess.model.pieces.Piece;

/**
 * Created by evadrone on 3/3/14.
 */
public interface Board {

    /**
     * Returns the player whose turn it is.
     *
     * @return the player whose turn it is
     */
    public Color getActivePlayer();

    /**
     * Moves the piece at the given position to the new position.  The move must be validated before being executed.
     *
     * @param currentPosition the original position
     * @param newPosition     the new position
     * @return true if the piece was moved; false otherwise
     */
    public boolean move(Position currentPosition, Position newPosition);

    /**
     * Returns the piece at the given position.
     *
     * @param position the position to query
     * @return the piece at the given position; null if there is no piece
     */
    public Piece getPiece(Position position);
}
