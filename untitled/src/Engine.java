import javax.swing.*;

public class Engine{

    public JFrame frame = new JFrame();
    public JLabel message = new JLabel("Hello World!");
    Engine(int width, int height){
        this.frame.add(this.message);
        this.frame.setSize(width, height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
