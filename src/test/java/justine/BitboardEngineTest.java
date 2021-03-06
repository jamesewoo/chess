package justine;

import org.junit.Test;

import static justine.BitboardEngine.*;
import static justine.BitboardUtils.print;
import static org.junit.Assert.assertEquals;

/**
 * Created by evadrone on 3/8/14.
 */
public class BitboardEngineTest {
    @Test
    public void testConstructor() {
        BitboardEngine engine = new BitboardEngine();

        long whitePieces = engine.getPieceSet(getWhiteIndex());
        assertEquals(0xFFFFL, whitePieces);
        System.out.println(print(whitePieces));

        long blackPieces = engine.getPieceSet(getBlackIndex());
        assertEquals(0xFFFFL << 48, blackPieces);
        System.out.println(print(blackPieces));

        long pawns = engine.getPieceSet(getPawnIndex());
        assertEquals(0xFFL << 8 | 0xFFL << 48, pawns);
        System.out.println(print(pawns));

        long knights = engine.getPieceSet(getKnightIndex());
        assertEquals(1L << 1 | 1L << 6 | 1L << 57 | 1L << 62, knights);
        System.out.println(print(knights));

        long bishops = engine.getPieceSet(getBishopIndex());
        assertEquals(1L << 2 | 1L << 5 | 1L << 58 | 1L << 61, bishops);
        System.out.println(print(bishops));

        long rooks = engine.getPieceSet(getRookIndex());
        System.out.println(print(rooks));

        long queens = engine.getPieceSet(getQueenIndex());
        assertEquals(1L << 3 | 1L << 59, queens);
        System.out.println(print(queens));

        long kings = engine.getPieceSet(getKingIndex());
        assertEquals(1L << 4 | 1L << 60, kings);
        System.out.println(print(kings));
    }
}
