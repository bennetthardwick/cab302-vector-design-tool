package renderer.actions;

import java.awt.*;

public class Pen implements Action {

    public Pen(String arguments) {

    }

    public ActionType getType() {
        return ActionType.PEN;
    }
    public String toString() {
        return "";
    }
    public Color getPen() {
        return Color.BLACK;
    }
}
