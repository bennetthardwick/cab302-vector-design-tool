package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * An ellipse vector
 */
public class Ellipse extends Vector {

    private BoundingBox box;

    public Ellipse(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() != 4) {
            throw new InvalidVectorArgumentsException();
        }

        box = new BoundingBox(
                arguments.get(0),
                arguments.get(1),
                arguments.get(2),
                arguments.get(3)
        );

    }

    @Override
    public Shape toShape(int width, int height) {
        return new Ellipse2D.Double(
                Math.round(box.getTopX() * width),
                Math.round(box.getTopY() * height),
                Math.round(box.getWidth() * width),
                Math.round(box.getHeight() * height)
        );
    }
}
