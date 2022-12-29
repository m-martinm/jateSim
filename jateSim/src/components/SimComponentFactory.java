package components;

import app.ContentPanel;
import app.Engine;
import components.boxer.BlackBox;
import components.displayComponents.BitDisplay;
import components.gates.*;
import components.sourceComponents.SwitchSource;

import java.awt.*;

public class SimComponentFactory
{
  private static final ContentPanel p = Engine.getContentPanel();

  public static void removeAllComponents()
  {
    SimComponent.deleteAllComponents();
  }

  public static void removeSelectedItems()
  {
    for(SimComponent c : SimComponent.selectedComponents) {
      c.deleteComponent();
    }
  }

  public static void createBlackBox()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    BlackBox.factory(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createAndGate()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new AndGate(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createOrGate()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new OrGate(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createNandGate()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new NandGate(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createNorGate()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new NorGate(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createNotGate()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new NotGate(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createBitDisplay()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new BitDisplay(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }

  public static void createSwitchSource()
  {
    Rectangle r = Engine.getContentPanel().getVisibleRect();
    new SwitchSource(r.width / 2, r.height / 2, p);
    p.revalidate();
    p.repaint();
  }
}
