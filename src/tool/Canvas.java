package tool;

import renderer.Parser;
import renderer.Point;
import renderer.Renderer;
import renderer.vectors.VectorType;
import tool.Events.UpdateEvent;
import tool.Observable.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Canvas extends JPanel implements Observer<UpdateEvent> {

    private Renderer renderer;
    private JPanel container;
    private ArrayList<Point<Float>> points;
    private VectorType type;

    @Override
    public Dimension getPreferredSize() {
        Dimension d;
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 10);
        }
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);
        return new Dimension(s, s);
    }

    /**
     * A blank component that uses a Renderer to display an image.
     * Maintains a 1:1 aspect ratio.
     * @param renderer
     */
    public Canvas(Renderer renderer) {
        container = new JPanel(new GridBagLayout());
        container.setBackground(Color.GRAY);
        container.add(this);

        setBackground(Color.WHITE);
        this.renderer = renderer;
        points = new ArrayList<>();

        var handler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (type == null) return;

                Float x = mouseEvent.getX() / (float) Canvas.super.getWidth();
                Float y = mouseEvent.getY() / (float) Canvas.super.getHeight();
                Point<Float> point = new Point<>(x, y);

                if (type == VectorType.PLOT) {
                    try {
                        renderer.updatePreview(Parser.createActionWithArguments(VectorType.PLOT.toString() + " " + point.x + " " + point.y));
                        Canvas.super.repaint();
                        renderer.commitPreview();
                    } catch (Throwable e) {
                        System.out.println(e.getMessage());
                    }
                    points = new ArrayList<>();
                    return;
                }

                if (points.size() >= 2) {
                    if (type == VectorType.POLYGON && SwingUtilities.isRightMouseButton(mouseEvent)) {
                        renderer.commitPreview();
                        points = new ArrayList<>();
                        return;
                    } else if (type != VectorType.POLYGON) {
                        renderer.commitPreview();
                        points = new ArrayList<>();
                        return;
                    }
                }


                if (type == VectorType.POLYGON) {
                    points.add(point);
                    points.add(point);
                } else {
                    points = new ArrayList<>();
                    points.add(point);
                    points.add(point);
                }
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                this.mouseMoved(mouseEvent);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                if (type == null || points.size() < 2) return;

                Float x = mouseEvent.getX() / (float) Canvas.super.getWidth();
                Float y = mouseEvent.getY() / (float) Canvas.super.getHeight();

                points.remove(points.size() - 1);
                points.add(new Point<>(x, y));

                String action = type.toString();

                for (Point<Float> point : points ) {
                    action += " " + point.x + " " + point.y;
                }

                try {
                    renderer.updatePreview(Parser.createActionWithArguments(action));
                    Canvas.super.repaint();
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        addMouseListener(handler);
        addMouseMotionListener(handler);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        renderer.render(graphics, getWidth(), getHeight());
        graphics.dispose();
    }

    private void saveToFile(File file) {
        var lines = renderer.serialize();
        try {
            FileWriter writer = new FileWriter(file);
            for (String line : lines) {
                writer.append(line);
                writer.append('\n');
            }
            writer.close();
        } catch (Throwable e) { }
    }

    /**
     * Update the state of the Renderer based on certain events
     * @param e an update event
     */
    @Override
    public void update(UpdateEvent e) {
        switch (e.type) {
            case UNDO:
                renderer.undo();
                break;
            case SELECT_VECTOR:
                type = e.vector;
                break;
            case CANCEL_SELECT:
                renderer.discardPreview();
                points = new ArrayList<>();
                type = null;
                break;
            case SELECT_PEN:
                renderer.setPen(e.color);
                break;
            case SELECT_FILL:
                renderer.setFill(e.color);
                break;
            case SELECT_NO_FILL:
                renderer.setNoFill();
                break;
            case OPEN:
                renderer.loadDocument(e.document);
                break;
            case SAVE:
                this.saveToFile(e.file);
        }

        this.repaint();
    }

}
