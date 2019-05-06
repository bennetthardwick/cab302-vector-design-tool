package renderer.actions;

import java.awt.*;

public class Pen implements Action {
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
