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

    if(args.length == 0) {
      System.out.println("There is no given command, the app runs with default configuration.");
      runApp(false);
    } else if(args.length > 1) {
      System.out.println("Too many commands were given, run 'java -jar jateSim.jar --help for more information.'");
    } else if(args[0].equals("--help")) {
      System.out.println("--getLAF  With the flag the app will load the system's default Look and Feel.");
      System.out.println("          Without this flag there might be some bugs,");
      System.out.println("          if something isn't working try running the app without it.\n");
      System.out.println("--help    Displays this window.");
    } else if(args[0].equals("--getLAF")) {
      runApp(true);
    } else {
      System.out.println("The command " + args[0] +
                                 " doesn't exist (yet), run 'java -jar jateSim.jar --help' for more information.");
    }
  }

  private static final JFrame frame = new JFrame("jateSim");
  private static final MyBar menuBar = new MyBar();
  private static final ContentPanel contentPanel = new ContentPanel();
  private static final ControlPanel controlPanel = new ControlPanel();
  private static Mode mode = Mode.DEFAULT;
  private static final SimClock clock = new SimClock(1);

  private static void runApp(boolean getLAF)
  {
    //    SimLogger.turnOn(true);
    if(getLAF) {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch(ClassNotFoundException | InstantiationException | IllegalAccessException |
              UnsupportedLookAndFeelException e) {
        SimLogger.log("Couldn't get system's look and feel: " + e);
      }
    }

    try {
      frame.setIconImage(ImageIO.read(new File("res/icon.png")));
    } catch(IOException e) {
      System.out.println(
              "Couldn't load icon. Make sure to place the 'res' folder in the same folder with the .jar file.");
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
