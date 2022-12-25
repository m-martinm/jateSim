package components.gates;

import components.Pin;

import javax.swing.*;

public class NandGate extends Gate
{
  public NandGate(int x, int y, JPanel parentPanel)
  {
    super(x, y, "NAND", parentPanel);
  }

  public void update()
  {
    if(this.input1.getValue() == Pin.UNKNOWN || this.input2.getValue() == Pin.UNKNOWN) {
      this.output.setValue(Pin.UNKNOWN);
    } else {
      int tmp = this.input1.getValue() & this.input2.getValue();
      switch(tmp) {
        case 1:
          this.output.setValue(0);
          break;
        case 0:
          this.output.setValue(1);
          break;
        default:
          this.output.setValue(Pin.UNKNOWN);
          break;
      }
    }
  }
}
