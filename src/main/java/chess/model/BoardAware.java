package chess.model;

/**
 * Interface to implement for objects that need a reference to a board.
 */
public interface BoardAware {

    public void setBoard(Board board);
}
