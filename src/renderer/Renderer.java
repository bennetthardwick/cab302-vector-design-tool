package renderer;

import renderer.actions.Action;
import renderer.actions.Fill;
import renderer.actions.Pen;
import renderer.actions.VectorAction;
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
        try {
            this.actions = Parser.loadDocument(vecFile);
        } catch (Throwable e) {
            // #TODO Handle this error on the front end
            this.actions = new ArrayList<>();
        }
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
            switch (action.getType()) {
                case PEN:
                    pen = ((Pen) action).getPen();
                    break;
                case FILL:
                    fill = ((Fill) action).getFill();
                    break;
                case VECTOR:
                    Shape shape = ((VectorAction) action).toShape(width, height);

                    if (fill.isPresent()) {
                        graphics.setPaint(fill.get());
                        graphics.fill(shape);
                    }

                    graphics.setPaint(pen);
                    graphics.draw(shape);

                    break;
            }
        }
    }
}
