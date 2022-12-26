package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimClock implements ActionListener
{
  private final Timer timer;
  private int updateSpeed;

  public SimClock(int updatesPerSecond)
  {
    this.updateSpeed = 1000 / updatesPerSecond;
    this.timer = new Timer(updateSpeed, this);
    this.timer.setRepeats(true);
  }

  public void changeSpeed(int updatesPerSecond)
  {
    this.stop();
    this.timer.setDelay(1000 / updatesPerSecond);
    this.start();
  }

  public void start()
  {
    this.timer.start();
  }

  public void stop()
  {
    this.timer.stop();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Engine.updateCycle();
  }
}
