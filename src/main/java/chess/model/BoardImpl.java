package chess.model;

import chess.model.pieces.King;
import chess.model.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;

/**
 * An 8x8 Chess board with the upper left position represented by (0, 0) and the bottom right position represented by (7, 7).
 */
public class BoardImpl implements Board {

    private final Map<Position, Piece> positionPieceMap = new HashMap<>();
    private final King whiteKing;
    private final King blackKing;
    private Color activePlayer;

    public BoardImpl() {
        activePlayer = WHITE;
        PositionImpl position = new PositionImpl(7, 3);
        whiteKing = new King(WHITE);
        positionPieceMap.put(position, whiteKing);
        position = new PositionImpl(0, 3);
        blackKing = new King(BLACK);
        positionPieceMap.put(position, blackKing);
    }

    @Override
    public Color getActivePlayer() {
        return activePlayer;
    }

    @Override
    public boolean move(Position currentPosition, Position newPosition) {
        Piece piece = positionPieceMap.get(currentPosition);
        if (piece == null) {
            System.out.println("no piece was found at the given position");
            return false;
        }
        if (piece.getColor() != activePlayer) {
            System.out.println("it is not this player's turn");
            return false;
        }
        if (newPosition.getColumn() < 0 || newPosition.getColumn() > 7
                || newPosition.getRow() < 0 || newPosition.getRow() > 7) {
            System.out.println("new position is out of bounds");
            return false;
        }
        if (newPosition.equals(currentPosition)) {
            System.out.println("new position cannot equal current position");
            return false;
        }
        if (!piece.isValidMove(currentPosition, newPosition)) {
            System.out.println("new position is invalid for the moving piece");
            return false;
        }
        Piece existingPiece = positionPieceMap.get(newPosition);
        if (existingPiece != null && existingPiece.getColor() == piece.getColor()) {
            System.out.println("a piece of the same color already exists at the new position");
            return false;
        }

        positionPieceMap.remove(currentPosition);
        positionPieceMap.put(newPosition, piece);
        if (kingIsExposed(piece.getColor())) {
            System.out.println("the move exposes the king of the same color");
            positionPieceMap.remove(newPosition);
            positionPieceMap.put(currentPosition, piece);
            if (existingPiece != null) {
                positionPieceMap.put(newPosition, existingPiece);
            }
            return false;
        }
        return true;
    }

    /**
     * Returns the position of the given piece.
     *
     * @param piece the piece to find
     * @return the position of the given piece; null if it does not exist
     */
    Position findPiece(Piece piece) {
        Position position = null;
        for (Map.Entry<Position, Piece> entry : positionPieceMap.entrySet()) {
            if (piece == entry.getValue()) {
                position = entry.getKey();
            }
        }
        return position;
    }

    boolean kingIsExposed(Color color) {
        Position kingPosition;
        if (color == WHITE) {
            kingPosition = findPiece(whiteKing);
        } else {
            kingPosition = findPiece(blackKing);
        }
        if (kingPosition == null) {
            throw new IllegalStateException("no king found");
        }
        for (Map.Entry<Position, Piece> entry : positionPieceMap.entrySet()) {
            Piece p = entry.getValue();
            if (p.getColor() != color && p.isValidMove(entry.getKey(), kingPosition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece getPiece(Position position) {
        return positionPieceMap.get(position);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < 8; ++row) {
            str.append("!");
            for (int col = 0; col < 8; ++col) {
                Piece piece = positionPieceMap.get(new PositionImpl(row, col));
                if (piece != null) {
                    str.append(format("%8s",
                            piece.getColor().toString().charAt(0) + "_" + piece.getClass().getSimpleName()));
                } else {
                    str.append("________");
                }
                str.append("!");
            }
            str.append(lineSeparator());
        }
        return str.toString();
    }
}
