package app;

import components.pins.Pin;

import java.awt.event.*;

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
    if(Engine.mode == Mode.MOVE) Engine.contentPanel.movePanel(e);
    switch(e.getKeyChar()) {
      case 'M' -> {
        if(Engine.mode == Mode.MOVE) Engine.setMode(Mode.DEFAULT);
        else Engine.setMode(Mode.MOVE);
        Pin.deselectAll();
      }
      case 'D' -> {
        Engine.setMode(Mode.DEFAULT);
        Pin.deselectAll();
      }
      case 'C' -> {
        if(Engine.mode == Mode.CONNECT) {
          Engine.setMode(Mode.DEFAULT);
          Pin.deselectAll();
        } else Engine.setMode(Mode.CONNECT);
      }
      case 'S' -> {
        if(Engine.mode == Mode.SELECT) Engine.setMode(Mode.DEFAULT);
        else Engine.setMode(Mode.SELECT);
        Pin.deselectAll();
      }
      default -> {
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {

  }

}
