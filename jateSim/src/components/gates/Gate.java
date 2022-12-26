package components.gates;

import javax.swing.*;

import app.Engine;
import components.*;

import java.awt.*;
import java.util.ArrayList;

public class Gate extends SimComponent
{
  public static final Dimension size = new Dimension(50, 70);
  public static ArrayList<Gate> gates = new ArrayList<>();

  Pin input1;
  Pin input2;
  Pin output;

  public Gate(int x, int y, String text, JPanel parentPanel)
  {
    super(x, y, size.width, size.height, text, parentPanel);
    this.input1 = new Pin(x - Pin.size.width, y, "1", this.parent, this, PinType.INPUT);
    this.input2 = new Pin(x - Pin.size.width, y + size.height - Pin.size.height, "2", this.parent, this, PinType.INPUT);
    this.output = new Pin(x + size.width, y + size.height / 2 - Pin.size.height / 2, "O", this.parent, this,
                          PinType.OUTPUT);
    gates.add(this);
  }

  public int getOutput()
  {
    return this.output.getValue();
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(x > this.parent.getWidth() || x < 0 || y > this.parent.getHeight() || y < 0) return;
    int a = x - this.label.getWidth() / 2;
    int b = y - this.label.getHeight() / 2;
    this.label.setBounds(a, b, this.label.getWidth(), this.label.getHeight());
    this.input1.label.setBounds(a - Pin.size.width, b, Pin.size.width, Pin.size.height);
    this.input2.label.setBounds(a - Pin.size.width, b + size.height - Pin.size.height, Pin.size.width, Pin.size.height);
    this.output.label.setBounds(a + size.width, b + size.height / 2 - Pin.size.height / 2, Pin.size.width,
                                Pin.size.height);
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

}
