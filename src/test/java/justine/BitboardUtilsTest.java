package justine;

import org.junit.Test;

import static justine.BitboardUtils.*;

/**
 * Created by evadrone on 3/8/14.
 */
public class BitboardUtilsTest {
    @Test
    public void testPrint() {
        // testing by inspection for now
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
