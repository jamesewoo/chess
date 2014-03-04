package chess.model;

import chess.model.pieces.*;

import java.util.HashMap;
import java.util.Map;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;
import static chess.model.Direction.DOWN;
import static chess.model.Direction.UP;
import static java.lang.Integer.signum;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Map.Entry;

/**
 * An 8x8 Chess board with the upper left position represented by (0, 0) and the bottom right position represented by (7, 7).
 */
public class BoardImpl implements Board {

    private final Map<Position, Piece> boardConfig;
    private final Piece whiteKing;
    private final Piece blackKing;
    private Color activePlayer;

    public BoardImpl() {
        activePlayer = WHITE;
        whiteKing = new King(WHITE);
        blackKing = new King(BLACK);
        boardConfig = new HashMap<>();
        boardConfig.put(new PositionImpl(6, 0), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 1), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 2), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 3), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 4), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 5), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 6), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 7), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(7, 0), new Rook(WHITE));
        boardConfig.put(new PositionImpl(7, 7), new Rook(WHITE));
        boardConfig.put(new PositionImpl(7, 1), new Knight(WHITE));
        boardConfig.put(new PositionImpl(7, 6), new Knight(WHITE));
        boardConfig.put(new PositionImpl(7, 2), new Bishop(WHITE));
        boardConfig.put(new PositionImpl(7, 5), new Bishop(WHITE));
        boardConfig.put(new PositionImpl(7, 3), new Queen(WHITE));
        boardConfig.put(new PositionImpl(7, 4), whiteKing);
        boardConfig.put(new PositionImpl(1, 0), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 1), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 2), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 3), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 4), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 5), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 6), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 7), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(0, 0), new Rook(BLACK));
        boardConfig.put(new PositionImpl(0, 7), new Rook(BLACK));
        boardConfig.put(new PositionImpl(0, 1), new Knight(BLACK));
        boardConfig.put(new PositionImpl(0, 6), new Knight(BLACK));
        boardConfig.put(new PositionImpl(0, 2), new Bishop(BLACK));
        boardConfig.put(new PositionImpl(0, 5), new Bishop(BLACK));
        boardConfig.put(new PositionImpl(0, 3), new Queen(BLACK));
        boardConfig.put(new PositionImpl(0, 4), blackKing);

        registerBoardListeners();
    }

    public BoardImpl(Color activePlayer, Map<Position, Piece> boardConfig) {
        this.activePlayer = activePlayer;
        this.boardConfig = boardConfig;
        Piece whiteKing = null;
        Piece blackKing = null;
        for (Piece piece : boardConfig.values()) {
            if (piece instanceof King) {
                if (piece.getColor() == WHITE) {
                    whiteKing = piece;
                } else {
                    blackKing = piece;
                }
            }
        }
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;

        registerBoardListeners();
    }

    private void registerBoardListeners() {
        for (Piece p : boardConfig.values()) {
            if (p instanceof BoardAware) {
                ((BoardAware) p).setBoard(this);
            }
        }
    }

    @Override
    public Color getActivePlayer() {
        return activePlayer;
    }

    @Override
    public Direction getDirection(Color color) {
        if (color == WHITE) {
            return UP;
        } else {
            return DOWN;
        }
    }

    @Override
    public boolean move(Position currentPosition, Position newPosition) {
        Piece piece = boardConfig.get(currentPosition);
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
            System.out.println("new position is invalid given the movement constraints of the piece");
            return false;
        }
        Piece existingPiece = boardConfig.get(newPosition);
        if (existingPiece != null && existingPiece.getColor() == piece.getColor()) {
            System.out.println("a piece of the same color already exists at the new position");
            return false;
        }
        if (!(piece instanceof Knight)) {
            int rowSign = signum(newPosition.getRow() - currentPosition.getRow());
            int columnSign = signum(newPosition.getColumn() - currentPosition.getColumn());
            Position inBetween = new PositionImpl(currentPosition.getRow() + rowSign, currentPosition.getColumn() + columnSign);
            while (!newPosition.equals(inBetween)) {
                if (boardConfig.get(inBetween) != null) {
                    System.out.println("the move would cause a collision before reaching the endpoint");
                    return false;
                }
                inBetween = new PositionImpl(inBetween.getRow() + rowSign, inBetween.getColumn() + columnSign);
            }
        }

        boardConfig.remove(currentPosition);
        boardConfig.put(newPosition, piece);
        if (isKingExposed(piece.getColor())) {
            System.out.println("the move exposes the king of the same color");
            boardConfig.remove(newPosition);
            boardConfig.put(currentPosition, piece);
            if (existingPiece != null) {
                boardConfig.put(newPosition, existingPiece);
            }
            return false;
        }

        if (activePlayer == WHITE) {
            activePlayer = BLACK;
        } else {
            activePlayer = WHITE;
        }
        return true;
    }

    /**
     * Returns the position of the given piece.
     *
     * @param piece the piece to find
     * @return the position of the given piece; null if it does not exist
     */
    Position findPosition(Piece piece) {
        Position position = null;
        for (Entry<Position, Piece> entry : boardConfig.entrySet()) {
            if (piece == entry.getValue()) {
                position = entry.getKey();
            }
        }
        return position;
    }

    boolean isKingExposed(Color color) {
        Position kingPosition;
        if (color == WHITE) {
            kingPosition = findPosition(whiteKing);
        } else {
            kingPosition = findPosition(blackKing);
        }
        if (kingPosition == null) {
            throw new IllegalStateException("no king found");
        }
        for (Entry<Position, Piece> entry : boardConfig.entrySet()) {
            Piece p = entry.getValue();
            if (p.getColor() != color && p.isValidMove(entry.getKey(), kingPosition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece getPiece(Position position) {
        return boardConfig.get(position);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < 8; ++row) {
            str.append("!");
            for (int col = 0; col < 8; ++col) {
                Piece piece = boardConfig.get(new PositionImpl(row, col));
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
