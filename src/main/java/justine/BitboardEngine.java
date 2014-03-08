package justine;

import static java.lang.Long.toBinaryString;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;

/**
 * A little endian rank-file representation of a board.
 */
public class BitboardEngine implements ChessEngine {

    private static final long FILE_A = 0x0101010101010101L;
    private static final long FILE_H = 0x8080808080808080L;
    private static final long RANK_ONE = 0x00000000000000FFL;
    private static final long RANK_EIGHT = 0xFF00000000000000L;
    private static final long DIAGONAL = 0x8040201008040201L;
    private static final long ANTI_DIAGONAL = 0x0102040810204080L;
    private static final long LIGHT_SQUARES = 0x55AA55AA55AA55AAL;
    private static final long DARK_SQUARES = 0xAA55AA55AA55AA55L;

    private enum BbIndex {
        N_WHITE(0),
        N_BLACK(1),
        N_PAWN(2),
        N_KNIGHT(3),
        N_BISHOP(4),
        N_ROOK(5),
        N_QUEEN(6),
        N_KING(7);

        private final int value;

        BbIndex(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private BbIndex getIndex(PieceType pieceType) {
        switch (pieceType) {
            case PAWN:
                return BbIndex.N_PAWN;
            case KNIGHT:
                return BbIndex.N_KNIGHT;
            case BISHOP:
                return BbIndex.N_BISHOP;
            case ROOK:
                return BbIndex.N_ROOK;
            case QUEEN:
                return BbIndex.N_QUEEN;
            case KING:
                return BbIndex.N_KING;
            default:
                return null;
        }
    }

    private BbIndex getIndex(ColorType colorType) {
        switch (colorType) {
            case WHITE:
                return BbIndex.N_WHITE;
            case BLACK:
                return BbIndex.N_BLACK;
            default:
                return null;
        }
    }

    private final long[] pieceBB = new long[8];

    private long getOccupiedBb() {
        return pieceBB[BbIndex.N_WHITE.getValue()] | pieceBB[BbIndex.N_BLACK.getValue()];
    }

    private long getEmptyBb() {
        return ~getOccupiedBb();
    }

//    private long getPawnAttacks() {
//        return ((pawns << 7) & ~FILE_A) & ((pawns << 9) & ~FILE_H);
//    }

    public long getPieceSet(PieceType pieceType, ColorType colorType) {
        return pieceBB[getIndex(pieceType).getValue()] & pieceBB[getIndex(colorType).getValue()];
    }

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
