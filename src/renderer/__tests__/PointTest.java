package renderer.__tests__;

import org.junit.jupiter.api.Test;
import renderer.Point;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void testFloatPoint() {
        var x = 0.5f;
        var y = 0.125f;
        var point = new Point<Float>(x, y);
        assertEquals(point.x, x);
        assertEquals(point.y, y);
    }

    @Test
    void testDoublePoint() {
        var x = 0.5;
        var y = 0.125;
        var point = new Point<Double>(x, y);
        assertEquals(point.x, x);
        assertEquals(point.y, y);
    }
}