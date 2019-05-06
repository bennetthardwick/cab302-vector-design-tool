package renderer.actions;

import java.awt.*;

public class Vector implements Action {
    public Vector() {

    }

    public ActionType getType() {
        return ActionType.VECTOR;
    }

    public String toString() {
        return "";
    }

    public Shape toShape(int width, int height) {
        return new Rectangle(0, 0, width / 2, height / 2);
    }
}
