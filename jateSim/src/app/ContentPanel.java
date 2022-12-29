package app;

import components.signals.Signal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ContentPanel extends JPanel implements MouseListener, MouseMotionListener
{
  public static final Dimension SIZE = new Dimension(5000, 5000);

  private Point startPoint;

  ContentPanel()
  {
    setBackground(Color.lightGray);
    setPreferredSize(SIZE);
    setBounds(0, ControlPanel.height, SIZE.width, SIZE.height);
    setLayout(null);
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public void resetPanel()
  {
    setLocation(0, ControlPanel.height);
    Signal.getPanel().setLocation(0, ControlPanel.height);
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


  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(Engine.getMode() == Mode.CONNECT && e.getButton() == MouseEvent.BUTTON3) {
      for(Signal s : Signal.signals) {
        if(s.isSelected(getPoint())) {
          s.changeDrawingMethod();
          update(s.getRect());
          break;
        }
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if(Engine.getMode() == Mode.MOVE) {
      startPoint = e.getPoint();
      setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    if(Engine.getMode() == Mode.MOVE) setCursor(Cursor.getDefaultCursor());
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {

  }

  @Override
  public void mouseExited(MouseEvent e)
  {

  }

  @Override
  public void mouseDragged(MouseEvent e)
  {
    if(Engine.getMode() == Mode.MOVE) {
      Point p = getLocation();
      setLocation(p.x + e.getX() - startPoint.x, p.y + e.getY() - startPoint.y);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e)
  {

  }
}
