package components.boxer;

import app.Engine;
import app.Mode;
import components.SimComponent;
import components.displayComponents.DisplayComponent;
import components.gates.Gate;
import components.pins.Pin;
import components.signals.Signal;
import components.sourceComponents.SourceComponent;
import simUtils.SimNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BlackBox extends SimComponent
{
  public static Dimension SIZE = new Dimension(100, 100);
  public static ArrayList<BlackBox> blackBoxes = new ArrayList<>();

  private ArrayList<Pin> inputs;
  private ArrayList<Pin> outputs;
  private ArrayList<Gate> gates;
  private ArrayList<Signal> signals;

  private BlackBox(int x, int y, ArrayList<Pin> inputs, ArrayList<Pin> outputs, ArrayList<Gate> gates,
                   ArrayList<Signal> signals, JPanel parentPanel)
  {
    super(x, y, SIZE.width, SIZE.height, "Unnamed", parentPanel);
    this.inputs = inputs;
    this.outputs = outputs;
    this.gates = gates;
    this.signals = signals;
    if(inputs.size() > 6 || outputs.size() > 6) {
      getLabel().setBounds(getX(), getY(), SIZE.width, 2 * SIZE.height);
    }
    init();
    blackBoxes.add(this);
    Signal.repositionSignals();
  }

  public void changeName()
  {
    String n = JOptionPane.showInputDialog("Please enter a new name");
    getLabel().setText(n);
  }

  public void takeApart()
  {
    // Remove the black box from the list of black boxes and the parent panel
    blackBoxes.remove(this);
    getParent().remove(getLabel());

    // Add all the input and output pins to the parent panel
    for(Pin pin : inputs) {
      pin.getParent().add(pin.getLabel());
    }
    for(Pin pin : outputs) {
      pin.getParent().add(pin.getLabel());
    }

    // Add all the gates and signals to the parent panel
    for(Gate gate : gates) {
      gate.getParent().add(gate.getLabel());
      if(!gate.getParent().isAncestorOf(gate.getInput1().getLabel())) {
        gate.getParent().add(gate.getInput1().getLabel());
      }
      if(!gate.getParent().isAncestorOf(gate.getInput2().getLabel())) {
        gate.getParent().add(gate.getInput2().getLabel());
      }
      if(!gate.getParent().isAncestorOf(gate.getOutput().getLabel())) {
        gate.getParent().add(gate.getOutput().getLabel());
      }
      gate.updatePinLocations();
    }
    for(Signal signal : signals) {
      signal.unhide();
    }
    Engine.getContentPanel().update();
    Signal.repositionSignals();
  }

  public void cloneBox()
  {
    for(int i = 0; i < inputs.size(); i++) {

    }
    BlackBox clone = new BlackBox(getX(), getY(), inputs, outputs, gates, signals, getParent()); //TODO make a copy of these arrays
    clone.getLabel().setText(getLabel().getText());
    blackBoxes.add(clone);
    Engine.getContentPanel().update();
    Signal.repositionSignals();
  }


  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    blackBoxes.remove(this);
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.getMode() != Mode.MOVE) return;
    if(x > getParent().getWidth() || x < 0 || y > getParent().getHeight() || y < 0) return;
    int a = x - getLabel().getWidth() / 2;
    int b = y - getLabel().getHeight() / 2;
    getLabel().setBounds(a, b, getLabel().getWidth(), getLabel().getHeight());
    int gap = 0;
    for(Pin p : inputs) {
      p.getLabel().setBounds(getX() - Pin.SIZE.width, getY() + gap, Pin.SIZE.width, Pin.SIZE.height);
      gap = gap + Pin.SIZE.height + 5;
    }
    gap = 0;
    for(Pin p : outputs) {
      p.getLabel().setBounds(getX() + SIZE.width, getY() + gap, Pin.SIZE.width, Pin.SIZE.height);
      gap = gap + Pin.SIZE.height + 5;
    }
    Signal.repositionSignals();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(Engine.getMode() == Mode.DEFAULT && e.getButton() == MouseEvent.BUTTON3) {
      JMenuItem remove = new JMenuItem("Remove component");
      remove.addActionListener(a -> deleteComponent());
      JMenuItem name = new JMenuItem("Change name");
      name.addActionListener(a -> changeName());
      JMenuItem setBack = new JMenuItem("Take it apart");
      setBack.addActionListener(a -> takeApart());
      JMenuItem getClone = new JMenuItem("Make a copy of it");
      getClone.addActionListener(a -> cloneBox());
      JPopupMenu popupMenu = new JPopupMenu();
      popupMenu.add(remove);
      popupMenu.add(name);
      popupMenu.add(setBack);
      popupMenu.add(getClone);
      popupMenu.show(getParent(), getX() + getLabel().getWidth() / 2, getY() + getLabel().getHeight() / 2);
    } else if(Engine.getMode() == Mode.SELECT && e.getButton() == MouseEvent.BUTTON1) {
      selectComponent();
    } else if(Engine.getMode() == Mode.SELECT && e.getButton() == MouseEvent.BUTTON3) {
      deselectComponent();
    }
  }

  private void init()
  {
    for(Gate g : gates) {
      g.getParent().remove(g.getLabel());
    }
    for(Signal s : signals) {
      s.hide();
    }
    int gap = 0;
    for(Pin p : inputs) {
      p.getLabel().setBounds(getX() - Pin.SIZE.width, getY() + gap, Pin.SIZE.width, Pin.SIZE.height);
      gap = gap + Pin.SIZE.height + 5;
    }
    gap = 0;
    for(Pin p : outputs) {
      p.getLabel().setBounds(getX() + SIZE.width, getY() + gap, Pin.SIZE.width, Pin.SIZE.height);
      gap = gap + Pin.SIZE.height + 5;
    }
  }

  public static void factory(int x, int y, JPanel parentPanel)
  {
    new SimNotification("Trying to create a BlackBox");
    ArrayList<Pin> inputsTmp = new ArrayList<>();
    ArrayList<Pin> outputsTmp = new ArrayList<>();
    ArrayList<Gate> gatesTmp = new ArrayList<>();
    ArrayList<Signal> signalsTmp = new ArrayList<>();
    for(SimComponent c : SimComponent.selectedComponents) {
      if(c instanceof SourceComponent || c instanceof DisplayComponent) {
        new SimNotification("Couldn't create the BlackBox please don't include display or source components");
        return;
      } else if(c instanceof Gate) {
        gatesTmp.add((Gate) c);
        if(((Gate) c).getOutput().connectedSignals.size() == 0 ||
                SimComponent.selectedComponents.contains(((Gate) c).getOutput())) {
          outputsTmp.add(((Gate) c).getOutput());
        } else {
          Engine.getContentPanel().remove(((Gate) c).getOutput().getLabel());
          for(Signal s : ((Gate) c).getOutput().connectedSignals) {
            if(!signalsTmp.contains(s)) signalsTmp.add(s);
          }
        }
        if(((Gate) c).getInput1().connectedSignals.size() == 0) {
          inputsTmp.add(((Gate) c).getInput1());
        } else {
          Engine.getContentPanel().remove(((Gate) c).getInput1().getLabel());
          for(Signal s : ((Gate) c).getInput1().connectedSignals) {
            if(!signalsTmp.contains(s)) signalsTmp.add(s);
          }
        }
        if(((Gate) c).getInput2().connectedSignals.size() == 0) {
          inputsTmp.add(((Gate) c).getInput2());
        } else {
          Engine.getContentPanel().remove(((Gate) c).getInput2().getLabel());
          for(Signal s : ((Gate) c).getInput2().connectedSignals) {
            if(!signalsTmp.contains(s)) signalsTmp.add(s);
          }
        }
      }
    }
    new BlackBox(x, y, inputsTmp, outputsTmp, gatesTmp, signalsTmp, parentPanel);
  }
}
