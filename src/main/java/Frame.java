import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame {

    WebcamPanel webcamPanel;
    FileWriter fw;

    ArrayList<int[]> arr = new ArrayList<int[]>();
    int xPrev = 0;
    int yPrev = 0;

    public Frame(Webcam webcam) throws HeadlessException, IOException {
        super("frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(800, 600);
        File file = new File("xy.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);

        setVisible(true);
//        add(new WebcamPanel().add(new MouseListener() {
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            public void mouseExited(MouseEvent e) {
//
//            }
//        }));

        webcamPanel = new WebcamPanel(webcam);
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
                    fw.write("x: " + getX() + " y: " + getY() + "\n");
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

        add(webcamPanel);
        pack();
    }


    Frame() {
        super("Frame");
        System.out.println("Frame.Frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
//        setLayout(new BorderLayout());
        setBackground(Color.WHITE);



        JPanel panel = new JPanel();
        panel.setSize(800, 600);
        panel.setBackground(Color.WHITE);
        panel.addMouseListener(new MouseListener() {


            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                if (e.getX() != xPrev && e.getY() != yPrev) {
                    int[] coor = {e.getX(), e.getY()};
                    arr.add(coor);
                    System.out.printf("Press X = %d Y = %d \n", e.getX(), e.getY());
                }

            }

            public void mouseReleased(MouseEvent e) {
//                if (e.getX() != xPrev && e.getY() != yPrev) {
//                    int[] coor = {e.getX(), e.getY()};
//                    arr.add(coor);
//                    System.out.printf("X = %d Y = %d ", e.getX(), e.getY());
//                }
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
                    int[] coor = {e.getX(), e.getY()};
                    arr.add(coor);
                    System.out.printf(" X = %d Y = %d \n", e.getX(), e.getY());
                    Point2D.Double p = new Point2D.Double(0., 0.);
                    p.setLocation((double) e.getX(), (double) e.getY());
                    ;

                }



            }

            public void mouseMoved(MouseEvent e) {

            }
        });
        add(panel);
        pack();
        setVisible(true);
    }


//    public static void main(String[] args) {
//        new Frame();
//    }
}
