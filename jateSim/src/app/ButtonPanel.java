package app;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends myPanel {
    
    public static int height = 50;
    private static final int gap = 10;
    public ButtonPanel(int x, int y, int w, int h, Color color, String name) {
        super(x, y, w, h, color, name);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    public void addButton(JButton b){
        add(Box.createRigidArea(new Dimension(gap,0)));
        add(b);
    }
}
