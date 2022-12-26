package components.sourceComponents;

import components.SimComponent;

import javax.swing.*;
import java.util.ArrayList;

public class SourceComponent extends SimComponent
{
  public static ArrayList<SourceComponent> sourceComponents = new ArrayList<>();

  public SourceComponent(int x, int y, int w, int h, String text, JPanel parentPanel)
  {
    super(x, y, w, h, text, parentPanel);
    sourceComponents.add(this);
  }

  public void update()
  {

  }
}
