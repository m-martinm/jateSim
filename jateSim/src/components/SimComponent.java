package components;


import components.displayComponents.DisplayComponent;
import components.gates.Gate;
import components.pins.Pin;
import components.signals.Signal;
import components.sourceComponents.SourceComponent;
import simUtils.SimLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class SimComponent implements MouseListener, MouseMotionListener
{
  public JLabel label;
  public JPanel parent;
  public JPopupMenu popupMenu;
  public JMenuItem remove = new JMenuItem("Remove component");
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
    this.remove.addActionListener((e) -> {
      this.deleteComponent();
      System.out.println("remaining gates: " + Gate.gates.size()); //TODO remove these
      System.out.println("remaining pins: " + Pin.pins.size());
      System.out.println("displays: " + DisplayComponent.displayComponents.size());
      System.out.println("sources: " + SourceComponent.sourceComponents.size());
      SimLogger.log("Overall components: " + SimComponent.allComponents.size());
      SimLogger.log("Overall signals: " + Signal.signals.size());
      System.out.println("------------------------------");
    });
    this.parent = parentPanel;
    this.parent.add(this.label);
    allComponents.add(this);
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
    parent.remove(this.label);
    allComponents.remove(this);
    parent.revalidate();
    parent.repaint();
  }

  public static void deleteAllComponents()
  {
    for(SimComponent c : allComponents) {
      c.deleteComponent();
    }
    Signal.deleteAllSignals();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(e.getButton() == MouseEvent.BUTTON3) {
      this.popupMenu = new JPopupMenu();
      this.popupMenu.add(this.remove);
      this.popupMenu.show(this.parent, this.getX() + this.label.getWidth() / 2,
                          this.getY() + this.label.getHeight() / 2);
    }
  }

  @Override
  public void mouseDragged(MouseEvent e)
  {
    Point m = MouseInfo.getPointerInfo().getLocation();
    Point c = this.parent.getLocationOnScreen();
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
