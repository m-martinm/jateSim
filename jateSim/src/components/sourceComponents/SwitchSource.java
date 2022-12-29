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
  public static final Dimension SIZE = new Dimension(30, 30);
  public Pin output;

  public SwitchSource(int x, int y, JPanel parentPanel)
  {
    super(x, y, SIZE.width, SIZE.height, "S(0)", parentPanel);
    this.output = new Pin(x + SIZE.width, (y + SIZE.height / 2) - Pin.SIZE.height / 2, "O", parentPanel, this,
                          PinType.OUTPUT);
    getLabel().setBackground(Color.red.darker());
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.getMode() != Mode.MOVE) return;
    if(x > getParent().getWidth() || x < 0 || y > getParent().getHeight() || y < 0) return;
    int a = x - getLabel().getWidth() / 2;
    int b = y - getLabel().getHeight() / 2;
    getLabel().setBounds(a, b, getLabel().getWidth(), getLabel().getHeight());
    this.output.getLabel().setBounds(a + SIZE.width, (b + SIZE.height / 2) - Pin.SIZE.height / 2, Pin.SIZE.width,
                                     Pin.SIZE.height);
    Signal.repositionSignals();
  }

  @Override
  public void update()
  {
    switch(this.output.getValue()) {
      case Pin.HIGH:
        this.output.setValue(Pin.LOW);
        getLabel().setText("S(0)");
        getLabel().setBackground(Color.red.darker());
        break;
      case Pin.LOW:
        this.output.setValue(Pin.HIGH);
        getLabel().setText("S(1)");
        getLabel().setBackground(Color.green.darker());
        break;
      case Pin.UNKNOWN:
        this.output.setValue(Pin.LOW);
        getLabel().setText("S(0)");
        getLabel().setBackground(Color.RED);
      default:
        break;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    super.mouseClicked(e);
    if(Engine.getMode() == Mode.DEFAULT && e.getButton() == MouseEvent.BUTTON1) this.update();
  }

  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    SourceComponent.sourceComponents.remove(this);
    this.output.deleteComponent();
  }
}
