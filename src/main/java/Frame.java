import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
//import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame {

    Point webcamPanel;
    FileWriter fw;
    File file;
    int i = 0;

    ArrayList<double[]> arr = new ArrayList<double[]>();
    int xPrev = 0;
    int yPrev = 0;

    public Frame(final Webcam webcam, final String name) throws HeadlessException, IOException {
        super(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(800, 600);
        file = new File(".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);

        setVisible(true);
        webcamPanel = new Point(webcam);
        webcamPanel.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
//                System.exit(0);
                new Thread(new Runnable() {
                    public void run() {
                        JFrame frame = new JFrame("x y");
                        frame.add(new JTextArea(e.getX() + "  " + e.getY() + "\n"));
                        frame.pack();
                        frame.setVisible(true);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }


            public void mousePressed(MouseEvent e) {
                try {
                    file = new File(i + "_" + name + ".png");
                    if (!file.exists()) {
                        file.createNewFile();

                    }
                    i++;
                    int n = 0;
                    if (name == "Left") {
                        n = 1;
                    }
                    BufferedImage bi = Webcam.getWebcams().get(n).getImage();
                    ImageIO.write(bi, "PNG", file);
                    n=0;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        webcamPanel.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if (e.getX() != xPrev && e.getY() != yPrev) {
                    double[] coor = {e.getX(), e.getY()};
                    webcamPanel.addP(coor);
                    System.out.printf(" X = %d Y = %d \n", e.getX(), e.getY());
                }
            }

            public void mouseMoved(MouseEvent e) {
            }

        });

        add(webcamPanel);
        pack();
    }


    Frame() {
        super("Frame");
        System.out.println("Frame.Frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setBackground(Color.WHITE);


        JPanel panel = new JPanel();
        panel.setSize(800, 600);
        panel.setBackground(Color.WHITE);
        panel.addMouseListener(new MouseListener() {


            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                if (e.getX() != xPrev && e.getY() != yPrev) {
                    System.out.printf("Press X = %d Y = %d \n", e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
//                System.out.printf(" ENTR X = %d Y = %d ", e.getX(), e.getY());
            }

            public void mouseExited(MouseEvent e) {
//                System.out.printf(" EX X = %d Y = %d \n", e.getX(), e.getY());
            }
        });
        panel.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if (e.getX() != xPrev && e.getY() != yPrev) {
                    System.out.printf(" X = %d Y = %d \n", e.getX(), e.getY());
                }
            }

            public void mouseMoved(MouseEvent e) {

            }
        });
        add(panel);
        pack();
        setVisible(true);
    }


}
