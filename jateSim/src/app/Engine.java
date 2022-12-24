package app;

import components.*;
import components.gates.AndGate;

import javax.swing.*;

import java.awt.*;


public class Engine{

    public static JFrame frame = new JFrame("gSim");
    public static ButtonPanel buttonsPanel = new ButtonPanel(0,0,0,0, Color.darkGray, "buttons");

    public static myPanel contentPanel = new myPanel(0,0,0,0, Color.lightGray, "content");
    public static myButton testButton = new myButton("Test");

    public static myBar menuBar = new myBar();
    public static AndGate testComp = new AndGate(50, 50, 30, 30, "AND", contentPanel);

    public static Mode mode = Mode.MOVE;

    public Engine(int width, int height){
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new FrameListener());
        frame.addKeyListener(new FrameListener());
        frame.setSize(width, height);
        contentPanel.setLayout(null);
        buttonsPanel.addButton(testButton);
        frame.setJMenuBar(menuBar);
        frame.add(buttonsPanel);
        frame.add(contentPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public static void updateSize(){
        Rectangle r = frame.getContentPane().getBounds();
        contentPanel.setBounds(0, 0, r.width, r.height - ButtonPanel.height);
        buttonsPanel.setBounds(r.x, r.height - ButtonPanel.height, r.width, ButtonPanel.height);
    }

    public static void setMode(Mode m){
        mode = m;
    }
}
