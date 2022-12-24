package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myBar extends JMenuBar implements ActionListener
{
  JMenu file = new JMenu("File");
  JMenuItem save = new JMenuItem("Save");
  JMenuItem load = new JMenuItem("Load");

  JMenu edit = new JMenu("Edit");
  JMenu addGate = new JMenu("Add gate");
  JMenuItem AND = new JMenuItem("AND Gate");

  JMenu simulate = new JMenu("Simulate");
  JMenuItem start = new JMenuItem("Start simulation");
  JMenu setSpeed = new JMenu("Set clock speed");
  JMenuItem slow = new JMenuItem("1/8");

  public myBar()
  {
    initFile();
    initEdit();
    initSimulate();
    addListeners();
    add(file);
    add(edit);
    add(simulate);
  }

  private void initFile()
  {
    this.file.add(save);
    this.file.add(load);
  }

  private void initEdit()
  {
    this.addGate.add(AND);
    this.edit.add(addGate);
  }

  private void initSimulate()
  {
    this.setSpeed.add(slow);
    this.simulate.add(setSpeed);
    this.simulate.add(start);
  }

  private void addListeners()
  {
    save.addActionListener(this);
    load.addActionListener(this);
    AND.addActionListener(this);
    slow.addActionListener(this);
    start.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    System.out.println(e.getActionCommand());
  }
}
