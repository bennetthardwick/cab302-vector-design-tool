package tool;

import renderer.Renderer;
import tool.Events.UpdateEvent;
import tool.Events.UpdateEventType;
import tool.Observable.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App extends JFrame implements KeyListener {

    private Renderer renderer;

    private Menu menu;
    private ToolBar toolbar;
    private Canvas canvas;

    private Subject<UpdateEvent> subject;

    public App() {
        renderer = new Renderer();
        subject = new Subject<>();

        renderer.loadDocument("RECTANGLE 0.25 0.25 0.75 0.75");

        canvas = new Canvas(renderer);

        menu = new Menu();
        toolbar = new ToolBar();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(menu);
        add(toolbar, BorderLayout.PAGE_START);
        add(canvas.container);

        setPreferredSize(new Dimension(400, 400));

        setLocationRelativeTo(null);
        pack();

        addKeyListener(this);

        setVisible(true);
        setFocusable(true);

        this.listenToEvents();
    }

    private void listenToEvents() {
        toolbar.attach(canvas);
        menu.attach(canvas);
        subject.attach(canvas);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_Z && keyEvent.isControlDown()) {
            subject.next(new UpdateEvent(UpdateEventType.UNDO));
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            subject.next(new UpdateEvent(UpdateEventType.CANCEL_SELECT));
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }

}
