package app;

import components.*;
import components.gates.*;

import javax.swing.*;

import java.awt.*;


public class Engine
{

  public static JFrame frame = new JFrame("jateSim");
  public static ButtonPanel buttonsPanel = new ButtonPanel(0, 0, 0, 0, Color.darkGray, "buttons");

  public static myPanel contentPanel = new myPanel(0, 0, 0, 0, Color.lightGray, "content");
  public static myButton testButton = new myButton("Test");
  public static JLabel modeLabel = new JLabel("DEFAULT");

  public static myBar menuBar = new myBar();

  public static Mode mode = Mode.DEFAULT;

  public Engine(int width, int height)
  {
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addComponentListener(new FrameListener());
    frame.addKeyListener(new FrameListener());
    frame.setSize(width, height);
    contentPanel.setLayout(null);

    modeLabel.setForeground(Color.lightGray);
    modeLabel.setToolTipText("(M)OVE, (C)ONNECT, (S)ELECT");
    buttonsPanel.addButton(modeLabel);
    buttonsPanel.addButton(testButton);
    frame.setJMenuBar(menuBar);
    frame.add(buttonsPanel);
    frame.add(contentPanel);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);

  }

  public static void updateSize()
  {
    Rectangle r = frame.getContentPane().getBounds();
    contentPanel.setBounds(0, 0, r.width, r.height - ButtonPanel.height);
    buttonsPanel.setBounds(r.x, r.height - ButtonPanel.height, r.width, ButtonPanel.height);
  }

  public static void setMode(Mode m)
  {
    mode = m;
    modeLabel.setText(m.name());
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

  public static void tickClock()
  {
    // TODO implement
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
