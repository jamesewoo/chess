package chess.model.pieces;

import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class BishopTest {
    @Test
    public void testIsValidMove() {
        Piece bishop = new Bishop(WHITE);
        assertTrue(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertTrue(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertTrue(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertTrue(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertFalse(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertTrue(bishop.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));
    }
}
