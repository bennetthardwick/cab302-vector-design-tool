package renderer.vectors;

import renderer.Point;
import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.util.ArrayList;

public class Polygon extends Vector {

    private ArrayList<Point<Float>> arguments;

    public Polygon(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() % 2 != 0) {
            throw new InvalidVectorArgumentsException();
        } else {
            this.arguments = new ArrayList<>();
            var argumentsIterator = arguments.iterator();
            while (argumentsIterator.hasNext()) {
                Float first = argumentsIterator.next();
                Float second = argumentsIterator.hasNext() ? argumentsIterator.next() : null;
                if (second != null) {
                    this.arguments.add(new Point<>(first, second));
                }
            }
        }
    }

    @Override
    public Shape toShape(int width, int height) {
        var polygon = new java.awt.Polygon();
        for (Point<Float> point : arguments) {
            polygon.addPoint(
                    Math.round(point.x * width),
                    Math.round(point.y * height)
            );
        }
        return polygon;
    }

}
