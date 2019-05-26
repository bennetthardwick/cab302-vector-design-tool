package tool;

import tool.Events.UpdateEvent;
import tool.Events.UpdateEventType;
import tool.Observable.Observer;
import tool.Observable.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;

public class Menu extends JMenuBar implements ActionListener {

    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem exit;
    private Subject<UpdateEvent> subject;

    public Menu() {
        this.createFileActions();
        subject = new Subject<>();
    }

    private void createFileActions() {
        var fileMenu = new JMenu("File");

        open = new JMenuItem("Open");
        open.addActionListener(this);

        save = new JMenuItem("Save As");
        save.addActionListener(this);

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(exit);

        this.add(fileMenu);
    }

    public void attach(Observer<UpdateEvent> observer) {
        subject.attach(observer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            var fileChooser = new JFileChooser();
            var returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    subject.next(new UpdateEvent(UpdateEventType.OPEN, Files.readString(file.toPath())));
                } catch (Throwable f) {
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred when reading the file!");
                }
            }

        } else if (e.getSource() == save) {
            var fileChooser = new JFileChooser();
            var returnValue = fileChooser.showSaveDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                subject.next(new UpdateEvent(UpdateEventType.SAVE, file));
            }
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }


}
