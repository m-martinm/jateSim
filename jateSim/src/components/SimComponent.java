package components;

import app.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SimComponent implements MouseListener, MouseMotionListener {
    public JLabel label;
    public JPanel parent;
    public JPopupMenu popupMenu;
    public JMenuItem remove = new JMenuItem("Remove component");

    public SimComponent(int x, int y, int w, int h, String text, JPanel parentPanel) {
        this.label = new JLabel(text);
        //TODO set color now working
        this.label.setBackground(Color.cyan);
        this.label.setBounds(x, y, w, h);
        this.label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label.addMouseListener(this);
        this.label.addMouseMotionListener(this);
        this.parent = parentPanel;
        this.parent.add(this.label);
    }

    public Rectangle getRect(){
        return new Rectangle(getX(), getY(), getW(), getH());
    }

    public int getX(){
        return this.label.getX();
    }

    public int getY(){
        return this.label.getY();
    }

    public int getW(){
        return this.label.getWidth();
    }

    public int getH(){
        return this.label.getHeight();
    }

    public void setLocation(int x, int y) {
        if (x > this.parent.getWidth() || x < 0 || y > this.parent.getHeight() || y < 0) return;
        this.label.setBounds(x - this.label.getWidth() / 2, y - this.label.getHeight() / 2, this.label.getWidth(),
                             this.label.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.popupMenu = new JPopupMenu();
            this.popupMenu.add(this.remove);
            this.popupMenu.show(this.parent, this.getX() + this.label.getWidth() / 2,
                                this.getY() + this.label.getHeight() / 2);
            System.out.println(getRect().toString());
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point m = MouseInfo.getPointerInfo().getLocation();
        Point c = this.parent.getLocationOnScreen();
        setLocation(m.x - c.x, m.y - c.y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
