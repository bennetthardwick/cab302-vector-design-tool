package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Vector {

    private BoundingBox box;

    static void ensureValidArguments(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() != 4) {
            throw new InvalidVectorArgumentsException();
        }
    }

    public Rectangle(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        Rectangle.ensureValidArguments(arguments);

        box = new BoundingBox(
                arguments.get(0),
                arguments.get(1),
                arguments.get(2),
                arguments.get(3)
        );

    }

    @Override
    public Shape toShape(int width, int height) {
        return new java.awt.Rectangle(
                Math.round(box.getTopX() * width),
                Math.round(box.getTopY() * height),
                Math.round(box.getWidth() * width),
                Math.round(box.getHeight() * height)
        );
    }
}
