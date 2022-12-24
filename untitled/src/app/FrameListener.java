package app;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameListener implements ComponentListener, KeyListener {
    @Override
    public void componentResized(ComponentEvent e) {
        Engine.updateSize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'M':
                if(Engine.mode == Mode.MOVE) Engine.setMode(Mode.DEFAULT);
                else Engine.setMode(Mode.MOVE);
                break;
            case 'D':
                Engine.setMode(Mode.DEFAULT);
                break;
            case 'C':
                if(Engine.mode == Mode.CONNECT) Engine.setMode(Mode.DEFAULT);
                else Engine.setMode(Mode.CONNECT);
                break;
            case 'S':
                if(Engine.mode == Mode.SELECT) Engine.setMode(Mode.DEFAULT);
                else Engine.setMode(Mode.SELECT);
                break;
            default:
                break;
        }
        System.out.println(Engine.mode.toString());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
