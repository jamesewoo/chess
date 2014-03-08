package chess.model;

/**
 * Created by evadrone on 3/3/14.
 */
public enum Color {
    WHITE(0),
    BLACK(1);

    private final byte value;

    Color(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }
}
