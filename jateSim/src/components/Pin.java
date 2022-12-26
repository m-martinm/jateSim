package components;

import app.Engine;
import app.Mode;
import app.SimNotification;
import simNotifier.SimObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;

// TODO https://stackoverflow.com/questions/15433855/how-to-create-change-listener-for-variable
// add a reference to its signal

public class Pin extends SimComponent implements MouseListener
{
  public static final int HIGH = 1;
  public static final int LOW = 0;
  public static final int UNKNOWN = -1;
  public static final Dimension size = new Dimension(15, 15);
  public static ArrayList<Pin> pins = new ArrayList<>();
  public static Pin selectedPin = null;

  SimObservable value;
  SimComponent parentSimComponent;
  PinType type;
  boolean selected;
  public ArrayList<Signal> connectedSignals;

  public Pin(int x, int y, String text, JPanel parentPanel, SimComponent parentSimComponent, PinType type)
  {
    super(x, y, size.width, size.height, text, parentPanel);
    this.parentSimComponent = parentSimComponent;
    this.type = type;
    this.value = new SimObservable(UNKNOWN);
    this.selected = false;
    this.connectedSignals = new ArrayList<>();
    pins.add(this);
  }

  public int getValue()
  {
    return this.value.getData();
  }

  public void setValue(int newValue)
  {
    // TODO notify observers and make the signals observers
    // if its an output pin
    this.value.setData(newValue); // maybe working should check
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
  public void setLocation(int x, int y)
  {
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
        //        this.connectedSignals.add(); // TODO finish: createSignal should return an object or null
      }

    } else if(selectedPin.type == PinType.OUTPUT && this.type == PinType.INPUT) {
      if(!Signal.createSignal(this, selectedPin)) {
        new SimNotification("Pins are already connected!");
      } else selectedPin.deselectPin(); //TODO same as above

    } else if(this == selectedPin) {
      selectedPin.deselectPin();

    } else {
      new SimNotification("Invalid connection!");
    }

  }
}
