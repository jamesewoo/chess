package chess.model.pieces;

import chess.model.*;

import static chess.model.Direction.DOWN;
import static chess.model.Direction.UP;
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
     * Returns true if the move is valid.  Does not check if movement exposes the king of the same color.
     *
     * @param p1 the original position
     * @param p2 the new position
     * @return true if the move is valid
     */
    @Override
    public boolean isValidMove(Position p1, Position p2) {
        Direction direction = board.getDirection(color);
        if (direction.getValue() * (p2.getRank() - p1.getRank()) < 0) {
            System.out.println("the move is in the wrong direction");
            return false;
        }
        if (p2.getFile() == p1.getFile()) {
            // Movement Logic
            Piece existingPiece = board.getPiece(p2);
            if (existingPiece != null) {
                System.out.println("a pawn may not capture during normal movement");
                return false;
            }
            int verticalDistance = abs(p2.getRank() - p1.getRank());
            if (verticalDistance == 1) {
                return true;
            } else if (verticalDistance == 2) {
                if (direction == UP && p1.getRank() != 6) {
                    System.out.println("a pawn can only move 2 spaces on its first move");
                    return false;
                } else if (direction == DOWN && p1.getRank() != 1) {
                    System.out.println("a pawn can only move 2 spaces on its first move");
                    return false;
                }
                Position midpoint = new PositionImpl(p1.getRank() + (p2.getRank() - p1.getRank()) / 2, p1.getFile());
                Piece inBetween = board.getPiece(midpoint);
                if (inBetween != null) {
                    System.out.println("there was a piece in between movement endpoints");
                    return false;
                }
                return true;
            } else {
                System.out.println("a pawn can move at most 2 spaces");
                return false;
            }
        } else if ((abs(p2.getFile() - p1.getFile()) == 1)
                && (abs(p2.getRank() - p1.getRank()) == 1)) {
            // Capture Logic
            Piece existingPiece = board.getPiece(p2);
            if (existingPiece != null && existingPiece.getColor() != color) {
                return true;
            } else {
                // TODO en passant logic
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }
}
