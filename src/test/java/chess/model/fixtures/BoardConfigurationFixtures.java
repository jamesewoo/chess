package chess.model.fixtures;

import chess.model.Position;
import chess.model.PositionImpl;
import chess.model.pieces.*;

import java.util.HashMap;
import java.util.Map;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;

/**
 * Created by evadrone on 3/3/14.
 */
public class BoardConfigurationFixtures {

    public static Map<Position, Piece> newEndGameFixture() {
        Map<Position, Piece> boardConfig = new HashMap<>();
        boardConfig.put(new PositionImpl(0, 0), new King(BLACK));
        boardConfig.put(new PositionImpl(7, 0), new King(WHITE));
        boardConfig.put(new PositionImpl(2, 0), new Rook(WHITE));
        return boardConfig;
    }

    public static Map<Position, Piece> newPawnSetupFixture() {
        Map<Position, Piece> boardConfig = new HashMap<>();
        boardConfig.put(new PositionImpl(7, 4), new King(WHITE));
        boardConfig.put(new PositionImpl(0, 4), new King(BLACK));
        boardConfig.put(new PositionImpl(4, 3), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 7), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(5, 6), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(6, 1), new Pawn(WHITE));
        boardConfig.put(new PositionImpl(2, 0), new Knight(WHITE));
        boardConfig.put(new PositionImpl(3, 4), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(3, 3), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 1), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(1, 0), new Pawn(BLACK));
        boardConfig.put(new PositionImpl(5, 1), new Rook(BLACK));
        return boardConfig;
    }
}
