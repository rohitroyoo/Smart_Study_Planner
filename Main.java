import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Smart Study Planner with Pomodoro Timer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 550);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout(10, 10));

            TimerPanel timerPanel = new TimerPanel();
            TaskManager taskManager = new TaskManager();

            frame.add(timerPanel, BorderLayout.NORTH);
            frame.add(taskManager, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}