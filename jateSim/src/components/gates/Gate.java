package components.gates;

import javax.swing.*;

import app.Engine;
import app.Mode;
import components.*;
import components.pins.Pin;
import components.pins.PinType;
import components.signals.Signal;

import java.awt.*;
import java.util.ArrayList;

public class Gate extends SimComponent
{
  public static final Dimension SIZE = new Dimension(50, 70);
  public static ArrayList<Gate> gates = new ArrayList<>();

  Pin input1;
  Pin input2;
  Pin output;

  public Gate(int x, int y, String text, JPanel parentPanel)
  {
    super(x, y, SIZE.width, SIZE.height, text, parentPanel);
    this.input1 = new Pin(x - Pin.SIZE.width, y, "1", getParent(), this, PinType.INPUT);
    this.input2 = new Pin(x - Pin.SIZE.width, y + SIZE.height - Pin.SIZE.height, "2", getParent(), this, PinType.INPUT);
    this.output = new Pin(x + SIZE.width, y + SIZE.height / 2 - Pin.SIZE.height / 2, "O", getParent(), this,
                          PinType.OUTPUT);
    this.output.setValue(Pin.LOW);
    gates.add(this);
  }

  public int getOutput()
  {
    return this.output.getValue();
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.getMode() != Mode.MOVE) return;
    if(x > getParent().getWidth() || x < 0 || y > getParent().getHeight() || y < 0) return;
    int a = x - getLabel().getWidth() / 2;
    int b = y - getLabel().getHeight() / 2;
    getLabel().setBounds(a, b, getLabel().getWidth(), getLabel().getHeight());
    this.input1.getLabel().setBounds(a - Pin.SIZE.width, b, Pin.SIZE.width, Pin.SIZE.height);
    this.input2.getLabel().setBounds(a - Pin.SIZE.width, b + SIZE.height - Pin.SIZE.height, Pin.SIZE.width,
                                     Pin.SIZE.height);
    this.output.getLabel().setBounds(a + SIZE.width, b + SIZE.height / 2 - Pin.SIZE.height / 2, Pin.SIZE.width,
                                     Pin.SIZE.height);
    Signal.repositionSignals();
  }

  public void update()
  {

  }

  public static void updateGates()
  {
    for(Gate g : gates) {
      g.update();
    }
  }

  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    gates.remove(this);
    this.input1.deleteComponent();
    this.input2.deleteComponent();
    this.output.deleteComponent();
  }
}
