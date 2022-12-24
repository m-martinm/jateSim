package components;

public class Signal{
    Pin input;
    Pin output;
    public Signal(Pin inp, Pin out){
        this.input = inp;
        this.output = out;
    }

    public void update(){
        this.output.setValue(this.input.getValue());
    }
}
