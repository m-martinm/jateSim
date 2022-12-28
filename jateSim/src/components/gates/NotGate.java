package components.gates;

import components.pins.Pin;

import javax.swing.*;
import java.awt.*;

public class NotGate extends Gate
{
  public static final Dimension notGateSize = new Dimension(50, 30);

  public NotGate(int x, int y, JPanel parentPanel)
  {
    super(x, y, "NOT", parentPanel);
    // TODO remove 2nd Pin-input
  }

  @Override
  public void update()
  {
    if(this.input1.getValue() == Pin.UNKNOWN) {
      this.output.setValue(Pin.UNKNOWN);
    } else {
      switch(this.input1.getValue()) {
        case Pin.HIGH:
          this.output.setValue(Pin.LOW);
          break;
        case Pin.LOW:
          this.output.setValue(Pin.HIGH);
          break;
        default:
          this.output.setValue(Pin.UNKNOWN);
          break;
      }
    }
  }
}
