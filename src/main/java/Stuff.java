import javax.xml.transform.sax.SAXSource;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Stuff {
    public static void main(String[] args) {

//        Расстояние
        double a = 6.05*Math.sqrt(3)/2;
        double b = Math.sqrt(3*(5.26*5.26 + 2.96*2.96))/2;
        double c = (a+b)/2;
//        double r = 10.08*c/d;
//        double d = 81/c;
//        double f = 21.622/Math.tan(1.05/1.99);
//        double g = 30./640;
//        double angle = 21*g;
//        double z = 21/Math.tan(angle*0.017);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

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