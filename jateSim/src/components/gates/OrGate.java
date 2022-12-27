package components.gates;

import components.pins.Pin;

import javax.swing.*;

public class OrGate extends Gate
{
  public OrGate(int x, int y, JPanel parentPanel)
  {
    super(x, y, "OR", parentPanel);
  }

  public void update()
  {
    if(this.input1.getValue() == Pin.UNKNOWN || this.input2.getValue() == Pin.UNKNOWN) {
      this.output.setValue(Pin.UNKNOWN);
    } else {
      int tmp = this.input1.getValue() | this.input2.getValue();
      switch(tmp) {
        case Pin.LOW:
          this.output.setValue(Pin.HIGH);
          break;
        case Pin.HIGH:
          this.output.setValue(Pin.LOW);
          break;
        default:
          this.output.setValue(Pin.UNKNOWN);
          break;
      }
    }
  }
}
