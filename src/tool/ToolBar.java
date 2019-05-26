package tool;

import renderer.vectors.VectorType;
import tool.Events.UpdateEvent;
import tool.Events.UpdateEventType;
import tool.Observable.Observer;
import tool.Observable.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class ToolBar extends JToolBar implements ActionListener {

    private Map<JButton, VectorType> buttons;
    private JButton fill;
    private JButton noFill;
    private JButton pen;

    private boolean isActing = false;
    private JButton cancelAction;

    private Subject<UpdateEvent> subject;

    private JButton undo;

    public ToolBar() {
        this.subject = new Subject<>();

        setFloatable(false);
        this.createButtons();
        addSeparator();
    }

    public void attach(Observer<UpdateEvent> observer) {
        this.subject.attach(observer);
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

        noFill = new JButton("NO FILL");
        noFill.addActionListener(this);

        add(pen);
        addSeparator();
        add(fill);
        addSeparator();
        add(noFill);

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
            var type = buttons.get(source);
            subject.next(new UpdateEvent(UpdateEventType.SELECT_VECTOR, type));
        } else if (source == pen) {
            Color color = JColorChooser.showDialog(this, "Choose Pen Color", Color.white);
            if (color != null) {
                subject.next(new UpdateEvent(UpdateEventType.SELECT_PEN, color));
            }
        } else if (source == fill) {
            Color color = JColorChooser.showDialog(this, "Choose Fill Color", Color.white);
            if (color != null) {
                subject.next(new UpdateEvent(UpdateEventType.SELECT_FILL, color));
            }
        } else if (source == undo) {
            subject.next(new UpdateEvent(UpdateEventType.UNDO));
            isActing = false;
        } else if (source == noFill) {
            subject.next(new UpdateEvent(UpdateEventType.SELECT_NO_FILL));
        } else if (source == cancelAction) {
            subject.next(new UpdateEvent(UpdateEventType.CANCEL_SELECT));
            isActing = false;
        }

        cancelAction.setVisible(isActing);
    }

}
