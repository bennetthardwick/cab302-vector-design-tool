package tool;

import renderer.Renderer;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    Renderer renderer;

    public Canvas() {
        setBackground(Color.WHITE);
        renderer = new Renderer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        renderer.render(graphics, getWidth(), getHeight());
        graphics.dispose();
    }

}
