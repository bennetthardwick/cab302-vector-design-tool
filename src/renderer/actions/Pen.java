package renderer.actions;

import java.awt.*;

public class Pen implements Action {

    Color pen;

    public Pen(String arguments) {
        if (arguments.startsWith("#")) {
            pen = Color.decode(arguments);
        } else {
            pen = Color.black;
        }
    }

    public void setPen(Color pen) {
        this.pen = pen;
    }

    @Override
    public String toString() {
        return "PEN " + "#" + Integer.toHexString(pen.getRGB()).substring(2);
    }

    public ActionType getType() {
        return ActionType.PEN;
    }

    public Color getPen() {
        return pen;
    }
}
