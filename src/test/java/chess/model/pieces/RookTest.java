package chess.model.pieces;

import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class RookTest {
    @Test
    public void testIsValidMove() {
        Piece rook = new Rook(WHITE);
        assertFalse(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertTrue(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertFalse(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertTrue(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertTrue(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertFalse(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertTrue(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertFalse(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertTrue(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertTrue(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertFalse(rook.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));
    }
}
