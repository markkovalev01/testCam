import javax.imageio.ImageIO;
import javax.xml.transform.sax.SAXSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stuff {
    public static void main(String[] args) throws IOException {
        BufferedImage bi = ImageIO.read(new File("1184.png"));
        Grad grad = new Grad(bi);
        grad.gradGray();
        grad.contrastMap(8, 5);
//        grad.gradientMap();
//        grad.binarization();
////        BufferedImage bi1 = grad.generalMapImage();
//        File file = new File("ress3.png");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
////        System.out.println(bi.getRGB(0,0));
        ImageIO.write(grad.bi, "png", new File("result.png"));
//        Frag frag = new Frag();
////        File fr = new File("frag18.png");
////        if (!fr.exists()) {
////            fr.createNewFile();
////        }
////        frag.makeFrag(ImageIO.read(file), 450, 189, 26, 16, new File("frag18.png"));
//        MatchFrag mf = new MatchFrag(new File("frag18.png"));
////        BufferedImage bi = ImageIO.read(new File("ress2.png"));
//        File file1 = new File("matchRess.png");
//        if (!file1.exists()) {
//            file1.createNewFile();
//        }
//        mf.findMatchHOG(file);
//
//        bi = ImageIO.read(new File("0001.png"));
//        BufferedImage bi1 = ImageIO.read(new File("findFragHOG2.png"));
//        int color = new Color(250, 250, 0).getRGB();
//
//        for (int i = 0; i < bi.getHeight(); i++) {
//            for (int j = 0; j < bi.getWidth(); j++) {
//                if (bi1.getRGB(j, i) == color) {
//                    bi.setRGB(j, i, color);
//                }
//            }
//        }
//
//        File res = new File("matchRess.png");
//        ImageIO.write(bi, "png", res);

//        int x = 0;
//        int y = 0;
//        int m = 0;
//        int buffM = 0;
//        int buffX = 0;
//        int buffY = 0;
//        boolean flag = false;
//        Color color = new Color(255, 255, 255);
//        for (int i = 0; i < bi.getHeight(); i++) {
//            for (int j = 0; j < bi.getWidth(); j++) {
//                if (bi.getRGB(j, i) == -65536) {
//                    continue;
//                }
//                if (bi.getRGB(j, i) != color.getRGB() && flag) {
//                    flag = false;
//                    if (buffM > m) {
//                        m = buffM;
//                        buffM = 0;
//                        x=buffX;
//                        y=buffY;
//                    }
//                }
//                if (flag) {
//                    buffM++;
//                }
//                if (bi.getRGB(j, i) == color.getRGB() && bi.getRGB(j, i ) != color.getRGB()) {
//                    System.out.println("yes");
//                    flag = true;
//                    buffX = i;
//                    buffY = j;
//                    buffM++;
//                    continue;
//                }
//
//            }
//        }
//        bi.setRGB(y, x, new Color(250, 250, 0).getRGB());
//ImageIO.write(bi,"png",new File("ress2.png"));
//        int i = 1;
//        System.out.println(1|1);
//        Расстояние
//        double a = 6.05*Math.sqrt(3)/2;
//        double b = Math.sqrt(3*(5.26*5.26 + 2.96*2.96))/2;
//        double c = (a+b)/2;
////        double r = 10.08*c/d;
////        double d = 81/c;
////        double f = 21.622/Math.tan(1.05/1.99);
////        double g = 30./640;
////        double angle = 21*g;
////        double z = 21/Math.tan(angle*0.017);
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);


//
//Scanner in = new Scanner(System.in);
//int n = in.nextInt();
//        ArrayList
//

//        String workingDir = System.getProperty("user.dir");
//        System.out.println("Current working directory : " + workingDir);

//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(f);
//        System.out.println(g);
//        System.out.println(z);
//        System.out.println(a*10.8/9);


//        int a = -16744448;
//        int alpha = (a >> 24) & 0xff;
//        System.out.println(a);
//        int red = (a >> 16) & 0xff;
//        System.out.println(a);
//        int green = (a >> 8) & 0xff;
//        System.out.println(a);
//        int blue = (a) & 0xff;
//        System.out.println(a);
//        System.out.println(alpha + " " + red + " " + " " + green + " " + blue);


    }
}
//25
//0.7570093457943925
//1216.9198192230033


//          if (buffI == x && buffJ == y) {
//          biN.setRGB(y, x, new Color(255, 255, 255).getRGB());
//          return;
//          }
//          if (buffI == x + 1 && buffJ == y + 1) {
//          biN.setRGB(y, x, new Color(0, 0, 135).getRGB());
//          return;
//          }
//          if (buffI == x && buffJ == y + 1) {
//          biN.setRGB(y, x, new Color(0, 0, 90).getRGB());
//          return;
//          }
//          if (buffI == x - 1 && buffJ == y + 1) {
//          biN.setRGB(y, x, new Color(0, 0, 45).getRGB());
//          return;
//          }
//          if (buffI == x - 1 && buffJ == y) {
//          biN.setRGB(y, x, new Color(0, 0, 0).getRGB());
//          return;
//          }
//          if (buffI == x - 1 && buffJ == y - 1) {
//          biN.setRGB(y, x, new Color(0, 60, 255).getRGB());
//          return;
//          }
//          if (buffI == x && buffJ == y - 1) {
//          biN.setRGB(y, x, new Color(0, 15, 255).getRGB());
//          return;
//          }
//          if (buffI == x + 1 && buffJ == y - 1) {
//          biN.setRGB(y, x, new Color(0, 0, 225).getRGB());
//          return;
//          }
//          if (buffI == x + 1 && buffJ == y) {
//          biN.setRGB(y, x, new Color(0, 0, 180).getRGB());
//          return;
//          }
//          }


//    public BufferedImage gradGray(BufferedImage bi) throws IOException {
//        BufferedImage biN = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        for (int i = 0; i < bi.getHeight(); i++) {
//            for (int j = 0; j < bi.getWidth(); j++) {
//                int argb = bi.getRGB(j, i);
//                int alpha = (argb >> 24) & 0xff;
//                int red = (argb >> 16) & 0xff;
//                int green = (argb >> 8) & 0xff;
//                int blue = (argb) & 0xff;
//                green = blue = red;
//                biN.setRGB(j, i, (red << 16) | (green << 8) | blue);
//            }
//        }
//        return biN;
//    }


//    public BufferedImage findMatchHOG(BufferedImage bi) throws IOException {
//        int buffMatch = 0;
//        System.out.println("frag " + frag[0].length + " " + frag.length);
//        for (int i = bi.getHeight() % 16; i < bi.getHeight(); i += 16) {
//            for (int j = bi.getWidth() % 16; j < bi.getWidth(); j += 16) {
//                compareFragHOG(i, j, bi);
//            }
//        }
//        int buff = 0;
//        int buffI = 0;
//        int buffJ = 0;
//        for (int k = 0; k < match.size(); k++) {
//
//            if (match.get(k) >= buff) {
//                buff = match.get(k);
//                buffI = xy.get(k)[1];
//                buffJ = xy.get(k)[0];
//            }
//        }
//        prefX = buffI;
//        prefY = buffJ;
//        return bi;
//    }
//    private void compareFragHOG(int x, int y, BufferedImage bi) {
//        int width = frag[0].length;
//        int height = frag.length;
//        int buffMatch = 0;
//        for (int i = 0; i < height; i += 16) {
//            for (int j = 0; j < width; j += 16) {
//                if (y + j < bi.getWidth() && i + x < bi.getHeight() && bi.getRGB(j + y, i + x) == frag[i][j]) {
//                    buffMatch++;
//                }
//            }
//        }
//        match.add(buffMatch);
//        xy.add(new Integer[]{x, y});
//    }