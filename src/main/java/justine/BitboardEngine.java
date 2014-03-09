package justine;

import static justine.BitboardUtils.*;
import static justine.ColorType.BLACK;
import static justine.ColorType.WHITE;
import static justine.PieceType.PAWN;

/**
 * A little endian rank-file representation of a board.
 */
public class BitboardEngine implements ChessEngine {

    private static final int N_WHITE = 0;
    private static final int N_BLACK = 1;
    private static final int N_PAWN = 2;
    private static final int N_KNIGHT = 3;
    private static final int N_BISHOP = 4;
    private static final int N_ROOK = 5;
    private static final int N_QUEEN = 6;
    private static final int N_KING = 7;

    private static int getIndex(PieceType pieceType) {
        switch (pieceType) {
            case PAWN:
                return N_PAWN;
            case KNIGHT:
                return N_KNIGHT;
            case BISHOP:
                return N_BISHOP;
            case ROOK:
                return N_ROOK;
            case QUEEN:
                return N_QUEEN;
            case KING:
                return N_KING;
            default:
                return -1;
        }
    }

    private static int getIndex(ColorType colorType) {
        switch (colorType) {
            case WHITE:
                return N_WHITE;
            case BLACK:
                return N_BLACK;
            default:
                return -1;
        }
    }

    private final long[] pieceSet = new long[8];

    public BitboardEngine() {
        pieceSet[0] = getRankOne() << 8 | getRankOne();
        pieceSet[1] = getRankEight() | getRankEight() >>> 8;
        pieceSet[2] = getRankOne() << 8 | getRankEight() >>> 8;
        pieceSet[3] = 1L << 1 | 1L << 6 | 1L << 57 | 1L << 62;
        pieceSet[4] = 1L << 2 | 1L << 5 | 1L << 58 | 1L << 61;
        pieceSet[5] = 1L | 1L << 7 | 1L << 56 | 1L << 63;
        pieceSet[6] = 1L << 3 | 1L << 59;
        pieceSet[7] = 1L << 4 | 1L << 60;
    }

    long getPieceSet(int index) {
        return pieceSet[index];
    }

    long getPieceSet(ColorType colorType, PieceType pieceType) {
        return pieceSet[getIndex(colorType)] & pieceSet[getIndex(pieceType)];
    }

    long getOccupiedBb() {
        return pieceSet[N_WHITE] | pieceSet[N_BLACK];
    }

    long getEmptyBb() {
        return ~getOccupiedBb();
    }

    long getWhitePawnAttacks() {
        return ((getPieceSet(WHITE, PAWN) << 7) & ~getFileH()) | ((getPieceSet(WHITE, PAWN) << 9) & ~getFileA());
    }

    long getBlackPawnAttacks() {
        return ((getPieceSet(BLACK, PAWN) >>> 7) & ~getFileH()) | ((getPieceSet(BLACK, PAWN) >>> 9) & ~getFileA());
    }

    static int getWhiteIndex() {
        return N_WHITE;
    }

    static int getBlackIndex() {
        return N_BLACK;
    }

    static int getPawnIndex() {
        return N_PAWN;
    }

    static int getKnightIndex() {
        return N_KNIGHT;
    }

    static int getBishopIndex() {
        return N_BISHOP;
    }

    static int getRookIndex() {
        return N_ROOK;
    }

    static int getQueenIndex() {
        return N_QUEEN;
    }

    static int getKingIndex() {
        return N_KING;
    }
}
