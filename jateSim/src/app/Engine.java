package app;

import components.*;
import components.displayComponents.BitDisplay;
import components.displayComponents.DisplayComponent;
import components.displayComponents.DisplayComponentType;
import components.gates.*;
import components.sourceComponents.SourceComponentType;
import components.sourceComponents.SwitchSource;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Engine
{

  public static void main(String[] args)
  {
    Engine.runApp();
  }

  public static JFrame frame = new JFrame("jateSim");
  public static MyBar menuBar = new MyBar();
  public static ContentPanel contentPanel = new ContentPanel();
  public static ControlPanel controlPanel = new ControlPanel();
  public static Mode mode = Mode.DEFAULT;
  public static SimClock clock = new SimClock(1);

  public Engine(int width, int height)
  {
    try {
      frame.setIconImage(ImageIO.read(new File("jateSim/src/res/icon.png")));
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
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

  private static void runApp()
  {
    new Engine(800, 800);

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
    Rectangle r = contentPanel.getVisibleRect();
    switch(type) {
      case AND:
        new AndGate(r.width / 2, r.height / 2, contentPanel);
        break;
      case OR:
        new OrGate(r.width / 2, r.height / 2, contentPanel);
        break;
      case NAND:
        new NandGate(r.width / 2, r.height / 2, contentPanel);
        break;
      case NOR:
        new NorGate(r.width / 2, r.height / 2, contentPanel);
        break;
      case NOT:
        new NotGate(r.width / 2, r.height / 2, contentPanel);
        break;
      default:
        break;
    }
    contentPanel.repaint();
  }

  public static void addDisplayComponent(DisplayComponentType type)
  {
    Rectangle r = contentPanel.getVisibleRect();
    switch(type) {
      case BIT:
        new BitDisplay(r.width / 2, r.height / 2, contentPanel);
        break;
      default:
        break;
    }
    contentPanel.repaint();
  }

  public static void addSourceComponent(SourceComponentType type)
  {
    Rectangle r = contentPanel.getVisibleRect();
    switch(type) {
      case SWITCH:
        new SwitchSource(r.width / 2, r.height / 2, contentPanel);
        break;
      default:
        break;
    }
    contentPanel.repaint();
  }

  public static void removeEverything()
  {
    SimComponent.deleteAllComponents();
  }

  /*
  Update process
  1. update input pins using signal.update()
  2. update output pins using gate.update()
  3. update display components
  This is one clock tick
  This way u can only use synchronized systems with one clock
  In the future create a clock component, so you can give clock signals to different components
   */

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
