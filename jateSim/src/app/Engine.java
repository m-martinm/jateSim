package app;

import components.*;
import components.displayComponents.DisplayComponent;
import components.gates.*;

import javax.swing.*;

import java.awt.*;


public class Engine
{

  public static JFrame frame = new JFrame("jateSim");
  public static MyBar menuBar = new MyBar();
  public static ContentPanel contentPanel = new ContentPanel();
  public static ControlPanel controlPanel = new ControlPanel();
  public static Mode mode = Mode.DEFAULT;

  public Engine(int width, int height)
  {
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addComponentListener(new FrameListener());
    frame.addKeyListener(new FrameListener());
    frame.setSize(width, height);

    frame.add(controlPanel);
    frame.add(contentPanel);
    frame.setJMenuBar(menuBar);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

    public static void updateSize()
    {
      Rectangle r = frame.getContentPane().getBounds();
      controlPanel.setBounds(0, 0, r.width, ControlPanel.height);
    }

  public static void setMode(Mode m)
  {
    mode = m;
    controlPanel.setModeText(m.name());
  }

  public static void addGate(GateType type)
  {
    switch(type) {
      case AND:
        new AndGate(50, 50, contentPanel);
        break;
      case OR:
        new OrGate(50, 50, contentPanel);
        break;
      case NAND:
        new NandGate(50, 50, contentPanel);
        break;
      case NOR:
        new NorGate(50, 50, contentPanel);
        break;
      default:
        break;
    }
    contentPanel.repaint();
  }

  /*
  Update process
  1. update input pins using signal.update()
  2. update output pins using gate.update()
  3. update display components
  This is one clock tick
  This way u can only use synchronized systems with one clock
  In the future create a clock component so you can give clock signals to different components
   */

  public static void tickClock()
  {
    Signal.updateSignals();
    Gate.updateGates();
    DisplayComponent.updateDisplayComponents();
  }

  public static void startSimulation()
  {
    //TODO implement
  }

  public static void endSimulation()
  {
    //TODO implement
  }
}
