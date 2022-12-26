package components.displayComponents;

import app.Engine;
import app.Mode;
import components.Pin;
import components.PinType;
import components.Signal;


import javax.swing.*;
import java.awt.*;

public class BitDisplay extends DisplayComponent
{
  public static final Dimension size = new Dimension(30, 30);
  Pin input;

  public BitDisplay(int x, int y, JPanel parentPanel)
  {
    super(x, y, size.width, size.height, "X", parentPanel);
    this.input = new Pin(x - Pin.size.width, (y + size.height / 2) - Pin.size.height / 2, "1", parentPanel, this,
                         PinType.INPUT);

  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.mode != Mode.MOVE) return;
    if(x > this.parent.getWidth() || x < 0 || y > this.parent.getHeight() || y < 0) return;
    int a = x - this.label.getWidth() / 2;
    int b = y - this.label.getHeight() / 2;
    this.label.setBounds(a, b, this.label.getWidth(), this.label.getHeight());
    this.input.label.setBounds(a - Pin.size.width, (b + size.height / 2) - Pin.size.height / 2, Pin.size.width,
                               Pin.size.height);
    Signal.repositionSignals();
  }

  @Override
  public void update()
  {
    switch(this.input.getValue()) {
      case 1:
        this.label.setText("1");
        break;
      case 0:
        this.label.setText("0");
        break;
      default:
        this.label.setText("X");
        break;
    }
  }

}
