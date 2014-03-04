package chess.model;

import chess.model.pieces.Piece;
import org.junit.Test;

import static chess.model.Color.BLACK;
import static chess.model.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by evadrone on 3/3/14.
 */
public class BoardImplTest {

    @Test
    public void testFindPiece() {
        BoardImpl board = new BoardImpl();
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
        // TODO move pieces around and test
    }

    @Test
    public void testIsKingExposed() {
        BoardImpl board = new BoardImpl();
        assertFalse(board.isKingExposed(WHITE));
        assertFalse(board.isKingExposed(BLACK));
        // TODO move pieces around and test
    }

    @Test
    public void testMove() {
        Board board = new BoardImpl();
        System.out.println(board);
        board.move(new PositionImpl(6, 4), new PositionImpl(5, 4));
        System.out.println(board);
    }
}
