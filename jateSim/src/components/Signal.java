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
  private static final int OFFSET = 1;

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
    reposition();
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

  public void reposition()
  {
    this.inputPoint.x = this.input.getX();
    this.inputPoint.y = this.input.getY() + Pin.size.height / 2;
    this.outputPoint.x = this.output.getX() + Pin.size.width;
    this.outputPoint.y = this.output.getY() + Pin.size.height / 2;
    if(outputPoint.x < inputPoint.x && outputPoint.y < inputPoint.y) {
      this.rect = new Rectangle(outputPoint.x, outputPoint.y, inputPoint.x - outputPoint.x,
                                inputPoint.y - outputPoint.y);
    } else if(outputPoint.x < inputPoint.x && outputPoint.y > inputPoint.y) {
      this.rect = new Rectangle(outputPoint.x, inputPoint.y, inputPoint.x - outputPoint.x,
                                outputPoint.y - inputPoint.y);
    } else if(outputPoint.x > inputPoint.x && outputPoint.y < inputPoint.y) {
      this.rect = new Rectangle(inputPoint.x, outputPoint.y, outputPoint.x - inputPoint.x,
                                inputPoint.y - outputPoint.y);
    } else if(outputPoint.x > inputPoint.x && outputPoint.y > inputPoint.y) {
      this.rect = new Rectangle(inputPoint.x, inputPoint.y, outputPoint.x - inputPoint.x, outputPoint.y - inputPoint.y);
    }
    setBounds(this.rect);
    repaint();
  }

  public static void repositionSignals()
  {
    for(Signal s : signals) {
      s.reposition();
    }
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(2));
    if(this.output.getValue() == Pin.HIGH) {
      g2.setColor(Color.green);
    } else g2.setColor(Color.blue);
    if(outputPoint.x < inputPoint.x && outputPoint.y < inputPoint.y) {
      g2.drawLine(0, 0, getWidth(), getHeight());
    } else if(outputPoint.x < inputPoint.x && outputPoint.y > inputPoint.y) {
      g2.drawLine(0, getHeight(), getWidth(), 0);
    } else if(outputPoint.x > inputPoint.x && outputPoint.y < inputPoint.y) {
      g2.drawLine(0, getHeight(), getWidth(), 0);
    } else if(outputPoint.x > inputPoint.x && outputPoint.y > inputPoint.y) {
      g2.drawLine(0, 0, getWidth(), getHeight());
    }

  }

  public void update()
  {
    this.input.setValue(this.output.getValue());
    repaint();
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
