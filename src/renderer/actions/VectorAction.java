package renderer.actions;

import renderer.errors.InvalidVectorArgumentsException;
import renderer.vectors.Vector;
import renderer.vectors.VectorType;

import java.awt.*;

/**
 * A
 */
public class VectorAction implements Action {

    private Vector vector;
    private String arguments;
    private VectorType type;

    public VectorAction(VectorType type, String arguments) throws InvalidVectorArgumentsException {
        this.arguments = arguments;
        this.type = type;
        this.vector = Vector.fromType(type, Vector.parseVectorArguments(arguments));
    }

    public ActionType getType() {
        return ActionType.VECTOR;
    }

    public String toString() {
        return type.toString() + ' ' + arguments;
    }

    public Shape toShape(int width, int height) {
        return this.vector.toShape(width, height);
    }
}
