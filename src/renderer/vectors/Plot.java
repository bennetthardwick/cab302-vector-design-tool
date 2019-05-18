package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Plot extends Vector {

    private Float x;
    private Float y;

    public Plot(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() != 2) {
            throw new InvalidVectorArgumentsException();
        }

        x = arguments.get(0);
        y = arguments.get(1);
    }

    @Override
    public Shape toShape(int width, int height) {
        x = this.x * width;
        y = this.y * y;
        return new Line2D.Float(
                x,
                y,
                x,
                y
        );
    }
}
