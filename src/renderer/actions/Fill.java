package renderer.actions;

import java.awt.*;
import java.util.Optional;

public class Fill implements Action {

    public Fill(String arguments) {

    }

    public Optional<Color> getFill() {
        return Optional.empty();
    }

    public ActionType getType() {
        return ActionType.FILL;
    }
}
