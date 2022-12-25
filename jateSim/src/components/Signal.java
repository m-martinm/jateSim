package components;

import app.Engine;

import java.awt.*;
import java.util.ArrayList;

public class Signal
{
  public static ArrayList<Signal> signals = new ArrayList<>();

  Pin input;
  Pin output;

  //TODO change drawing method(link in myPanel)
  public Signal(Pin inp, Pin out)
  {
    this.input = inp;
    this.output = out;

    signals.add(this);
  }

  public void update()
  {
    this.output.setValue(this.input.getValue());
  }

  public void connectPins()
  {
    Graphics g = Engine.contentPanel.getGraphics();
    g.drawLine(this.input.getX(), this.input.getY(), this.output.getX(), this.output.getY());
  }
}
