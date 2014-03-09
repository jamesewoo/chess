package justine;

import static java.lang.Long.toBinaryString;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;

/**
 * Created by evadrone on 3/8/14.
 */
public class BitboardUtils {

    private static final long FILE_A = 0x0101010101010101L;
    private static final long FILE_H = 0x8080808080808080L;
    private static final long RANK_ONE = 0x00000000000000FFL;
    private static final long RANK_EIGHT = 0xFF00000000000000L;
    private static final long DIAGONAL = 0x8040201008040201L;
    private static final long ANTI_DIAGONAL = 0x0102040810204080L;
    private static final long LIGHT_SQUARES = 0x55AA55AA55AA55AAL;
    private static final long DARK_SQUARES = 0xAA55AA55AA55AA55L;

    /**
     * Prints a little endian rank-file representation of the given bitboard.  Square 'a1' is printed in the bottom
     * left, and 'h8' is printed in the top right.
     *
     * @param bitboard the bitboard
     * @return a little endian rank-file representation of the bitboard
     */
    public static String print(long bitboard) {
        StringBuilder builder = new StringBuilder();
        String str = format("%64s", toBinaryString(bitboard)).replace(" ", "0");
        for (int i = 0; i < str.length(); i += 8) {
            builder.append(new StringBuilder(str.substring(i, i + 8)).reverse());
            builder.append(lineSeparator());
        }
        return builder.toString();
    }

    public static long getFileA() {
        return FILE_A;
    }

    public static long getFileH() {
        return FILE_H;
    }

    public static long getRankOne() {
        return RANK_ONE;
    }

    public static long getRankEight() {
        return RANK_EIGHT;
    }

    public static long getDiagonal() {
        return DIAGONAL;
    }

    public static long getAntiDiagonal() {
        return ANTI_DIAGONAL;
    }

    public static long getLightSquares() {
        return LIGHT_SQUARES;
    }

    public static long getDarkSquares() {
        return DARK_SQUARES;
    }
}
