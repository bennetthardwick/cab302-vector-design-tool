package renderer.actions;

import renderer.vectors.Vector;

import java.awt.*;
import java.util.Optional;

public interface Action {
    ActionType getType();
    String toString();
}
