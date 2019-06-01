package renderer.vectors;

import renderer.Point;
import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * A line vector
 */
public class Line extends Vector {

    Point<Float> from;
    Point<Float> to;

    public Line(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() != 4) {
            throw new InvalidVectorArgumentsException();
        }

        from = new Point<>(arguments.get(0), arguments.get(1));
        to = new Point<>(arguments.get(2), arguments.get(3));
    }

    @Override
    public Shape toShape(int width, int height) {
        return new Line2D.Float(
                from.x * width,
                from.y * height,
                to.x * width,
                to.y * height
        );
    }
}
