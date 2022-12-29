package components.gates;

import app.Engine;
import app.Mode;
import components.pins.Pin;
import components.signals.Signal;

import javax.swing.*;
import java.awt.*;

public class NotGate extends Gate
{
  public static final Dimension NOT_GATE_SIZE = new Dimension(45, 30);

  public NotGate(int x, int y, JPanel parentPanel)
  {
    super(x, y, "NOT", parentPanel);
    getInput2().deleteComponent();
    getLabel().setBounds(x, y, NOT_GATE_SIZE.width, NOT_GATE_SIZE.height);
    getInput1().getLabel().setBounds(getX() - Pin.SIZE.width, getY() + Pin.SIZE.height, Pin.SIZE.width,
                                     Pin.SIZE.height);
    getOutput().getLabel().setBounds(getX() + NOT_GATE_SIZE.width, getY() + Pin.SIZE.height, Pin.SIZE.width,
                                     Pin.SIZE.height);
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.getMode() != Mode.MOVE) return;
    if(x > getParent().getWidth() || x < 0 || y > getParent().getHeight() || y < 0) return;
    int a = x - getLabel().getWidth() / 2;
    int b = y - getLabel().getHeight() / 2;
    getLabel().setBounds(a, b, getLabel().getWidth(), getLabel().getHeight());
    getInput1().getLabel().setBounds(a - Pin.SIZE.width, b + Pin.SIZE.height, Pin.SIZE.width, Pin.SIZE.height);
    getOutput().getLabel().setBounds(a + NOT_GATE_SIZE.width, b + Pin.SIZE.height, Pin.SIZE.width, Pin.SIZE.height);
    Signal.repositionSignals();
  }

  @Override
  public void update()
  {
    if(this.input1.getValue() == Pin.UNKNOWN) {
      this.output.setValue(Pin.UNKNOWN);
    } else {
      switch(getInput1().getValue()) {
        case Pin.HIGH:
          getOutput().setValue(Pin.LOW);
          break;
        case Pin.LOW:
          getOutput().setValue(Pin.HIGH);
          break;
        default:
          getOutput().setValue(Pin.UNKNOWN);
          break;
      }
    }
  }
}
