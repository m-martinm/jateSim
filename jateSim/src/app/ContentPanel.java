package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ContentPanel extends JPanel
{
  public static Dimension size = new Dimension(5000, 5000);

  ContentPanel()
  {
    setBackground(Color.lightGray);
    setPreferredSize(size);
    setBounds(this.getX(), this.getY(), size.width, size.height);
    setLayout(null);
  }

  public void resetPanel()
  {
    this.setLocation(0, ControlPanel.height);
  }

  public void movePanel(KeyEvent e)
  {
    switch(e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        this.setLocation(this.getX() + 10, this.getY());
        break;
      case KeyEvent.VK_RIGHT:
        this.setLocation(this.getX() - 10, this.getY());
        break;
      case KeyEvent.VK_DOWN:
        this.setLocation(this.getX(), this.getY() - 10);
        break;
      case KeyEvent.VK_UP:
        this.setLocation(this.getX(), this.getY() + 10);
        break;
      default:
        break;
    }

  }

}
