package components;

import app.Engine;
import app.Mode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class TextComponent extends SimComponent
{
  public static ArrayList<TextComponent> textComponents = new ArrayList<>();

  private TextComponent(int x, int y, int w, int h, String text, JPanel parentPanel)
  {
    super(x, y, w, h, text, parentPanel);
    getLabel().setBackground(Color.lightGray);
    textComponents.add(this);
  }

  public static void getInstance(int x, int y, JPanel parentPanel)
  {
    String text = JOptionPane.showInputDialog("Enter the text.");
    Graphics g = Engine.getContentPanel().getGraphics();
    FontMetrics fm = g.getFontMetrics();
    Rectangle2D bounds = fm.getStringBounds(text, g);
    new TextComponent(x, y, (int) bounds.getWidth() + 10, (int) bounds.getHeight() + 5, text, parentPanel);
  }

  @Override
  public void deleteComponent()
  {
    super.deleteComponent();
    textComponents.remove(this);
  }

  @Override
  public void setLocation(int x, int y)
  {
    if(Engine.getMode() != Mode.MOVE) return;
    if(x > getParent().getWidth() || x < 0 || y > getParent().getHeight() || y < 0) return;
    int a = x - getLabel().getWidth() / 2;
    int b = y - getLabel().getHeight() / 2;
    getLabel().setBounds(a, b, getLabel().getWidth(), getLabel().getHeight());
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(Engine.getMode() == Mode.DEFAULT && e.getButton() == MouseEvent.BUTTON3) {
      JMenuItem remove = new JMenuItem("Remove component");
      remove.addActionListener(a -> deleteComponent());
      JMenuItem name = new JMenuItem("Change text");
      name.addActionListener(a -> changeText());
      JPopupMenu popupMenu = new JPopupMenu();
      popupMenu.add(remove);
      popupMenu.add(name);
      popupMenu.show(getParent(), getX() + getLabel().getWidth() / 2, getY() + getLabel().getHeight() / 2);
    }
  }

  private void changeText()
  {
    String text = JOptionPane.showInputDialog("Enter the text.");
    Graphics g = Engine.getContentPanel().getGraphics();
    FontMetrics fm = g.getFontMetrics();
    Rectangle2D bounds = fm.getStringBounds(text, g);
    getLabel().setBounds(getX(), getY(), (int) bounds.getWidth() + 10, (int) bounds.getHeight() + 5);
    getLabel().setText(text);
  }
}
