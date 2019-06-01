package renderer.actions;

import java.awt.*;
import java.util.Optional;

/**
 * The "FILL" action
 */
public class Fill implements Action {

    private Optional<Color> fill;

    public Fill(String arguments) {
        if (arguments.startsWith("#")) {
            fill = Optional.of(Color.decode(arguments));
        } else {
            fill = Optional.empty();
        }
    }

    public void setColor(Color color) {
        fill = Optional.of(color);
    }

    public void setNoFill() {
        fill = Optional.empty();
    }

    public Optional<Color> getFill() {
        return fill;
    }

    public ActionType getType() {
        return ActionType.FILL;
    }

    @Override
    public String toString() {
        return "FILL " + (fill.isPresent() ? "#" + Integer.toHexString(fill.get().getRGB()).substring(2) : "NONE");
    }
}
