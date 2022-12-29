package components.signals;

import app.ContentPanel;
import app.Engine;
import components.pins.Pin;
import components.pins.PinType;
import simNotifier.SimObservable;
import simNotifier.SimObserver;

import java.awt.*;
import java.util.ArrayList;

public class Signal implements SimObserver
{
  public static ArrayList<Signal> signals = new ArrayList<>();

  private SignalRect rect;
  private Point inputPoint;
  private Point outputPoint;
  private Pin input;
  private Pin output;
  private SignalDrawingMethod drawingMethod;
  private boolean hidden = false;

  private Signal(Pin inp, Pin out)
  {
    this.drawingMethod = SignalDrawingMethod.STRAIGHT;
    this.input = inp;
    out.value.addObserver(this);
    this.output = out;
    inp.connectedSignals.add(this);
    out.connectedSignals.add(this);
    this.inputPoint = new Point(inp.getX(), inp.getY() + Pin.SIZE.height / 2); //TODO adjust input and output
    this.outputPoint = new Point(out.getX() + Pin.SIZE.width, out.getY() + Pin.SIZE.height / 2);
    this.rect = new SignalRect(this);
    reposition();
    signals.add(this);
    getPanel().update();
  }

  public void hide()
  {
    hidden = true;
  }

  public static ContentPanel getPanel()
  {
    return Engine.getContentPanel();
  }

  public SignalDrawingMethod getDrawingMethod()
  {
    return drawingMethod;
  }

  public void changeDrawingMethod()
  {
    switch(drawingMethod) {
      case LOWER:
        drawingMethod = SignalDrawingMethod.STRAIGHT;
        break;
      case UPPER:
        drawingMethod = SignalDrawingMethod.LOWER;
        break;
      case STRAIGHT:
        drawingMethod = SignalDrawingMethod.UPPER;
        break;
      default:
        break;
    }
    rect.updateRect(this);
  }

  public SignalRect getRect()
  {
    return rect;
  }

  public Point getOutputPoint()
  {
    return outputPoint;
  }

  public Point getInputPoint()
  {
    return inputPoint;
  }

  public boolean isSelected(Point p)
  {
    rect.updateRect(this);
    return rect.isClicked(p, this);
  }

  public void unhide()
  {
    hidden = false;
  }

  public boolean isHidden()
  {
    return hidden;
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
    rect.updateRect(this);
    getPanel().update(this.rect);
  }

  public static void repositionSignals()
  {
    for(Signal s : signals) {
      s.reposition();
    }
  }

  public void paintSignal(Graphics2D g)
  {
    if(hidden) return;
    g.setStroke(new BasicStroke(2));
    if(this.output.getValue() == Pin.HIGH) {
      g.setColor(Color.green.darker());
    } else g.setColor(Color.blue);
    switch(this.drawingMethod) {
      case STRAIGHT:
        g.drawLine(outputPoint.x, outputPoint.y, inputPoint.x, inputPoint.y);
        break;
      case LOWER:
        g.drawLine(outputPoint.x, outputPoint.y, outputPoint.x, inputPoint.y);
        g.drawLine(outputPoint.x, inputPoint.y, inputPoint.x, inputPoint.y);
        break;
      case UPPER:
        g.drawLine(outputPoint.x, outputPoint.y, inputPoint.x, outputPoint.y);
        g.drawLine(inputPoint.x, outputPoint.y, inputPoint.x, inputPoint.y);
        break;
      default:
        break;
    }
  }

  public void deleteSignal()
  {
    Rectangle tmp = this.rect;
    signals.remove(this);
    getPanel().update(tmp);
  }

  public static void deleteAllSignals()
  {
    signals.clear();
    getPanel().update();
  }

  @Override
  public void updateObserver(SimObservable o, int data)
  {
    this.input.setValue(this.output.getValue());
    getPanel().update(this.rect);
  }
}