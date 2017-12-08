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
//        g2d.s
//        for (int i = 0; i <= 100000; i++) {
//            Dimension size = getSize();
//            int w = size.width ;
//            int h = size.height;
//
//            Random r = new Random();
//            int x = Math.abs(r.nextInt()) % w;
//            int y = Math.abs(r.nextInt()) % h;
        for (int i = 0; i < arr.size(); i++) {
            Point2D.Double p = new Point2D.Double(0., 0.);
//            p.setLocation((double) 14 + i, (double) 20 + i);
            double[] point = arr.get(i);
//            Ellipse2D ellipse2D = new Ellipse2D.Double(point[0], point[1], 10, 10);
            g2d.fillOval((int)point[0], (int)point[1], 10, 10);
//            g2d.draw(ellipse2D);
        }
    }

}
