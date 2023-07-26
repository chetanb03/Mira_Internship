import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DigitalClockUI extends JFrame {
    private JLabel timeLabel;
    private JButton stopwatchButton;
    private JButton countdownButton;
    private JButton worldClockButton;
    private CountdownTimer countdownTimer;
    private Timer stopwatchTimer;
    private long stopwatchStartTime;
    private boolean stopwatchRunning;
     private int remainingTime;

    public DigitalClockUI() {
        setTitle("Digital Clock By Chetan Bhandari");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setLogo();

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        updateTime();

        JPanel buttonPanel = new JPanel();
        stopwatchButton = new JButton("Stopwatch");
        countdownButton = new JButton("Countdown");
        worldClockButton = new JButton("World Clock");

        buttonPanel.add(stopwatchButton);
        buttonPanel.add(countdownButton);
        buttonPanel.add(worldClockButton);

        add(timeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        stopwatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stopwatchRunning) {
                    startStopwatch();
                } else {
                    stopStopwatch();
                }
            }
        });

        countdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the countdown time
                String input = JOptionPane.showInputDialog(DigitalClockUI.this, "Enter countdown time (in seconds):");
                if (input != null && !input.isEmpty()) {
                    int countdownTime = Integer.parseInt(input);

                    // Start the countdown
                    startCountdown(countdownTime);
                }
            }
        });

        worldClockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the list of available time zones
                String[] availableTimeZones = TimeZone.getAvailableIDs();

                // Prompt the user to select a time zone
                String selectedTimeZone = (String) JOptionPane.showInputDialog(
                        DigitalClockUI.this,
                        "Select a time zone:",
                        "World Clock",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        availableTimeZones,
                        availableTimeZones[0]
                );

                // Display the time for the selected time zone
                if (selectedTimeZone != null) {
                    String time = getCurrentTimeInTimeZone(selectedTimeZone);
                    JOptionPane.showMessageDialog(DigitalClockUI.this, "Time in " + selectedTimeZone + ": " + time);
                }
            }
        });
    }

    private void updateTime() {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        timeLabel.setText(time);
    }

    private String getCurrentTimeInTimeZone(String timeZoneID) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }

     private void startCountdown(int countdownTime) {
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }

        remainingTime = countdownTime; // Initialize the remainingTime variable

        countdownTimer = new CountdownTimer(countdownTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--; // Decrease the remaining time

                if (remainingTime >= 0) {
                    String time = String.format("%02d:%02d:%02d", remainingTime / 3600, (remainingTime % 3600) / 60, remainingTime % 60);
                    timeLabel.setText(time);
                } else {
                    countdownTimer.stop();
                    JOptionPane.showMessageDialog(DigitalClockUI.this, "Countdown finished!");
                    updateTime();
                }
            }
        });

        countdownTimer.start();
    }
    
    private void startStopwatch() {
        stopwatchStartTime = System.currentTimeMillis();
        stopwatchRunning = true;
        stopwatchButton.setText("Stop Stopwatch");

        stopwatchTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - stopwatchStartTime;
                String time = formatElapsedTime(elapsedTime);
                timeLabel.setText(time);
            }
        });
        stopwatchTimer.start();
    }

    private void stopStopwatch() {
        stopwatchRunning = false;
        stopwatchButton.setText("Start Stopwatch");

        stopwatchTimer.stop();
        long elapsedTime = System.currentTimeMillis() - stopwatchStartTime;
        JOptionPane.showMessageDialog(DigitalClockUI.this, "Stopwatch stopped. Elapsed time: " + formatElapsedTime(elapsedTime));
    }

    private String formatElapsedTime(long elapsedTime) {
        long hours = elapsedTime / 3600000;
        long minutes = (elapsedTime % 3600000) / 60000;
        long seconds = (elapsedTime % 60000) / 1000;
        long milliseconds = elapsedTime % 1000;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }

    public void setBackgroundColor(Color color) {
        getContentPane().setBackground(color);
    }

    private void setLogo() {
        // Set icon logo
        String logoPath = "C:\\Users\\chetan bhandari\\OneDrive\\Desktop\\Mira Advanced Engineering\\clock.png";
        ImageIcon logoIcon = new ImageIcon(logoPath);
        setIconImage(logoIcon.getImage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitalClockUI clockUI = new DigitalClockUI();
            clockUI.setVisible(true);
            clockUI.setBackgroundColor(Color.YELLOW); // Set the background color here
        });
    }
}

class CountdownTimer extends Timer {
    private int remainingTime;

    public CountdownTimer(int countdownTime, ActionListener listener) {
        super(1000, listener);
        this.remainingTime = countdownTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
}
