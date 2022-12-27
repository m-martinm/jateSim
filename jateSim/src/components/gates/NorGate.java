package components.gates;

import components.Pin;

import javax.swing.*;

public class NorGate extends Gate
{
  public NorGate(int x, int y, JPanel parentPanel)
  {
    super(x, y, "NOR", parentPanel);
  }

  public void update()
  {
    if(this.input1.getValue() == Pin.UNKNOWN || this.input2.getValue() == Pin.UNKNOWN) {
      this.output.setValue(Pin.UNKNOWN);
    } else {
      this.output.setValue(this.input1.getValue() | this.input2.getValue());
    }
  }
}
