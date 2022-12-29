package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimClock implements ActionListener
{
  private final Timer timer;
  private boolean running;

  public SimClock(int updatesPerSecond)
  {
    this.timer = new Timer(1000 / updatesPerSecond, this);
    this.timer.setRepeats(true);
    this.running = false;
  }

  public boolean isRunning()
  {
    return this.running;
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
    this.running = true;
  }

  public void stop()
  {
    this.timer.stop();
    this.running = false;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Engine.updateCycle();
  }
}
