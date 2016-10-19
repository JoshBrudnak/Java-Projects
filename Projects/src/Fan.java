
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Fan extends JFrame implements ActionListener {

    JButton speedup;
    JButton slowdown;

    MyPanel testPanel = new MyPanel();// trying to inherit properties of MyPanel

    public Fan() {
        add(testPanel);

        JButton speedup = new JButton("Speed Up");
        speedup.addActionListener(this);

        JButton slowdown = new JButton("Slow Down");
        slowdown.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(speedup);
        panel.add(slowdown);

        add(panel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Spinning Fan");
        setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        int delay;
        String cmd = event.getActionCommand();
        if ("Speed Up".equals(cmd)) {
            delay = testPanel.getTimer().getDelay();
            delay--;
            testPanel.getTimer().setDelay(delay);
        } else {
            delay = testPanel.getTimer().getDelay();
            delay++;
            testPanel.getTimer().setDelay(delay);
        }
    }

    // My attempt at getting timer to work commented out

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new Fan();
            }
        });
    }
}

class MyPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public Timer timer = new Timer(10, new TimerListener());
    private int alpha = 0; // angle

    public Timer getTimer() {
        return timer; // getter method for timer
    }

    public MyPanel() {
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        alpha = alpha + 1;
        int xc = getWidth() / 2;
        int yc = getHeight() / 2;
        int rad = (int) (Math.min(getWidth(), getHeight()) * 0.4);
        int x = xc - rad;
        int y = yc - rad;
        g.fillArc(x, y, 2 * rad, 2 * rad, 0 + alpha, 30);
        g.fillArc(x, y, 2 * rad, 2 * rad, 90 + alpha, 30);
        g.fillArc(x, y, 2 * rad, 2 * rad, 180 + alpha, 30);
        g.fillArc(x, y, 2 * rad, 2 * rad, 270 + alpha, 30);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
}