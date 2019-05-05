package renderer;

import renderer.shapes.Shape;

import java.awt.*;

public class Renderer {
    private String pen;
    private String fill;
    private Shape[] shapes;

    public Renderer() {

    }

    public void loadDocument(String vecFile) {
        this.shapes = Parser.loadDocument(vecFile);
    }

    public void render(Graphics2D graphics) {

    }

}
