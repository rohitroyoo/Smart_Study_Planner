import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TimerPanel extends JPanel {
    private Timer timer;
    private int seconds = 25 * 60; // 25-minute timer
    private JLabel timeLabel;
    private JButton startBtn, pauseBtn, resetBtn;
    private boolean running = false;

    public TimerPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBorder(BorderFactory.createTitledBorder("Pomodoro Timer"));

        timeLabel = new JLabel(formatTime(seconds));
        timeLabel.setFont(new Font("Consolas", Font.BOLD, 36));

        startBtn = new JButton("Start");
        pauseBtn = new JButton("Pause");
        resetBtn = new JButton("Reset");

        startBtn.addActionListener(e -> startTimer());
        pauseBtn.addActionListener(e -> pauseTimer());
        resetBtn.addActionListener(e -> resetTimer());

        add(timeLabel);
        add(startBtn);
        add(pauseBtn);
        add(resetBtn);
    }

    private void startTimer() {
        if (!running) {
            running = true;
            timer = new Timer(1000, e -> {
                seconds--;
                timeLabel.setText(formatTime(seconds));
                if (seconds <= 0) {
                    timer.stop();
                    running = false;
                    JOptionPane.showMessageDialog(this, "Pomodoro complete! Time for a break.");
                }
            });
            timer.start();
        }
    }

    private void pauseTimer() {
        if (running && timer != null) {
            timer.stop();
            running = false;
        }
    }

    private void resetTimer() {
        if (timer != null) timer.stop();
        seconds = 25 * 60;
        running = false;
        timeLabel.setText(formatTime(seconds));
    }

    private String formatTime(int totalSeconds) {
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d", mins, secs);
    }
}