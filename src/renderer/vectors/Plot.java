package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.awt.Rectangle;
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
        var x = this.x * width;
        var y = this.y * height;

        return new Line2D.Float(
                x,
                y,
                x,
                y
        );
    }
}
