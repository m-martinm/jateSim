package app;

import components.signals.Signal;
import components.signals.SignalDrawingMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ContentPanel extends JPanel implements MouseListener
{
  public static final Dimension SIZE = new Dimension(5000, 5000);

  ContentPanel()
  {
    setBackground(Color.lightGray);
    setPreferredSize(SIZE);
    setBounds(0, ControlPanel.height, SIZE.width, SIZE.height);
    setLayout(null);
    addMouseListener(this);
  }

  public void resetPanel()
  {
    setLocation(0, ControlPanel.height);
    Signal.getPanel().setLocation(0, ControlPanel.height);
  }

  public void movePanel(KeyEvent e)
  {
    switch(e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        setLocation(getX() + 10, getY());
        Signal.getPanel().setLocation(getX() + 10, getY());
        break;
      case KeyEvent.VK_RIGHT:
        setLocation(getX() - 10, getY());
        Signal.getPanel().setLocation(getX() - 10, getY());
        break;
      case KeyEvent.VK_DOWN:
        setLocation(getX(), getY() - 10);
        Signal.getPanel().setLocation(getX(), getY() - 10);
        break;
      case KeyEvent.VK_UP:
        setLocation(getX(), getY() + 10);
        Signal.getPanel().setLocation(getX(), getY() + 10);
        break;
      default:
        break;
    }

  }

  public void update()
  {
    revalidate();
    repaint();
  }

  public void update(Rectangle r)
  {
    revalidate();
    repaint(r);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for(Signal s : Signal.signals) {
      s.paintSignal(g2);
    }
  }

  private Point getPoint()
  {
    Point a = MouseInfo.getPointerInfo().getLocation();
    Point b = getLocationOnScreen();
    return new Point(a.x - b.x, a.y - b.y);
  }

  private boolean isSignalSelected(Signal s)
  {
    Point p = getPoint();
    if(s.getRect().contains(p)) {
      return true;
    }
    return false;
  }


  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(Engine.getMode() == Mode.CONNECT && e.getButton() == MouseEvent.BUTTON3) {
      for(Signal s : Signal.signals) {
        if(isSignalSelected(s)) {
          switch(s.getDrawingMethod()) {
            case LOWER:
              s.setDrawingMethod(SignalDrawingMethod.UPPER);
              break;
            case UPPER:
              s.setDrawingMethod(SignalDrawingMethod.STRAIGHT);
              break;
            case STRAIGHT:
              s.setDrawingMethod(SignalDrawingMethod.LOWER);
              break;
          }
          update(s.getRect());
          break;
        }
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {

  }

  @Override
  public void mouseReleased(MouseEvent e)
  {

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {

  }

  @Override
  public void mouseExited(MouseEvent e)
  {

  }
}
