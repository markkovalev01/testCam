import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Point extends WebcamPanel {
    double x = 0.;
    double y = 0.;
    ArrayList<double[]> arr = new ArrayList<double[]>();
    int xPrev = 0;
    int yPrev = 0;

    public Point(Webcam webcam) {
        super(webcam);
        setSize(1280, 720);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addP(double[] xy) {
        arr.add(xy);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        g2d.setBackground(Color.red);
        g2d.drawLine(0, this.getHeight()/2, getWidth(), getHeight()/2);
        g2d.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
        for (int i = 0; i < arr.size(); i++) {
            Point2D.Double p = new Point2D.Double(0., 0.);
            double[] point = arr.get(i);
            g2d.fillOval((int)point[0], (int)point[1], 10, 10);
        }
    }

}
