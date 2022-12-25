package app;

import components.gates.Gate;
import components.gates.GateType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO finish class
public class myBar extends JMenuBar implements ActionListener
{
  JMenu file = new JMenu("File");
  JMenuItem save = new JMenuItem("Save");
  JMenuItem load = new JMenuItem("Load");

  JMenu edit = new JMenu("Edit");
  JMenu addGate = new JMenu("Add gate");
  JMenuItem AND = new JMenuItem("AND Gate");
  JMenuItem OR = new JMenuItem("OR Gate");
  JMenuItem NAND = new JMenuItem("NAND Gate");
  JMenuItem NOR = new JMenuItem("NOR Gate");


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
    this.addGate.add(OR);
    this.addGate.add(NAND);
    this.addGate.add(NOR);
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
    NAND.addActionListener(this);
    OR.addActionListener(this);
    NOR.addActionListener(this);
    slow.addActionListener(this);
    start.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch(e.getActionCommand()) {
      case "OR Gate":
        Engine.addGate(GateType.OR);
        break;
      case "AND Gate":
        Engine.addGate(GateType.AND);
        break;
      case "NAND Gate":
        Engine.addGate(GateType.NAND);
        break;
      case "NOR Gate":
        Engine.addGate(GateType.NOR);
        break;
      default:
        break;
    }
  }
}
