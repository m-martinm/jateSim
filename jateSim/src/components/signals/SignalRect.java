package components.signals;

import java.awt.*;
import java.awt.geom.Line2D;

public class SignalRect extends Rectangle
{
  private static final int OFFSET = 2;
  private static final int DIST = 10;
  private Line2D line1;
  private Line2D line2;

  public SignalRect(Signal s)
  {
    super(s.getOutputPoint().x, s.getOutputPoint().y, s.getInputPoint().x - s.getOutputPoint().x,
          s.getInputPoint().y - s.getOutputPoint().y);
    this.line1 = new Line2D.Double(s.getOutputPoint(), s.getInputPoint());
    this.line2 = null;
  }

  public void updateRect(Signal s)
  {
    if(s.getOutputPoint().x < s.getInputPoint().x && s.getOutputPoint().y < s.getInputPoint().y) {
      setBounds(s.getOutputPoint().x - OFFSET, s.getOutputPoint().y - OFFSET,
                s.getInputPoint().x - s.getOutputPoint().x + 2 * OFFSET,
                s.getInputPoint().y - s.getOutputPoint().y + 2 * OFFSET);
    } else if(s.getOutputPoint().x < s.getInputPoint().x && s.getOutputPoint().y > s.getInputPoint().y) {
      setBounds(s.getOutputPoint().x - OFFSET, s.getInputPoint().y - OFFSET,
                s.getInputPoint().x - s.getOutputPoint().x + 2 * OFFSET,
                s.getOutputPoint().y - s.getInputPoint().y + 2 * OFFSET);
    } else if(s.getOutputPoint().x > s.getInputPoint().x && s.getOutputPoint().y < s.getInputPoint().y) {
      setBounds(s.getInputPoint().x - OFFSET, s.getOutputPoint().y - OFFSET,
                s.getOutputPoint().x - s.getInputPoint().x + 2 * OFFSET,
                s.getInputPoint().y - s.getOutputPoint().y + 2 * OFFSET);
    } else if(s.getOutputPoint().x > s.getInputPoint().x && s.getOutputPoint().y > s.getInputPoint().y) {
      setBounds(s.getInputPoint().x - OFFSET, s.getInputPoint().y - OFFSET,
                s.getOutputPoint().x - s.getInputPoint().x + 2 * OFFSET,
                s.getOutputPoint().y - s.getInputPoint().y + 2 * OFFSET);
    }
    switch(s.getDrawingMethod()) {
      case STRAIGHT:
        line1 = new Line2D.Double(s.getOutputPoint().x, s.getOutputPoint().y, s.getInputPoint().x, s.getInputPoint().y);
        line2 = null;
        break;
      case LOWER:
        line1 = new Line2D.Double(s.getOutputPoint().x, s.getOutputPoint().y, s.getOutputPoint().x,
                                  s.getInputPoint().y);
        line2 = new Line2D.Double(s.getOutputPoint().x, s.getInputPoint().y, s.getInputPoint().x, s.getInputPoint().y);
        break;
      case UPPER:
        line1 = new Line2D.Double(s.getOutputPoint().x, s.getOutputPoint().y, s.getInputPoint().x,
                                  s.getOutputPoint().y);
        line2 = new Line2D.Double(s.getInputPoint().x, s.getOutputPoint().y, s.getInputPoint().x, s.getInputPoint().y);
        break;
      default:
        break;
    }
  }

  public boolean isClicked(Point p, Signal s)
  {
    if(s.isHidden()) return false;
    switch(s.getDrawingMethod()) {
      case STRAIGHT:
        double d = line1.ptSegDist(p);
        if(d <= DIST) return true;
        break;
      case LOWER:
      case UPPER:
        double d1 = line1.ptSegDist(p);
        double d2 = line2.ptSegDist(p);
        if(d1 <= DIST || d2 <= DIST) return true;
        break;
      default:
        return false;
    }
    return false;
  }

  public Line2D getLine1()
  {
    return line1;
  }

  public Line2D getLine2()
  {
    return line2;
  }
}