package chess.model.pieces;

import chess.model.BoardImpl;
import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class KingTest {
    @Test
    public void testIsValidMove() {
        King king = new King(WHITE);
        king.setBoard(new BoardImpl());
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertTrue(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertFalse(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertFalse(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertFalse(king.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));
    }
}
