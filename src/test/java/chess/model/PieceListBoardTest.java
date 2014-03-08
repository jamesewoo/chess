package chess.model;

import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import org.junit.Test;

import java.util.Map;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;
import static chess.model.fixtures.BoardConfigurationFixtures.newEndGameFixture;
import static chess.model.fixtures.BoardConfigurationFixtures.newMidGameFixture;
import static org.junit.Assert.*;

/**
 * Created by evadrone on 3/3/14.
 */
public class PieceListBoardTest {

    @Test
    public void testFindPiece() {
        PieceListBoard board = new PieceListBoard();
        Position position = new PositionImpl(7, 4);
        Piece p = board.getPiece(position);
        assertEquals(position, board.findPosition(p));
        position = new PositionImpl(0, 4);
        p = board.getPiece(position);
        assertEquals(position, board.findPosition(p));
        position = new PositionImpl(7, 5);
        p = board.getPiece(position);
        assertEquals(position, board.findPosition(p));
        position = new PositionImpl(0, 5);
        p = board.getPiece(position);
        assertEquals(position, board.findPosition(p));

        Map<Position, Piece> boardConfig = newEndGameFixture();
        Piece blackPawn = new Pawn(BLACK);
        boardConfig.put(new PositionImpl(6, 2), blackPawn);
        board = new PieceListBoard(BLACK, boardConfig);
        System.out.println(board);
        assertEquals(new PositionImpl(6, 2), board.findPosition(blackPawn));
    }

    @Test
    public void testIsKingExposed() {
        PieceListBoard board = new PieceListBoard();
        assertFalse(board.isKingExposed(WHITE));
        assertFalse(board.isKingExposed(BLACK));

        Map<Position, Piece> boardConfig = newEndGameFixture();
        board = new PieceListBoard(BLACK, boardConfig);
        System.out.println(board);
        assertFalse(board.isKingExposed(WHITE));
        assertTrue(board.isKingExposed(BLACK));
    }

    @Test
    public void testMovePieceNotFound() {
        Board board = new PieceListBoard();
        assertFalse(board.move(new PositionImpl(2, 0), new PositionImpl(3, 0)));
    }

    @Test
    public void testMoveOutOfOrder() {
        Board board = new PieceListBoard();
        assertFalse("white's turn but black moves", board.move(new PositionImpl(1, 0), new PositionImpl(1, 1)));
        assertTrue("white moves", board.move(new PositionImpl(6, 0), new PositionImpl(5, 0)));
        System.out.println(board);
        assertFalse("black's turn but white moves", board.move(new PositionImpl(5, 0), new PositionImpl(4, 0)));
    }

    @Test
    public void testMoveOutOfBounds() {
        Board board = new PieceListBoard();
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(-1, -1)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(-1, 1)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(-1, 8)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(1, -1)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(1, 8)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(8, -1)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(8, 1)));
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(8, 8)));
    }

    @Test
    public void testMoveToSamePosition() {
        Board board = new PieceListBoard();
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(6, 0)));
        assertFalse(board.move(new PositionImpl(7, 1), new PositionImpl(7, 1)));
        assertFalse(board.move(new PositionImpl(7, 7), new PositionImpl(7, 7)));
    }

    @Test
    public void testMoveViolatesMovementConstraintsOfPiece() {
        Board board = new PieceListBoard();
        assertFalse(board.move(new PositionImpl(6, 0), new PositionImpl(3, 0)));
        assertFalse(board.move(new PositionImpl(7, 0), new PositionImpl(5, 2)));
        assertFalse(board.move(new PositionImpl(7, 1), new PositionImpl(5, 1)));
        assertFalse(board.move(new PositionImpl(7, 2), new PositionImpl(5, 2)));
        assertFalse(board.move(new PositionImpl(7, 3), new PositionImpl(5, 2)));
        assertFalse(board.move(new PositionImpl(7, 4), new PositionImpl(5, 4)));
    }

    @Test
    public void testMoveCollidesWithSameColorAtEndpoint() {
        Board board = new PieceListBoard();
        assertFalse(board.move(new PositionImpl(7, 0), new PositionImpl(6, 0)));
        assertFalse(board.move(new PositionImpl(7, 1), new PositionImpl(6, 3)));
        assertFalse(board.move(new PositionImpl(7, 2), new PositionImpl(6, 1)));
        assertFalse(board.move(new PositionImpl(7, 3), new PositionImpl(6, 3)));
        assertFalse(board.move(new PositionImpl(7, 4), new PositionImpl(6, 5)));
    }

    @Test
    public void testMoveCollidesWithPieceDuringTraversal() {
        Board board = new PieceListBoard(WHITE, newMidGameFixture());
        System.out.println(board);
        assertFalse(board.move(new PositionImpl(4, 2), new PositionImpl(4, 0)));
        board = new PieceListBoard(BLACK, newMidGameFixture());
        assertFalse(board.move(new PositionImpl(6, 4), new PositionImpl(3, 7)));
        assertFalse(board.move(new PositionImpl(6, 4), new PositionImpl(3, 1)));
        assertTrue("this is a valid capture", board.move(new PositionImpl(6, 4), new PositionImpl(4, 2)));
        System.out.println(board);
    }

    @Test
    public void testMoveExposesKingOfSameColor() {
        Board board = new PieceListBoard(WHITE, newMidGameFixture());
        System.out.println(board);
        Piece pieceToCapture = board.getPiece(new PositionImpl(6, 4));
        assertFalse(board.move(new PositionImpl(7, 4), new PositionImpl(6, 4)));
        assertEquals("the canceled move resulted in a change to the board", pieceToCapture, board.getPiece(new PositionImpl(6, 4)));
        System.out.println(board);
        board = new PieceListBoard(BLACK, newMidGameFixture());
        System.out.println(board);
        assertFalse(board.move(new PositionImpl(2, 6), new PositionImpl(3, 6)));
        assertNotNull("the canceled move resulted in a change to the board", board.getPiece(new PositionImpl(2, 6)));
        System.out.println(board);
    }

    @Test
    public void testMoveTogglesActivePlayer() {
        Board board = new PieceListBoard();
        System.out.println(board);
        assertEquals(WHITE, board.getActivePlayer());
        board.move(new PositionImpl(6, 4), new PositionImpl(5, 4));
        System.out.println(board);
        assertEquals(BLACK, board.getActivePlayer());
        board.move(new PositionImpl(1, 4), new PositionImpl(2, 4));
        System.out.println(board);
        assertEquals(WHITE, board.getActivePlayer());
    }
}
