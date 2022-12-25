package app;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JToolBar
{
  private static final int gap = 10;
  public static final int height = 50;

  private MyButton resetButton;
  private JLabel modeLabel;

  ControlPanel()
  {
    setBackground(Color.gray);
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    setFloatable(false);

    this.modeLabel = new JLabel("DEFAULT");
    this.modeLabel.setForeground(Color.lightGray);
    this.modeLabel.setToolTipText("(M)OVE, (C)ONNECT, (S)ELECT");
    this.resetButton = new MyButton("Reset position");
    this.resetButton.addActionListener(e -> Engine.contentPanel.resetPanel());
    add(Box.createRigidArea(new Dimension(gap, 0)));
    add(this.modeLabel);
    add(Box.createRigidArea(new Dimension(gap, 0)));
    add(this.resetButton);
  }

  public void setModeText(String s)
  {
    this.modeLabel.setText(s);
  }
}
