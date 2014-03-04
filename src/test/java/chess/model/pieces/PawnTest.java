package chess.model.pieces;

import chess.model.Board;
import chess.model.BoardImpl;
import chess.model.PositionImpl;
import org.junit.Test;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;
import static chess.model.fixtures.BoardConfigurationFixtures.newPawnSetupFixture;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by evadrone on 3/3/14.
 */
public class PawnTest {
    @Test
    public void testMoveInWrongDirection() {
        Pawn pawn = new Pawn(WHITE);
        Board board = new BoardImpl(WHITE, newPawnSetupFixture());
        System.out.println(board);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(4, 3), new PositionImpl(5, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(4, 3), new PositionImpl(6, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(4, 3), new PositionImpl(5, 2)));
        pawn = new Pawn(BLACK);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(3, 3), new PositionImpl(2, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(3, 3), new PositionImpl(1, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(3, 3), new PositionImpl(2, 4)));
    }

    @Test
    public void testMoveIntoOppositeColor() {
        Pawn pawn = new Pawn(WHITE);
        Board board = new BoardImpl(WHITE, newPawnSetupFixture());
        System.out.println(board);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(4, 3), new PositionImpl(3, 3)));
        pawn = new Pawn(BLACK);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(1, 0), new PositionImpl(2, 0)));
    }

    @Test
    public void testMoveTwoSpaces() {
        Pawn pawn = new Pawn(WHITE);
        Board board = new BoardImpl(WHITE, newPawnSetupFixture());
        System.out.println(board);
        pawn.setBoard(board);
        assertTrue(pawn.isValidMove(new PositionImpl(6, 7), new PositionImpl(4, 7)));
        assertFalse(pawn.isValidMove(new PositionImpl(5, 6), new PositionImpl(3, 6)));
        pawn = new Pawn(BLACK);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(3, 4), new PositionImpl(5, 4)));
        assertTrue(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
    }

    @Test
    public void testMoveCollisionAtMidpoint() {
        Pawn pawn = new Pawn(WHITE);
        Board board = new BoardImpl(WHITE, newPawnSetupFixture());
        System.out.println(board);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(4, 1)));
        pawn = new Pawn(BLACK);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(1, 0), new PositionImpl(3, 0)));
    }

    @Test
    public void testMoveMoreThanTwoSpaces() {
        Pawn pawn = new Pawn(WHITE);
        Board board = new BoardImpl(WHITE, newPawnSetupFixture());
        System.out.println(board);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(5, 6), new PositionImpl(2, 6)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 7), new PositionImpl(3, 7)));
        pawn = new Pawn(BLACK);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(4, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(4, 3), new PositionImpl(7, 3)));
    }

    @Test
    public void testCaptureLogic() {
        Pawn pawn = new Pawn(WHITE);
        Board board = new BoardImpl(WHITE, newPawnSetupFixture());
        System.out.println(board);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(5, 0)));
        assertTrue(pawn.isValidMove(new PositionImpl(4, 3), new PositionImpl(3, 4)));
        pawn = new Pawn(BLACK);
        pawn.setBoard(board);
        assertFalse(pawn.isValidMove(new PositionImpl(1, 0), new PositionImpl(2, 1)));
        assertTrue(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
    }

    @Test
    public void testIsValidMove() {
        Pawn pawn = new Pawn(WHITE);
        pawn.setBoard(new BoardImpl());
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(5, 0)));
        assertTrue(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(5, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(5, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(6, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(6, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(6, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(7, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(7, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(7, 2)));
        assertTrue(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(4, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(1, 4)));
        assertFalse(pawn.isValidMove(new PositionImpl(6, 1), new PositionImpl(4, 3)));

        pawn = new Pawn(BLACK);
        pawn.setBoard(new BoardImpl());
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(0, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 0)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 2)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 0)));
        assertTrue(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(2, 2)));
        assertTrue(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 1)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(1, 3)));
        assertFalse(pawn.isValidMove(new PositionImpl(1, 1), new PositionImpl(3, 3)));
    }
}
