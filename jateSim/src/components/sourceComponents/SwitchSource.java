package components.sourceComponents;

import app.Engine;
import app.Mode;
import components.pins.Pin;
import components.pins.PinType;
import components.signals.Signal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SwitchSource extends SourceComponent
{
  public static final Dimension size = new Dimension(30, 30);
  public Pin output;

  public SwitchSource(int x, int y, JPanel parentPanel)
  {
    super(x, y, size.width, size.height, "S(X)", parentPanel);
    this.output = new Pin(x + size.width, (y + size.height / 2) - Pin.size.height / 2, "O", parentPanel, this,
                          PinType.OUTPUT);
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.mode != Mode.MOVE) return;
    if(x > this.parent.getWidth() || x < 0 || y > this.parent.getHeight() || y < 0) return;
    int a = x - this.label.getWidth() / 2;
    int b = y - this.label.getHeight() / 2;
    this.label.setBounds(a, b, this.label.getWidth(), this.label.getHeight());
    this.output.label.setBounds(a + size.width, (b + size.height / 2) - Pin.size.height / 2, Pin.size.width,
                                Pin.size.height);
    Signal.repositionSignals();
  }

  @Override
  public void update()
  {
    switch(this.output.getValue()) {
      case Pin.HIGH:
        this.output.setValue(Pin.UNKNOWN);
        this.label.setText("S(X)");
        this.label.setBackground(Color.gray);
        break;
      case Pin.LOW:
        this.output.setValue(Pin.HIGH);
        this.label.setText("S(1)");
        this.label.setBackground(Color.green);
        break;
      case Pin.UNKNOWN:
        this.output.setValue(Pin.LOW);
        this.label.setText("S(0)");
        this.label.setBackground(Color.RED);
      default:
        break;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    super.mouseClicked(e);
    if(e.getButton() == MouseEvent.BUTTON1) this.update();
  }

  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    SourceComponent.sourceComponents.remove(this);
    this.output.deleteComponent();
  }
}
