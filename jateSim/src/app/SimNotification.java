package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimNotification implements ActionListener
{
  private static final Dimension size = new Dimension(200, 100);
  private static final Dimension offset = new Dimension(10, 10);
  private Timer timer;
  private JDialog dialog;
  private JTextArea textArea;
  private static ArrayList<SimNotification> notifications = new ArrayList<>();

  public SimNotification(String message)
  {
    if(notifications.size() != 0) closeAll();
    notifications.add(this);
    this.textArea = new JTextArea(2, 30);
    this.textArea.setText(message);
    this.textArea.setWrapStyleWord(true);
    this.textArea.setLineWrap(true);
    this.textArea.setOpaque(false);
    this.textArea.setEditable(false);
    this.textArea.setFocusable(false);
    this.textArea.setBackground(UIManager.getColor("Label.background"));
    this.textArea.setFont(UIManager.getFont("Label.font"));
    this.textArea.setBorder(UIManager.getBorder("Label.border"));

    this.dialog = new JDialog(Engine.frame, "Message");
    this.dialog.setSize(size);
    this.dialog.setLayout(new GridBagLayout());
    this.dialog.add(textArea);
    this.timer = new Timer(2000, this);
    this.timer.setRepeats(false);
    this.dialog.setLocation(Engine.frame.getLocation().x + Engine.frame.getWidth() - size.width - offset.width,
                            Engine.frame.getLocation().y + Engine.frame.getHeight() - size.height - offset.height);
    this.dialog.setVisible(true);
    this.timer.start();
  }

  public SimNotification(String message, boolean permanent)
  {
    if(notifications.size() != 0) closeAll();
    if(false) new SimNotification(message);
    else {
      notifications.add(this);
      this.textArea = new JTextArea(2, 30);
      this.textArea.setText(message);
      this.textArea.setWrapStyleWord(true);
      this.textArea.setLineWrap(true);
      this.textArea.setOpaque(false);
      this.textArea.setEditable(false);
      this.textArea.setFocusable(false);
      this.textArea.setBackground(UIManager.getColor("Label.background"));
      this.textArea.setFont(UIManager.getFont("Label.font"));
      this.textArea.setBorder(UIManager.getBorder("Label.border"));

      this.dialog = new JDialog(Engine.frame, "Message");
      this.dialog.setSize(size);
      this.dialog.setLayout(new GridBagLayout());
      this.dialog.add(textArea);
      this.dialog.setLocation(Engine.frame.getLocation().x + Engine.frame.getWidth() - size.width - offset.width,
                              Engine.frame.getLocation().y + Engine.frame.getHeight() - size.height - offset.height);
      this.dialog.setVisible(true);
    }
  }

  private void closeAll()
  {
    for(SimNotification n : notifications) {
      n.dialog.setVisible(false);
      n.dialog.dispose();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    dialog.setVisible(false);
    dialog.dispose();
  }
}
