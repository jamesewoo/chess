package chess.model.pieces;

import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class QueenTest {
    @Test
    public void testIsValidMove() {
        Piece queen = new Queen(WHITE);
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertTrue(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));
        assertFalse(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 3)));
        assertFalse(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 2)));
        assertFalse(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 3)));
        assertFalse(queen.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 0)));
    }
}
