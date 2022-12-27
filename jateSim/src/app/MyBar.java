package app;

import components.displayComponents.DisplayComponent;
import components.displayComponents.DisplayComponentType;
import components.gates.GateType;
import components.sourceComponents.SourceComponentType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyBar extends JMenuBar implements ActionListener
{
  JMenu file = new JMenu("File");
  JMenuItem save = new JMenuItem("Save");
  JMenuItem load = new JMenuItem("Load");

  JMenu edit = new JMenu("Edit");
  JMenu addSourceComponent = new JMenu("Add source component");
  JMenuItem switchSource = new JMenuItem("Switch");
  JMenu addDisplayComponent = new JMenu("Add display component");
  JMenuItem bitDisplay = new JMenuItem("Bit display");
  JMenu addGate = new JMenu("Add gate");
  JMenuItem AND = new JMenuItem("AND Gate");
  JMenuItem OR = new JMenuItem("OR Gate");
  JMenuItem NAND = new JMenuItem("NAND Gate");
  JMenuItem NOR = new JMenuItem("NOR Gate");
  JMenuItem NOT = new JMenuItem("NOT Gate");

  JMenu simulate = new JMenu("Simulate");
  JMenuItem start = new JMenuItem("Start simulation");
  JMenuItem stop = new JMenuItem("Stop simulation");
  JMenu setSpeed = new JMenu("Set clock speed");
  JMenuItem oneEight = new JMenuItem("1/8");
  JMenuItem oneFour = new JMenuItem("1/4");
  JMenuItem oneTwo = new JMenuItem("1/2");
  JMenuItem oneOne = new JMenuItem("1/1");

  public MyBar()
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
    this.addSourceComponent.add(switchSource);
    this.addDisplayComponent.add(bitDisplay);
    this.addGate.add(AND);
    this.addGate.add(OR);
    this.addGate.add(NAND);
    this.addGate.add(NOR);
    this.addGate.add(NOT);
    this.edit.add(addGate);
    this.edit.add(addDisplayComponent);
    this.edit.add(addSourceComponent);
  }

  private void initSimulate()
  {
    this.setSpeed.add(oneOne);
    this.setSpeed.add(oneTwo);
    this.setSpeed.add(oneFour);
    this.setSpeed.add(oneEight);
    this.simulate.add(start);
    this.simulate.add(stop);
    this.simulate.add(setSpeed);
  }

  private void addListeners()
  {
    save.addActionListener(this);
    load.addActionListener(this);
    AND.addActionListener(this);
    NAND.addActionListener(this);
    OR.addActionListener(this);
    NOR.addActionListener(this);
    NOT.addActionListener(this);
    bitDisplay.addActionListener(this);
    switchSource.addActionListener(this);
    oneOne.addActionListener(this);
    oneTwo.addActionListener(this);
    oneFour.addActionListener(this);
    oneEight.addActionListener(this);
    start.addActionListener(this);
    stop.addActionListener(this);
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
      case "NOT Gate":
        Engine.addGate(GateType.NOT);
        break;
      case "Bit display":
        Engine.addDisplayComponent(DisplayComponentType.BIT);
        break;
      case "Switch":
        Engine.addSourceComponent(SourceComponentType.SWITCH);
        break;
      case "Start simulation":
        Engine.startSimulation();
        break;
      case "Stop simulation":
        Engine.stopSimulation();
        break;
      case "1/1":
        Engine.clock.changeSpeed(1);
        break;
      case "1/2":
        Engine.clock.changeSpeed(2);
        break;
      case "1/4":
        Engine.clock.changeSpeed(4);
        break;
      case "1/8":
        Engine.clock.changeSpeed(8);
        break;
      default:
        break;
    }
  }
}
