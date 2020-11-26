package model;

import model.Direction;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DirectionTest {
    @Test
    public void testDirectionReverse() {
        Direction up = Direction.UP;
        Direction down = Direction.DOWN;
        Direction left = Direction.LEFT;
        Direction right = Direction.RIGHT;

        assertEquals(Direction.DOWN, up.reverse());
        assertEquals(Direction.UP, down.reverse());
        assertEquals(Direction.RIGHT, left.reverse());
        assertEquals(Direction.LEFT, right.reverse());
    }
}
