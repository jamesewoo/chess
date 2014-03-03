package chess.model.pieces;

import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class PawnTest {
    @Test
    public void testIsValidMove() {
        Piece pawn = new Pawn(WHITE);
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertTrue(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));

        pawn = new Pawn(BLACK);
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertTrue(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));
    }
}
