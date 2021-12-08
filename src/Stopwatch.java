import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("start");
    JButton resetButton = new JButton("reset");
    JLabel timeLabel = new JLabel();
    // stores milliseconds
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    String font = "Verdana";

    Timer timer = new Timer(1000, new ActionListener() {
        // what the timer will do every second (==1000 milliseconds stated above)
        @Override
        public void actionPerformed(ActionEvent e) {

            elapsedTime+=1000;
            hours = (elapsedTime/3_600_000);
            minutes = (elapsedTime/60_000) % 60;
            seconds = (elapsedTime/1000) % 60;

            // updating the display in strings:
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);

            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

    Stopwatch() {
        // TODO later add Graphics and change the color of the stopwatch

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font(font, Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("Freestyle Script", Font.PLAIN, 40));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("Freestyle Script", Font.PLAIN, 40));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // to make the window appear in the middle
        frame.setTitle("Stopwatch");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==startButton) {
            start();
            if(started==false) {
                started=true;
                startButton.setText("stop");
                start();
            } else {
                started=false;
                startButton.setText("start");
                stop();
            }
        }

        if(e.getSource()==resetButton) {
            started=false;
            startButton.setText("start");
            reset();
        }
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {

        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        hours = 0;

        // updating the data visually
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }
}
