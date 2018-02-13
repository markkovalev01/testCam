import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Points extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);

        for(int i = 0; i < 100; i++){
            Point2D.Double p = new Point2D.Double(0., 0.);
            p.setLocation((double) 14 + i, (double) 20 + i);
            g2d.draw(new Ellipse2D.Double(14 + i, 20 + i, 4,4));
        }
        }


    public static void main(String[] args) {
        Points points = new Points();
        JFrame frame = new JFrame("Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(points);
        frame.setSize(250, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}