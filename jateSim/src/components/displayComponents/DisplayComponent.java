package components.displayComponents;

import components.SimComponent;

import javax.swing.*;
import java.util.ArrayList;

public class DisplayComponent extends SimComponent
{

  public static ArrayList<DisplayComponent> displayComponents = new ArrayList<>();

  public DisplayComponent(int x, int y, int w, int h, String text, JPanel parentPanel)
  {
    super(x, y, w, h, text, parentPanel);
    displayComponents.add(this);
  }

  public void update()
  {
  }

  public static void updateDisplayComponents()
  {
    for(DisplayComponent d : displayComponents) {
      d.update();
    }
  }

}
