package app;

import components.SimComponent;
import components.SimComponentFactory;

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
  JMenuItem andGate = new JMenuItem("AND Gate");
  JMenuItem orGate = new JMenuItem("OR Gate");
  JMenuItem nandGate = new JMenuItem("NAND Gate");
  JMenuItem norGate = new JMenuItem("NOR Gate");
  JMenuItem notGate = new JMenuItem("NOT Gate");

  JMenu simulate = new JMenu("Simulate");
  JMenuItem start = new JMenuItem("Start simulation");
  JMenuItem stop = new JMenuItem("Stop simulation");
  JMenu setSpeed = new JMenu("Set clock speed");
  JMenuItem oneEight = new JMenuItem("1/8");
  JMenuItem oneFour = new JMenuItem("1/4");
  JMenuItem oneTwo = new JMenuItem("1/2");
  JMenuItem oneOne = new JMenuItem("1/1");

  JMenu select = new JMenu("Select");
  JMenuItem removeSelected = new JMenuItem("Remove selected items");
  JMenuItem blackBox = new JMenuItem("Selected > blackbox");

  public MyBar()
  {
    initFile();
    initEdit();
    initSimulate();
    initSelect();
    addListeners();
    add(file);
    add(edit);
    add(simulate);
    add(select);
  }

  private void initFile()
  {
    file.add(save);
    file.add(load);
  }

  private void initEdit()
  {
    addSourceComponent.add(switchSource);
    addDisplayComponent.add(bitDisplay);
    addGate.add(andGate);
    addGate.add(orGate);
    addGate.add(nandGate);
    addGate.add(norGate);
    addGate.add(notGate);
    edit.add(addGate);
    edit.add(addDisplayComponent);
    edit.add(addSourceComponent);
  }

  private void initSimulate()
  {
    setSpeed.add(oneOne);
    setSpeed.add(oneTwo);
    setSpeed.add(oneFour);
    setSpeed.add(oneEight);
    simulate.add(start);
    simulate.add(stop);
    simulate.add(setSpeed);
  }

  private void initSelect()
  {
    select.add(removeSelected);
    select.add(blackBox);
  }

  private void addListeners()
  {
    save.addActionListener(this);
    load.addActionListener(this);
    andGate.addActionListener(e -> SimComponentFactory.createAndGate());
    nandGate.addActionListener(e -> SimComponentFactory.createNandGate());
    orGate.addActionListener(e -> SimComponentFactory.createOrGate());
    norGate.addActionListener(e -> SimComponentFactory.createNorGate());
    notGate.addActionListener(e -> SimComponentFactory.createNotGate());
    bitDisplay.addActionListener(e -> SimComponentFactory.createBitDisplay());
    switchSource.addActionListener(e -> SimComponentFactory.createSwitchSource());
    oneOne.addActionListener(this);
    oneTwo.addActionListener(this);
    oneFour.addActionListener(this);
    oneEight.addActionListener(this);
    start.addActionListener(this);
    stop.addActionListener(this);
    removeSelected.addActionListener((e) -> {
      System.out.println(SimComponent.selectedComponents);
      SimComponentFactory.removeSelectedItems();
    });
    blackBox.addActionListener(e -> SimComponentFactory.createBlackBox());
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch(e.getActionCommand()) {
      case "Start simulation":
        Engine.startSimulation();
        break;
      case "Stop simulation":
        Engine.stopSimulation();
        break;
      case "1/1":
        Engine.getClock().changeSpeed(1);
        break;
      case "1/2":
        Engine.getClock().changeSpeed(2);
        break;
      case "1/4":
        Engine.getClock().changeSpeed(4);
        break;
      case "1/8":
        Engine.getClock().changeSpeed(8);
        break;
      default:
        break;
    }
  }
}
