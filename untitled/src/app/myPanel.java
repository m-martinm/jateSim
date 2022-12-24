package app;

import javax.swing.*;
import java.awt.*;

public class myPanel extends JPanel {
    public myPanel(int x, int y, int w, int h, Color color, String name){
        setName(name);
        setBounds(x, y, w, h);
        setBackground(color);
    }
}
