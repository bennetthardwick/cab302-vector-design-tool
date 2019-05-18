package renderer.actions;

import java.awt.*;
import java.util.Optional;

public class Fill implements Action {

    private Optional<Color> fill;

    public Fill(String arguments) {
        if (arguments.startsWith("#")) {
            fill = Optional.of(Color.decode(arguments));
        } else {
            fill = Optional.empty();
        }
    }

    public Optional<Color> getFill() {
        return fill;
    }

    public ActionType getType() {
        return ActionType.FILL;
    }
}
