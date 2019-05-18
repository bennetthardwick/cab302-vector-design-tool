package renderer.vectors;

import javafx.util.Pair;
import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Line extends Vector {

    Pair<Float, Float> from;
    Pair<Float, Float> to;

    public Line(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() != 4) {
            throw new InvalidVectorArgumentsException();
        }

        from = new Pair<>(arguments.get(0), arguments.get(1));
        to = new Pair<>(arguments.get(2), arguments.get(3));
    }

    @Override
    public Shape toShape(int width, int height) {
        return new Line2D.Float(
                from.getKey() * width,
                from.getValue() * height,
                to.getKey() * width,
                to.getValue() * height
        );
    }
}
