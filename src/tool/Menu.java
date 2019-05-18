package tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JMenuBar implements ActionListener {

    JMenuItem open;

    public Menu() {
        this.createFileActions();
    }

    private void createFileActions() {
        var fileMenu = new JMenu("File");

        open = new JMenuItem("Open");
        open.addActionListener(this);

        fileMenu.add(open);

        this.add(fileMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            var fileChooser = new JFileChooser();
            var returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
            }

        }
    }


}
