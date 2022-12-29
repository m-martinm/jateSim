package app;

import components.SimComponent;
import components.pins.Pin;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameListener implements ComponentListener, KeyListener
{
  @Override
  public void componentResized(ComponentEvent e)
  {
    Engine.updateSize();
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {

  }

  @Override
  public void componentShown(ComponentEvent e)
  {

  }

  @Override
  public void componentHidden(ComponentEvent e)
  {

  }

  @Override
  public void keyTyped(KeyEvent e)
  {

  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    if(Engine.getMode() == Mode.MOVE) Engine.getContentPanel().movePanel(e);
    switch(e.getKeyChar()) {
      case 'M':
        if(Engine.getMode() == Mode.MOVE) Engine.setMode(Mode.DEFAULT);
        else Engine.setMode(Mode.MOVE);
        Pin.deselectAll();
        SimComponent.deselectAll();
        break;
      case 'D':
        Engine.setMode(Mode.DEFAULT);
        Pin.deselectAll();
        SimComponent.deselectAll();
        break;
      case 'C':
        if(Engine.getMode() == Mode.CONNECT) {
          Engine.setMode(Mode.DEFAULT);
          Pin.deselectAll();
        } else {
          Engine.setMode(Mode.CONNECT);
          SimComponent.deselectAll();
        }
        break;
      case 'S':
        if(Engine.getMode() == Mode.SELECT) {
          Engine.setMode(Mode.DEFAULT);
          SimComponent.deselectAll();
        } else Engine.setMode(Mode.SELECT);
        Pin.deselectAll();
        break;
      default:
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {

  }

}
