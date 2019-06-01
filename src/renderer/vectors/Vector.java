package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.util.ArrayList;

/**
 * The base class for the different types of "Vector" action.
 */
public abstract class Vector {
    /**
     * Convert a vector to a shape
     * @param width width of the canvas
     * @param height height of the canvas
     * @return
     */
    public abstract Shape toShape(int width, int height);

    /**
     * Create a Vector instance based on the VectorType
     * @param type the type of Vector
     * @param arguments the arguments
     * @return an instance of Vector
     * @throws InvalidVectorArgumentsException
     */
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

    /**
     * Convert a string of arguments to a list of arguments
     * @param arguments
     * @return
     * @throws InvalidVectorArgumentsException
     */
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
