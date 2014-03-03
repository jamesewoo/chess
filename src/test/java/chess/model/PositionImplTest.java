package chess.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by evadrone on 3/3/14.
 */
public class PositionImplTest {

    @Test
    public void testEquals() {
        Position p1 = new PositionImpl(1, 1);
        Position p2 = new PositionImpl(1, 1);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}
