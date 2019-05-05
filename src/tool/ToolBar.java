package tool;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        setFloatable(false);

        JButton example = new JButton("button");

        add(example);
        addSeparator();
    }
}
