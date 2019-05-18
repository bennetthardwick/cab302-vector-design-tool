package tool;

import renderer.Renderer;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    Renderer renderer;

    public Canvas() {
        setBackground(Color.WHITE);
        renderer = new Renderer();

        renderer.loadDocument("ELLIPSE 0.0 0.0 1.0 1.0");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        renderer.render(graphics, getWidth(), getHeight());
        graphics.dispose();
    }

}
