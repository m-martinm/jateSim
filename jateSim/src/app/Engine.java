package app;

import components.*;
import components.displayComponents.DisplayComponent;
import components.gates.*;
import simUtils.SimLogger;
import simUtils.SimNotification;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Engine
{

  public static void main(String[] args)
  {
    Engine.runApp();
  }

  private static final JFrame frame = new JFrame("jateSim");
  private static final MyBar menuBar = new MyBar();
  private static final ContentPanel contentPanel = new ContentPanel();
  private static final ControlPanel controlPanel = new ControlPanel();
  private static Mode mode = Mode.DEFAULT;
  private static final SimClock clock = new SimClock(1);

  private static void runApp()
  {
    SimLogger.turnOn(true);
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(ClassNotFoundException | InstantiationException | IllegalAccessException |
            UnsupportedLookAndFeelException e) {
      SimLogger.log("Couldn't get system's look and feel: " + e);
    }

    try {
      frame.setIconImage(ImageIO.read(new File("res/icon.png")));
    } catch(IOException e) {
      System.out.println("Couldn't load icon.");
    }
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addComponentListener(new FrameListener());
    frame.addKeyListener(new FrameListener());
    frame.setSize(800, 800);

    frame.add(controlPanel);
    frame.add(contentPanel);
    frame.setJMenuBar(menuBar);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static JFrame getFrame()
  {
    return frame;
  }

  public static ContentPanel getContentPanel()
  {
    return contentPanel;
  }

  public static Mode getMode()
  {
    return mode;
  }

  public static SimClock getClock()
  {
    return clock;
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

  public static void updateCycle()
  {
    Gate.updateGates();
    DisplayComponent.updateDisplayComponents();
  }

  public static void startSimulation()
  {
    new SimNotification("Simulation started!", true);
    clock.start();
  }

  public static void stopSimulation()
  {
    new SimNotification("Simulation ended!", true);
    clock.stop();
  }
}
