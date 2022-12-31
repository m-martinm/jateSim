package components.gates;

import components.boxer.Prototype;
import components.pins.Pin;

import javax.swing.*;

public class AndGate extends Gate
{
  public AndGate(int x, int y, JPanel parentPanel)
  {
    super(x, y, "AND", parentPanel);
  }

  public void update()
  {
    if(this.input1.getValue() == Pin.UNKNOWN || this.input2.getValue() == Pin.UNKNOWN) {
      this.output.setValue(Pin.UNKNOWN);
    } else {
      this.output.setValue(this.input1.getValue() & this.input2.getValue());
    }
  }

  @Override
  public Prototype clone()
  {
    return new AndGate(getX(), getY(), getParent());
  }
}
