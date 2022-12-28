package components.pins;

import app.Engine;
import app.Mode;
import app.SimNotification;
import components.SimComponent;
import components.signals.Signal;
import simNotifier.SimObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Pin extends SimComponent implements MouseListener
{
  public static final int HIGH = 1;
  public static final int LOW = 0;
  public static final int UNKNOWN = -1;
  public static final Dimension size = new Dimension(15, 15);
  public static ArrayList<Pin> pins = new ArrayList<>();
  public static Pin selectedPin = null;

  public SimObservable value;
  public SimComponent parentSimComponent;
  public PinType type;
  boolean selected;
  public ArrayList<Signal> connectedSignals= new ArrayList<>();

  public Pin(int x, int y, String text, JPanel parentPanel, SimComponent parentSimComponent, PinType type)
  {
    super(x, y, size.width, size.height, text, parentPanel);
    this.parentSimComponent = parentSimComponent;
    this.type = type;
    this.value = new SimObservable(UNKNOWN);
    this.selected = false;
    pins.add(this);
  }

  public int getValue()
  {
    return this.value.getData();
  }

  public void setValue(int newValue)
  {
    this.value.setData(newValue);
  }

  public void selectPin()
  {
    this.selected = true;
    selectedPin = this;
    this.label.setBackground(Color.orange);
  }

  public void deselectPin()
  {
    this.selected = false;
    selectedPin = null;
    this.label.setBackground(Color.gray);
  }

  public static void deselectAll()
  {
    for(Pin p : pins) {
      p.deselectPin();
    }
  }

  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    if(this.type == PinType.OUTPUT) value.deleteObservers();
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(Engine.mode != Mode.CONNECT) return;
    if(selectedPin == null) {
      this.selectPin();

    } else if(selectedPin.type == PinType.INPUT && this.type == PinType.OUTPUT) {

      if(!Signal.createSignal(selectedPin, this)) {
        new SimNotification("Pins are already connected!");
      } else {
        selectedPin.deselectPin();
      }

    } else if(selectedPin.type == PinType.OUTPUT && this.type == PinType.INPUT) {
      if(!Signal.createSignal(this, selectedPin)) {
        new SimNotification("Pins are already connected!");
      } else selectedPin.deselectPin();

    } else if(this == selectedPin) {
      selectedPin.deselectPin();

    } else {
      new SimNotification("Invalid connection!");
    }

  }
}