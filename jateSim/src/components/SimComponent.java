package components;


import app.Engine;
import components.signals.Signal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class SimComponent implements MouseListener, MouseMotionListener
{
  private JLabel label;
  private JPanel parent;
  public static ArrayList<SimComponent> allComponents = new ArrayList<>();

  public SimComponent(int x, int y, int w, int h, String text, JPanel parentPanel)
  {
    this.label = new JLabel(text, SwingConstants.CENTER);
    this.label.setBackground(Color.gray);
    this.label.setOpaque(true);
    this.label.setBounds(x, y, w, h);
    this.label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.label.addMouseListener(this);
    this.label.addMouseMotionListener(this);
    this.parent = parentPanel;
    this.parent.add(this.label);
    allComponents.add(this);
  }

  public JLabel getLabel()
  {
    return this.label;
  }

  public JPanel getParent()
  {
    return this.parent;
  }

  public Rectangle getRect()
  {
    return new Rectangle(getX(), getY(), getW(), getH());
  }

  public int getX()
  {
    return this.label.getX();
  }

  public int getY()
  {
    return this.label.getY();
  }

  public int getW()
  {
    return this.label.getWidth();
  }

  public int getH()
  {
    return this.label.getHeight();
  }

  public void setLocation(int x, int y)
  {

  }

  // always should call super.deleteComponent()
  public void deleteComponent()
  {
    getParent().remove(getLabel());
    allComponents.remove(this);
    getParent().revalidate();
    getParent().repaint();
  }

  public static void deleteAllComponents()
  {
    for(SimComponent c : allComponents) {
      c.getParent().remove(c.getLabel());
    }
    Engine.getContentPanel().revalidate();
    Engine.getContentPanel().repaint();
    allComponents.clear();
    Signal.deleteAllSignals();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(e.getButton() == MouseEvent.BUTTON3) {
      JMenuItem remove = new JMenuItem("Remove component");
      remove.addActionListener(a -> deleteComponent());
      JPopupMenu popupMenu = new JPopupMenu();
      popupMenu.add(remove);
      popupMenu.show(getParent(), getX() + getLabel().getWidth() / 2, getY() + getLabel().getHeight() / 2);
    }
  }

  @Override
  public void mouseDragged(MouseEvent e)
  {
    Point m = MouseInfo.getPointerInfo().getLocation();
    Point c = getParent().getLocationOnScreen();
    setLocation(m.x - c.x, m.y - c.y);
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

  @Override
  public void mouseMoved(MouseEvent e)
  {

  }
}
