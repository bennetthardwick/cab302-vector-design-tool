package renderer;

import renderer.actions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class Renderer {
    private ArrayList<Action> actions;
    private Action preview;

    public Renderer() {
        actions = new ArrayList<>();
    }

    public void loadDocument(String vecFile) {
        try {
            this.actions = Parser.loadDocument(vecFile);
        } catch (Throwable e) {
            System.out.println(e.getMessage());

            // #TODO Handle this error on the front end
            this.actions = new ArrayList<>();
        }
    }

    public ArrayList<String> serialize() {
        ArrayList<String> lines = new ArrayList<>();

        for (Action action : actions) {
            lines.add(action.toString());
        }

        return lines;
    }

    public void undo() {
        if (actions.size() > 0) {
            actions.remove(actions.size() - 1);
        }
    }

    public void discardPreview() {
        if (preview != null) {
            preview = null;
        }
    }

    public void updatePreview(Action action) {
        preview = action;
    }

    public void commitPreview() {
        if (preview != null) {
            actions.add(preview);
            preview = null;
        }
    }

    public void setFill(Color fill) {
        if (actions.size() > 0 && actions.get(actions.size() - 1).getType() == ActionType.PEN) {
            ((Fill) actions.get(actions.size() - 1)).setColor(fill);
        } else {
            Fill f = new Fill("NONE");
            f.setColor(fill);
            actions.add(f);
        }
    }

    public void setNoFill() {
        if (actions.size() > 0 && actions.get(actions.size() - 1).getType() == ActionType.FILL) {
            ((Fill) actions.get(actions.size() - 1)).setNoFill();
        } else {
            Fill f = new Fill("NONE");
            f.setNoFill();
            actions.add(f);
        }
    }

    public void setPen(Color pen) {
        if (actions.get(actions.size() - 1).getType() == ActionType.PEN) {
            ((Pen) actions.get(actions.size() - 1)).setPen(pen);
        } else {
            Pen p = new Pen("#ffffff");
            p.setPen(pen);
            actions.add(p);
        }
    }

    public void render(Graphics2D graphics, int width, int height) {
        Optional<Color> fill = Optional.empty();
        Color pen = Color.BLACK;

        var actions = this.actions;

        if (preview != null) {
            actions = new ArrayList<Action>();
            actions.addAll(this.actions);
            actions.add(preview);
        }

        for (Action action : actions){
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
