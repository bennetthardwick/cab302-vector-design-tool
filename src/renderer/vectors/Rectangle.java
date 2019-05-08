package renderer.vectors;

import renderer.errors.InvalidVectorArgumentsException;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Vector {

    private Float width;
    private Float height;
    private Float x;
    private Float y;

    static void ensureValidArguments(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        if (arguments.size() != 4) {
            throw new InvalidVectorArgumentsException();
        }
    }

    public Rectangle(ArrayList<Float> arguments) throws InvalidVectorArgumentsException {
        Rectangle.ensureValidArguments(arguments);
        x = arguments.get(0);
        y = arguments.get(1);
        width = arguments.get(2);
        height = arguments.get(3);
    }

    @Override
    public Shape toShape(int width, int height) {
        var x = Math.round(this.x * width);
        var y = Math.round(this.y * height);
        var normWidth = Math.round(this.width * width);
        var normHeight = Math.round(this.height * height);
        return new java.awt.Rectangle(x, y, normWidth, normHeight);
    }
}
