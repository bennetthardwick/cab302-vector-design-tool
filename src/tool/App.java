package tool;

import javax.swing.*;
import java.awt.*;

public class App extends  JFrame {
    public App() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(new Menu());
        add(new ToolBar(), BorderLayout.PAGE_START);
        add(new Canvas());

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
