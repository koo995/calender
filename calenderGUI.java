import java.awt.*;
import javax.swing.*;

public class calenderGUI {
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                scheduleFrame frame = new scheduleFrame();
                frame.setTitle("Scheduler");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
