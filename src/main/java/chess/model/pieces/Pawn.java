package chess.model.pieces;

import chess.model.Board;
import chess.model.BoardAware;
import chess.model.Color;
import chess.model.Position;

import static java.lang.Math.abs;

/**
 * Created by evadrone on 3/3/14.
 */
public class Pawn implements Piece, BoardAware {

    private final Color color;
    private Board board;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Returns true if the move is valid.
     *
     * @param p1 the original position
     * @param p2 the new position
     * @return true if the move is valid
     */
    @Override
    public boolean isValidMove(Position p1, Position p2) {
        int directionVector = board.getDirection(color).getValue();
        if (directionVector * (p2.getRow() - p1.getRow()) < 0) {
            // a move in the wrong direction
            return false;
        }
        if (p2.getColumn() == p1.getColumn()) {
            // Movement Logic
            Piece existingPiece = board.getPiece(p2);
            if (existingPiece != null) {
                // a pawn may not capture during normal movement
                return false;
            }
            int verticalDistance = abs(p2.getRow() - p1.getRow());
            if (verticalDistance == 1) {
                return true;
            } else if (verticalDistance == 2) {
                // ensure this is the pawn's first move
                // TODO
            } else {
                return false;
            }
        } else if ((abs(p2.getColumn() - p1.getColumn()) == 1)
                && (abs(p2.getRow() - p1.getRow()) == 1)) {
            // Capture Logic
            Piece existingPiece = board.getPiece(p2);
            if (existingPiece != null && existingPiece.getColor() != color) {
                return true;
            } else {
                // TODO en passant logic
            }
        }
        return false;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }
}
