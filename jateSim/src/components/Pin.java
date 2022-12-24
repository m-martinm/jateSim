package components;

import javax.swing.*;

public class Pin extends SimComponent {
    public static final int HIGH = 1;
    public static final int LOW = 0;
    public static final int UNKNOWN = -1;

    int value;
    SimComponent parentSimComponent;
    PinType type;
    public Pin(int x, int y, int w, int h, String text, JPanel parentPanel, SimComponent parentSimComponent, PinType type) {
        super(x, y, w, h, text, parentPanel);
        this.parentSimComponent = parentSimComponent;
        this.type = type;
        this.value = UNKNOWN;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int newValue){
        this.value = newValue;
    }
}
