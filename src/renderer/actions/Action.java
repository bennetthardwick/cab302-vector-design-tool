package renderer.actions;

import renderer.vectors.Vector;

import java.awt.*;
import java.util.Optional;

public interface Action {
    Optional<Vector> getVector();
    Optional<Optional<Color>> getFill();
    Optional<Color> getPen();
    String toString();
}
