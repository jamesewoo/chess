package chess.model;

/**
 * A position on the board.
 */
public interface Position {

    /**
     * Returns the rank (row).
     *
     * @return the rank
     */
    public int getRank();

    /**
     * Returns the file (column).
     *
     * @return the file
     */
    public int getFile();
}
