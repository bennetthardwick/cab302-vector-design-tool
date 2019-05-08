package tool;

import renderer.Renderer;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    Renderer renderer;

    public Canvas() {
        setBackground(Color.WHITE);
        renderer = new Renderer();
        renderer.loadDocument("RECTANGLE 0.25 0.25 0.5 0.5");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        renderer.render(graphics, getWidth(), getHeight());
        graphics.dispose();
    }

}
