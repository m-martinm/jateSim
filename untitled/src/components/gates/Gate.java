package components.gates;

import javax.swing.*;
import components.*;

import java.awt.*;

public class Gate extends SimComponent {
    Pin input1;
    Pin input2;
    Pin output;
    public Gate(int x, int y, int w, int h, String text, JPanel parentPanel) {
        super(x, y, 50, 70, text, parentPanel);
        /* TODO fix size and location of pins */
        this.input1 = new Pin(x-7, y-5, 15, 15, "1", this.parent, this, PinType.INPUT);
        this.input2 = new Pin(x-7, y-5, 15, 15, "2", this.parent, this, PinType.INPUT);
        this.output = new Pin(x+getW(), y+getH()/2, 15, 15, "O", this.parent, this, PinType.OUTPUT);
    }
    public int getOutput(){
        return this.output.getValue();
    }

    public void setLocation(int x, int y) {
        if (x > this.parent.getWidth() || x < 0 || y > this.parent.getHeight() || y < 0) return;
        this.label.setBounds(x - this.label.getWidth() / 2, y - this.label.getHeight() / 2, this.label.getWidth(),
                             this.label.getHeight());
        this.input1.setLocation(x-getW()/2-7, y - this.label.getHeight()/2+15);
        this.input2.setLocation(x-getW()/2-7, y + this.label.getHeight()/2-15);
        this.output.setLocation(x+getW()/2+7, y);
    }

}
