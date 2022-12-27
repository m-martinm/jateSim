package simNotifier;

import java.util.ArrayList;

public class SimObservable
{

  private int d;
  private ArrayList<SimObserver> observers;

  public SimObservable(int initialValue, SimObserver observer)
  {
    this.d = initialValue;
    this.observers = new ArrayList<>();
    this.observers.add(observer);
  }

  public SimObservable(int initialValue)
  {
    this.d = initialValue;
    this.observers = new ArrayList<>();
  }

  public void notifyObservers()
  {
    for(SimObserver o : observers) {
      o.updateObserver(this, this.d);
    }
  }

  public void addObserver(SimObserver o)
  {
    this.observers.add(o);
  }

  public void setData(int newData)
  {
    this.d = newData;
    this.notifyObservers();
  }


  public void deleteObservers()
  {
    this.observers.clear();
  }

  public int getData()
  {
    return this.d;
  }

}
