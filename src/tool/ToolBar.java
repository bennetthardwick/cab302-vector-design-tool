package tool;

import renderer.vectors.VectorType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class ToolBar extends JToolBar implements ActionListener {

    private Map<JButton, VectorType> buttons;
    private JButton fill;
    private JButton pen;

    private boolean isActing = false;
    private JButton cancelAction;

    private JButton undo;

    public ToolBar() {
        setFloatable(false);

        this.createButtons();

        addSeparator();
    }

    private void createButtons() {

        undo = new JButton("UNDO");
        undo.addActionListener(this);
        add(undo);

        addSeparator();
        addSeparator();

        buttons = new HashMap<>();
        EnumSet.allOf(VectorType.class).forEach(type -> {
            var button = new JButton(type.toString());
            buttons.put(button, type);
            button.addActionListener(this);
            add(button);
            addSeparator();
        });

        addSeparator();
        addSeparator();

        pen = new JButton("PEN");
        pen.addActionListener(this);
        fill = new JButton("FILL");
        fill.addActionListener(this);

        add(pen);
        addSeparator();
        add(fill);

        addSeparator();
        addSeparator();

        cancelAction = new JButton("CANCEL");
        cancelAction.setVisible(false);
        cancelAction.addActionListener(this);
        add(cancelAction);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();

        isActing = true;

        if (buttons.containsKey(source)) {
        } else if (source == pen) {
        } else if (source == fill) {
        } else {
            isActing = false;
        }

        cancelAction.setVisible(isActing);
    }

}
