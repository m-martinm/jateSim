package app;

import javax.swing.*;
import java.awt.*;


public class myButton extends JButton {

    public myButton(String text){
        setText(text);
        setBackground(Color.gray);
        setBorderPainted(false);
        setFocusPainted(false);
        setFocusable(false);
    }
}
