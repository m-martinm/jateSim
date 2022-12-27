package app;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JToolBar
{
  private static final int gap = 10;
  public static final int height = 50;

  private MyButton resetPositionButton;
  private JLabel modeLabel;
  private MyButton tickClockButton;
  private MyButton resetComponentsButton;

  ControlPanel()
  {
    setBackground(Color.gray);
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    setFloatable(false);

    this.modeLabel = new JLabel("DEFAULT");
    this.modeLabel.setForeground(Color.lightGray);
    this.modeLabel.setToolTipText("(M)OVE, (C)ONNECT, (S)ELECT");
    this.resetPositionButton = new MyButton("Reset position");
    this.resetPositionButton.addActionListener(e -> Engine.contentPanel.resetPanel());
    this.tickClockButton = new MyButton("Tick clock");
    this.tickClockButton.addActionListener((e) -> {
      if(!Engine.clock.isRunning()) {
        Engine.updateCycle();
        new SimNotification("Clock ticked!");
      } else {
        new SimNotification("Simulation already running!");
      }
    });
    this.resetComponentsButton = new MyButton("Reset canvas");
    this.resetComponentsButton.addActionListener(e -> Engine.removeEverything());
    add(Box.createRigidArea(new Dimension(gap, 0)));
    add(this.modeLabel);
    add(Box.createRigidArea(new Dimension(gap, 0)));
    add(this.resetPositionButton);
    add(Box.createRigidArea(new Dimension(gap, 0)));
    add(this.resetComponentsButton);
    add(Box.createRigidArea(new Dimension(gap, 0)));
    add(this.tickClockButton);
  }

  public void setModeText(String s)
  {
    this.modeLabel.setText(s);
  }
}
