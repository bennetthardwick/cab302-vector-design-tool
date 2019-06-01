package renderer;

import renderer.actions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The class that is in charge of rendering a "vec" file
 * to a Graphics2D object.
 */
public class Renderer {
    private ArrayList<Action> actions;
    private Action preview;

    public Renderer() {
        actions = new ArrayList<>();
    }

    /**
     * Load a "vec" file into the current Renderer instance
     * @param vecFile
     */
    public void loadDocument(String vecFile) {
        try {
            this.actions = Parser.loadDocument(vecFile);
        } catch (Throwable e) {
            System.out.println(e.getMessage());

            // #TODO Handle this error on the front end
            this.actions = new ArrayList<>();
        }
    }

    /**
     * Serialize the actions to a list of ActionStrings
     * @return
     */
    public ArrayList<String> serialize() {
        ArrayList<String> lines = new ArrayList<>();

        for (Action action : actions) {
            lines.add(action.toString());
        }

        return lines;
    }

    /**
     * Remove the latest action
     */
    public void undo() {
        if (actions.size() > 0) {
            actions.remove(actions.size() - 1);
        }
    }

    /**
     * Remove the preview Action from the Renderer
     */
    public void discardPreview() {
        if (preview != null) {
            preview = null;
        }
    }

    /**
     * Add an action to act as a preview
     * @param action
     */
    public void updatePreview(Action action) {
        preview = action;
    }

    /**
     * Take the preview (if it exists) and add it to the list of actions
     */
    public void commitPreview() {
        if (preview != null) {
            actions.add(preview);
            preview = null;
        }
    }

    /**
     * Set the fill color
     * @param fill the fill color
     */
    public void setFill(Color fill) {
        if (actions.size() > 0 && actions.get(actions.size() - 1).getType() == ActionType.PEN) {
            ((Fill) actions.get(actions.size() - 1)).setColor(fill);
        } else {
            Fill f = new Fill("NONE");
            f.setColor(fill);
            actions.add(f);
        }
    }

    /**
     * Set the "fill color" to "NONE"
     */
    public void setNoFill() {
        if (actions.size() > 0 && actions.get(actions.size() - 1).getType() == ActionType.FILL) {
            ((Fill) actions.get(actions.size() - 1)).setNoFill();
        } else {
            Fill f = new Fill("NONE");
            f.setNoFill();
            actions.add(f);
        }
    }

    /**
     * Set the pen color
     * @param pen
     */
    public void setPen(Color pen) {
        if (actions.get(actions.size() - 1).getType() == ActionType.PEN) {
            ((Pen) actions.get(actions.size() - 1)).setPen(pen);
        } else {
            Pen p = new Pen("#ffffff");
            p.setPen(pen);
            actions.add(p);
        }
    }

    /**
     * Render the current list of actions to a Graphics2D object
     * @param graphics the object to draw the actions to
     * @param width the width of the object
     * @param height ths height of the object
     */
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
