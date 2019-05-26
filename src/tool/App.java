package tool;

import renderer.Renderer;

import javax.swing.*;
import java.awt.*;

public class App extends  JFrame {

    private Renderer renderer;

    private Menu menu;
    private ToolBar toolbar;
    private Canvas canvas;

    public App() {
        renderer = new Renderer();

        renderer.loadDocument("RECTANGLE 0.25 0.25 0.75 0.75");

        canvas = new Canvas(renderer);

        menu = new Menu();
        toolbar = new ToolBar();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(menu);
        add(toolbar, BorderLayout.PAGE_START);
        add(canvas);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        this.listenToEvents();
    }

    private void listenToEvents() {
        toolbar.attach(canvas);
        menu.attach(canvas);
    }

}
