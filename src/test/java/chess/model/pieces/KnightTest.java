package chess.model.pieces;

import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class KnightTest {
    @Test
    public void testIsValidMove() {
        Piece knight = new Knight(WHITE);
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertFalse(knight.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));

        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(3, 4)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(4, 3)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(3, 0)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(0, 3)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(1, 4)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(4, 1)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(1, 0)));
        assertTrue(knight.isValidMove(new PositionImpl(2, 2), new PositionImpl(0, 1)));
    }
}
