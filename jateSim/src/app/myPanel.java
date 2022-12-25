package app;

import javax.swing.*;
import java.awt.*;

/*
TODO https://stackoverflow.com/questions/5797862/draw-a-line-in-a-jpanel-with-button-click-in-java
- make separate class for buttonsPanel and contentsPanel
- make the contentsPanel sth like 5000x5000 so you can move around
 */

public class myPanel extends JPanel
{
  public myPanel(int x, int y, int w, int h, Color color, String name)
  {
    setName(name);
    setBounds(x, y, w, h);
    setBackground(color);
  }
}
