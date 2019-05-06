package renderer;

import renderer.actions.Action;
import renderer.vectors.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class Renderer {
    private ArrayList<Action> actions;

    public Renderer() {
        actions = new ArrayList<>();
    }

    public void loadDocument(String vecFile) {
        this.actions = Parser.loadDocument(vecFile);
    }

    public String serialize() {
        return "";
    }

    public void undo() {
        if (actions.size() > 0) {
            actions.remove(actions.size() - 1);
        }
    }

    public void render(Graphics2D graphics, int width, int height) {
        Optional<Color> fill = Optional.empty();
        Color pen = Color.BLACK;
        for (Action action : actions) {
            pen = action.getPen().orElse(pen);
            fill = action.getFill().orElse(fill);

            if (action.getVector().isPresent()) {
               Shape shape = action.getVector().get().toShape(width, height);

               graphics.setPaint(pen);
               graphics.draw(shape);

               if (fill.isPresent()) {
                   graphics.setPaint(fill.get());
                   graphics.fill(shape);
               }

            }
        }
    }

}
