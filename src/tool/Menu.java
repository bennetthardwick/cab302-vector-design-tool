package tool;

import javax.swing.*;

public class Menu extends JMenuBar {
    public Menu() {
        this.createFileActions();
    }

    private void createFileActions() {
        var fileMenu = new JMenu("File");

        this.add(fileMenu);
    }

}
