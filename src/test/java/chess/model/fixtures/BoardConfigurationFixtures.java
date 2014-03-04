package chess.model.fixtures;

import chess.model.Position;
import chess.model.PositionImpl;
import chess.model.pieces.King;
import chess.model.pieces.Piece;
import chess.model.pieces.Rook;

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
}
