package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.util.ArrayList;

public abstract class Vector {
    public abstract Shape toShape(int width, int height);

    public static Vector fromType(VectorType type, ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        switch (type) {
            case RECTANGLE:
                return new Rectangle(arguments);
            case POLYGON:
                return new Polygon(arguments);
            case ELLIPSE:
                return new Ellipse(arguments);
            case PLOT:
                return new Plot(arguments);
            case LINE:
                return new Line(arguments);
            default:
                throw new InvalidVectorArgumentsException();
        }
    }

    public static ArrayList<Float> parseVectorArguments(String arguments) throws InvalidVectorArgumentsException {
        ArrayList<Float> values = new ArrayList<>();

        try {
            for (String argument : arguments.split(" ")) {
                values.add(Float.valueOf(argument));
            }
        } catch (NumberFormatException e) {
            throw new InvalidVectorArgumentsException(arguments);
        }

        return values;

    }
}
