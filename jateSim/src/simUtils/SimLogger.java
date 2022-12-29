package simUtils;

import components.SimComponent;
import components.displayComponents.DisplayComponent;
import components.gates.Gate;
import components.pins.Pin;
import components.signals.Signal;
import components.sourceComponents.SourceComponent;

import java.time.LocalDateTime;


public class SimLogger
{
  private static final String FILENAME = "jateSimLog.txt";
  private static boolean turned_on = false;
  private static boolean toConsole = false;

  //TODO add option to write to file
  public static void turnOn(boolean shouldWriteToConsole)
  {
    turned_on = true;
    toConsole = shouldWriteToConsole;
    System.out.println("Logger turned on: " + true + "\nOutput is written to console: " + shouldWriteToConsole);
  }

  public static void log(String message)
  {
    if(!turned_on) return;
    if(toConsole) {
      System.out.println("--- " + LocalDateTime.now() + " ---");
      System.out.println(message);
    }
  }

  public static void getDisplayedItems()
  {
    if(!turned_on) return;
    if(toConsole) {
      System.out.println("--- " + LocalDateTime.now() + " ---");
      System.out.println("Gates: " + Gate.gates.size());
      System.out.println("Pins: " + Pin.pins.size());
      System.out.println("Displays: " + DisplayComponent.displayComponents.size());
      System.out.println("Sources: " + SourceComponent.sourceComponents.size());
      System.out.println("Overall components: " + SimComponent.allComponents.size());
      System.out.println("Overall signals: " + Signal.signals.size());
    }
  }
}
