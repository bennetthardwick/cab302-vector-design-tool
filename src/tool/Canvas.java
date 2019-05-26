package tool;

import javafx.util.Pair;
import renderer.Parser;
import renderer.Renderer;
import renderer.vectors.Plot;
import renderer.vectors.VectorType;
import tool.Events.UpdateEvent;
import tool.Observable.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;

public class Canvas extends JPanel implements Observer<UpdateEvent> {

    Renderer renderer;

    private ArrayList<Pair<Float, Float>> points;

    private VectorType type;

    public Canvas(Renderer renderer) {
        setBackground(Color.WHITE);
        this.renderer = renderer;
        points = new ArrayList<>();

        var handler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (type == null) return;

                Float x = mouseEvent.getX() / (float) Canvas.super.getWidth();
                Float y = mouseEvent.getY() / (float) Canvas.super.getHeight();
                Pair<Float, Float> point = new Pair<>(x, y);

                if (type == VectorType.PLOT) {
                    try {
                        renderer.updatePreview(Parser.createActionWithArguments(VectorType.PLOT.toString() + " " + point.getKey() + " " + point.getValue()));
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
                points.add(new Pair<>(x, y));

                String action = type.toString();

                for (Pair<Float, Float> point : points ) {
                    action += " " + point.getKey() + " " + point.getValue();
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
