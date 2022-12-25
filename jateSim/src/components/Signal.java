package components;

import app.Engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Signal extends JPanel
{
  public static ArrayList<Signal> signals = new ArrayList<>();

  public Rectangle rect;
  public Point inputPoint;
  public Point outputPoint;
  Pin input;
  Pin output;

  private Signal(Pin inp, Pin out)
  {
    this.input = inp;
    this.output = out;
    this.inputPoint = new Point(inp.getX(), inp.getY() + Pin.size.height / 2);
    this.outputPoint = new Point(out.getX() + Pin.size.width, out.getY() + Pin.size.height / 2);
    this.rect = new Rectangle(outputPoint.x, outputPoint.y, inputPoint.x - outputPoint.x, inputPoint.y - outputPoint.y);

    signals.add(this);
    setBounds(this.rect);
    setOpaque(false);
    Engine.contentPanel.add(this);
    this.repaint();
  }

  public void getInfo()
  {
    System.out.println("-----  " + this + "  -----");
    System.out.println("rect: " + this.rect);
    System.out.println("Inp: " + this.inputPoint + " Out: " + this.outputPoint);
    System.out.println("Input pin x,y  " + this.input.getX() + "  " + this.input.getY());
    System.out.println("Output pin x,y  " + this.output.getX() + "  " + this.output.getY());
    System.out.println("Visible rect: " + this.getVisibleRect());
  }

  public static boolean createSignal(Pin inp, Pin out)
  {
    if(inp.type != PinType.INPUT && out.type != PinType.OUTPUT) return false;
    for(Signal s : signals) {
      if(s.input == inp && s.output == out) return false;
    }
    new Signal(inp, out);
    return true;
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(3));
    g2.setColor(Color.blue);
    g2.drawLine(0, 0, getWidth(), getHeight());
  }

  public void update()
  {
    this.output.setValue(this.input.getValue());
  }

  public static void updateSignals()
  {
    for(Signal s : signals) {
      s.update();
    }
  }

  public static void repaintSignals()
  {
    for(Signal s : signals)
      s.repaint();
  }
}
