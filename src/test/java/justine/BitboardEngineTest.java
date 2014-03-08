package justine;

import org.junit.Test;

import static justine.BitboardEngine.*;

/**
 * Created by evadrone on 3/8/14.
 */
public class BitboardEngineTest {
    @Test
    public void testPrint() {
        System.out.println(print(getFileA()));
        System.out.println(print(getFileH()));
        System.out.println(print(getRankOne()));
        System.out.println(print(getRankEight()));
        System.out.println(print(getDiagonal()));
        System.out.println(print(getAntiDiagonal()));
        System.out.println(print(getLightSquares()));
        System.out.println(print(getDarkSquares()));
    }
}
