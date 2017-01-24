import javax.swing.*;
import java.util.*;

/**
 * Created by nazar on 20.01.17.
 */
class TimerLabel extends JLabel {
    java.util.Timer timer = new java.util.Timer();

    TimerLabel() {
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    TimerTask timerTask = new TimerTask() {
        public volatile int time;
        Runnable refresher = new Runnable() {
            public void run() {
                TimerLabel.this.setText(String.format("%02d:%02d", time / 60, time % 60));
            }
        };

        public void run() {
            time++;
            SwingUtilities.invokeLater(refresher);
        }
    };

    void stopTimer() {
        timer.cancel();
    }
}
