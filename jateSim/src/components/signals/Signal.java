package components.signals;

import app.Engine;
import app.Mode;
import components.pins.Pin;
import components.pins.PinType;
import simNotifier.SimObservable;
import simNotifier.SimObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Signal extends JPanel implements SimObserver, MouseListener
{
  public static ArrayList<Signal> signals = new ArrayList<>();

  private Rectangle rect;
  private Point inputPoint;
  private Point outputPoint;
  private Pin input;
  private Pin output;
  private SignalDrawingMethod drawingMethod;

  private Signal(Pin inp, Pin out)
  {
    this.drawingMethod = SignalDrawingMethod.STRAIGHT;
    this.input = inp;
    out.value.addObserver(this);
    this.output = out;
    inp.connectedSignals.add(this);
    out.connectedSignals.add(this);
    this.inputPoint = new Point(inp.getX(), inp.getY() + Pin.SIZE.height / 2);
    this.outputPoint = new Point(out.getX() + Pin.SIZE.width, out.getY() + Pin.SIZE.height / 2);
    this.rect = new Rectangle(outputPoint.x, outputPoint.y, inputPoint.x - outputPoint.x, inputPoint.y - outputPoint.y);
    reposition();
    signals.add(this);
    setBounds(this.rect);
    setOpaque(false);
    addMouseListener(this);
    Engine.getContentPanel().add(this);
    revalidate();
    repaint();
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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
    this.inputPoint.y = this.input.getY() + Pin.SIZE.height / 2;
    this.outputPoint.x = this.output.getX() + Pin.SIZE.width;
    this.outputPoint.y = this.output.getY() + Pin.SIZE.height / 2;
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
    revalidate();
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
      switch(this.drawingMethod) {
        case STRAIGHT:
          g2.drawLine(0, 0, getWidth(), getHeight());
          break;
        case UPPER:
          g2.drawLine(0, 0, getWidth(), 0);
          g2.drawLine(getWidth(), 0, getWidth(), getHeight());
          break;
        case LOWER:
          g2.drawLine(0, 0, 0, getHeight());
          g2.drawLine(0, getHeight(), getWidth(), getHeight());
          break;
        default:
          break;
      }
    } else if(outputPoint.x < inputPoint.x && outputPoint.y > inputPoint.y) {
      switch(this.drawingMethod) {
        case STRAIGHT:
          g2.drawLine(0, getHeight(), getWidth(), 0);
          break;
        case UPPER:
          g2.drawLine(0, getHeight(), 0, 0);
          g2.drawLine(0, 0, getWidth(), 0);
          break;
        case LOWER:
          g2.drawLine(0, getHeight(), getWidth(), getHeight());
          g2.drawLine(getWidth(), getHeight(), getWidth(), 0);
          break;
        default:
          break;
      }
    } else if(outputPoint.x > inputPoint.x && outputPoint.y < inputPoint.y) {
      switch(this.drawingMethod) {
        case STRAIGHT:
          g2.drawLine(0, getHeight(), getWidth(), 0);
          break;
        case UPPER:
          g2.drawLine(0, getHeight(), 0, 0);
          g2.drawLine(0, 0, getWidth(), 0);
          break;
        case LOWER:
          g2.drawLine(0, getHeight(), getWidth(), getHeight());
          g2.drawLine(getWidth(), getHeight(), getWidth(), 0);
          break;
        default:
          break;
      }
    } else if(outputPoint.x > inputPoint.x && outputPoint.y > inputPoint.y) {
      switch(this.drawingMethod) {
        case STRAIGHT:
          g2.drawLine(0, 0, getWidth(), getHeight());
          break;
        case UPPER:
          g2.drawLine(0, 0, getWidth(), 0);
          g2.drawLine(getWidth(), 0, getWidth(), getHeight());
          break;
        case LOWER:
          g2.drawLine(0, 0, 0, getHeight());
          g2.drawLine(0, getHeight(), getWidth(), getHeight());
          break;
        default:
          break;
      }
    }
  }


  public void update()
  {
    this.input.setValue(this.output.getValue());
    revalidate();
    repaint();
  }

  public void deleteSignal()
  {
    Engine.getContentPanel().remove(this);
    signals.remove(this);
    Engine.getContentPanel().revalidate();
    Engine.getContentPanel().repaint();
  }

  public static void deleteAllSignals()
  {
    for(Signal s : signals) {
      Engine.getContentPanel().remove(s);
    }
    signals.clear();
    Engine.getContentPanel().revalidate();
    Engine.getContentPanel().repaint();
  }

  @Override
  public void updateObserver(SimObservable o, int data)
  {
    this.update();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(Engine.getMode() == Mode.CONNECT && e.getButton() == MouseEvent.BUTTON3) {
      switch(this.drawingMethod) {
        case LOWER:
          this.drawingMethod = SignalDrawingMethod.UPPER;
          break;
        case UPPER:
          this.drawingMethod = SignalDrawingMethod.STRAIGHT;
          break;
        case STRAIGHT:
          this.drawingMethod = SignalDrawingMethod.LOWER;
          break;
      }
      revalidate();
      repaint();
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
