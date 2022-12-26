package components;

import app.Engine;
import app.SimNotification;
import components.sourceComponents.SwitchSource;
import simNotifier.SimObservable;
import simNotifier.SimObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Signal extends JPanel implements SimObserver
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
    out.value.addObserver(this);
    this.output = out;
    this.inputPoint = new Point(inp.getX(), inp.getY() + Pin.size.height / 2);
    this.outputPoint = new Point(out.getX() + Pin.size.width, out.getY() + Pin.size.height / 2);
    this.rect = new Rectangle(outputPoint.x, outputPoint.y, inputPoint.x - outputPoint.x, inputPoint.y - outputPoint.y);
    //TODO don't need the points just call reposition();
    signals.add(this);
    setBounds(this.rect);
    setOpaque(true); //TODO change
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

  public void reposition()
  {
    if(this.output.getX() < this.input.getX() && this.output.getY() < this.input.getY()) {
      this.rect.x = this.output.getX() + Pin.size.width;
      this.rect.y = this.output.getY() + Pin.size.height / 2;
      this.rect.width = this.input.getX() - this.rect.x;
      this.rect.height = this.input.getY() + Pin.size.height / 2 - this.rect.y;
    } else if(this.output.getX() < this.input.getX() && this.output.getY() > this.input.getY()) {
      this.rect.x = this.output.getX() + Pin.size.width;
      this.rect.y = this.output.getY() + Pin.size.height / 2 - this.input.getY() - this.input.getY();
      this.rect.width = this.input.getX() - this.rect.x;
      this.rect.height = this.input.getY() + Pin.size.height / 2 - this.rect.y;
    } else if(this.output.getX() > this.input.getX() && this.output.getY() < this.input.getY()) {

    } else if(this.output.getX() > this.input.getX() && this.output.getY() > this.input.getY()) {

    }
    this.setBounds(this.rect);
    this.repaint();
  }

  public static void repositionSignals()
  {
    for(Signal s : signals) {
      //TODO this only works for specified position check where are the pins if(out.x > inp.x && ...)...
      s.reposition();
    }
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
    this.input.setValue(this.output.getValue());
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

  @Override
  public void updateObserver(SimObservable o, int data)
  {
    this.update();
  }
}
