package components.displayComponents;

import app.Engine;
import app.Mode;
import components.pins.Pin;
import components.pins.PinType;
import components.signals.Signal;

import javax.swing.*;
import java.awt.*;

public class BitDisplay extends DisplayComponent
{
  public static final Dimension SIZE = new Dimension(30, 30);
  Pin input;

  public BitDisplay(int x, int y, JPanel parentPanel)
  {
    super(x, y, SIZE.width, SIZE.height, "X", parentPanel);
    this.input = new Pin(x - Pin.SIZE.width, (y + SIZE.height / 2) - Pin.SIZE.height / 2, "1", parentPanel, this,
                         PinType.INPUT);

  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.getMode() != Mode.MOVE) return;
    if(x > getParent().getWidth() || x < 0 || y > getParent().getHeight() || y < 0) return;
    int a = x - getLabel().getWidth() / 2;
    int b = y - getLabel().getHeight() / 2;
    getLabel().setBounds(a, b, getLabel().getWidth(), getLabel().getHeight());
    this.input.getLabel().setBounds(a - Pin.SIZE.width, (b + SIZE.height / 2) - Pin.SIZE.height / 2, Pin.SIZE.width,
                                    Pin.SIZE.height);
    Signal.repositionSignals();
  }

  @Override
  public void update()
  {
    switch(this.input.getValue()) {
      case 1:
        getLabel().setText("1");
        break;
      case 0:
        getLabel().setText("0");
        break;
      default:
        getLabel().setText("X");
        break;
    }
  }

  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    DisplayComponent.displayComponents.remove(this);
    this.input.deleteComponent();
  }
}
