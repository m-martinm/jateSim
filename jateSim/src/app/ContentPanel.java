package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ContentPanel extends JPanel
{
  public static final Dimension SIZE = new Dimension(5000, 5000);

  ContentPanel()
  {
    setBackground(Color.lightGray);
    setPreferredSize(SIZE);
    setBounds(0, ControlPanel.height, SIZE.width, SIZE.height);
    setLayout(null);
  }

  public void resetPanel()
  {
    setLocation(0, ControlPanel.height);
  }

  public void movePanel(KeyEvent e)
  {
    switch(e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        this.setLocation(getX() + 10, getY());
        break;
      case KeyEvent.VK_RIGHT:
        this.setLocation(getX() - 10, getY());
        break;
      case KeyEvent.VK_DOWN:
        this.setLocation(getX(), getY() - 10);
        break;
      case KeyEvent.VK_UP:
        this.setLocation(getX(), getY() + 10);
        break;
      default:
        break;
    }

  }

}
