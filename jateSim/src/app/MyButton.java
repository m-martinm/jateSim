package app;

import javax.swing.*;
import java.awt.*;


public class MyButton extends JButton
{

  public MyButton(String text)
  {
    setText(text);
    setBackground(Color.lightGray);
    setFocusPainted(false);
    setFocusable(false);
  }
}
