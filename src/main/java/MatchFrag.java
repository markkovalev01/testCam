import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MatchFrag {
    int[][] frag = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, 1, -1, -1, -1},
            {-1, 1, 1, 1, -1, -1},
            {1, 1, -1, 1, 1, -1},
            {1, -1, -1, -1, 1, -1},
            {1, -1, -1, -1, 1, -1}
    };
    ArrayList<Integer> match;
    ArrayList<Integer[]> xy;

    MatchFrag(File file) throws IOException {
        match = new ArrayList<Integer>();
        xy = new ArrayList<Integer[]>();
        BufferedImage bi = ImageIO.read(file);
        frag = new int[bi.getHeight()][bi.getWidth()];
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                frag[i][j] = bi.getRGB(j, i);
                if (frag[i][j] != -1) {
                    System.out.print(1 + " ");
                    continue;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println(bi.getHeight());
        System.out.println(bi.getWidth());
    }

    MatchFrag() throws IOException {
        match = new ArrayList<Integer>();
        xy = new ArrayList<Integer[]>();
//        frag =
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
//                frag[i][j] = bi.getRGB(j, i);
                if (frag[i][j] != -1) {
                    System.out.print(1 + " ");
                    continue;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
//        System.out.println(bi.getHeight());
//        System.out.println(bi.getWidth());
    }


    public void findMatch(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        int buffMatch = 0;
        System.out.println(bi.getWidth());
        System.out.println(bi.getHeight());
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
//                System.out.println(i + " " + j);
                compareFrag(j, i, bi);
            }
        }
        File res = new File("findFrag.png");
        if (!res.exists()) {
            file.createNewFile();
        }
        for (int k = 0; k < xy.size(); k++) {
//            System.out.println(xy.get(k)[0]+" "+ xy.get(k)[1]);
            bi.setRGB(xy.get(k)[1], xy.get(k)[0], new Color(250, 0, 0).getRGB());
        }
        ImageIO.write(bi, "png", res);
    }

    private void compareFrag(int y, int x, BufferedImage bi) {
        int buffMatch = 0;
        int width = frag[0].length;
        int height = frag.length;
        System.out.println(x + " " + y);
//        if ((x + frag.length) >= bi.getHeight()) {
//            height = y + frag.length - bi.getHeight();
////            System.out.println("yes");
//        }
////        System.out.println(height);
////        System.out.println();
//        if ((y + frag[0].length) >= bi.getWidth()) {
//            height = x + frag[0].length - bi.getWidth();
////            System.out.println("yes");
//        }
//        System.out.println(width);
//        System.out.println();


//        if (((x + frag[0].length) >= bi.getWidth() - 2)) {
////            System.out.println("Ret");
//            return;
//        }
//        if (((y + frag.length) >= bi.getHeight() - 2)) {
////            System.out.println("Ret");
//            return;
//        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
//                if ((j + x) < bi.getWidth() && (y + i) < bi.getHeight() && frag[i][j] == bi.getRGB(j + x, y + i)) {
//                    buffMatch++;
//                }
//                if ((y + j) < bi.getWidth() && (i + x) < bi.getHeight() && frag[i][j] != -1 && bi.getRGB(y + j, i + x) != -1) {
////                    (y + i) < bi.getWidth() && (j + x) < bi.getHeight() &&
////                    System.out.println(y+j);
//                    buffMatch++;
//                    continue;
//                }
                if ((y + j) < bi.getWidth() && (i + x) < bi.getHeight() && frag[i][j] == bi.getRGB(y + j, i + x)) {
//                     Math.abs(frag[i][j]) - Math.abs(bi.getRGB(y + j, i + x)) < -1000000
//                    frag[i][j] == bi.getRGB(y + j, i + x)
//                    (y + i) < bi.getWidth() && (j + x) < bi.getHeight() &&
//                    (y + i) < bi.getWidth() && (j + x) < bi.getHeight() &&
//                    System.out.println(y+j);
                    buffMatch++;
                }
            }
        }

//        System.out.println(buffMatch);

        if (buffMatch >= 320) {
            match.add(buffMatch);
            xy.add(new Integer[]{x, y});
//            System.out.println(match);
        }

    }

    public void findMatchHOG(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        int buffMatch = 0;
        for (int i = 0; i < bi.getHeight(); i += 16) {
            for (int j = 0; j < bi.getWidth(); j += 16) {

                compareFragHOG(i, j, bi);
            }
        }
        File res = new File("findFragHOG.png");
        if (!res.exists()) {
            file.createNewFile();
        }
        for (int k = 0; k < xy.size(); k++) {
//            System.out.println(xy.get(k)[0]+" "+ xy.get(k)[1]);
            bi.setRGB(xy.get(k)[1], xy.get(k)[0], new Color(250, 0, 0).getRGB());
        }
        ImageIO.write(bi, "png", res);

    }

    private void compareFragHOG(int x, int y, BufferedImage bi) {
        int width = frag[0].length;
        int height = frag.length;
        int buffMatch = 0;

        for (int i = 0; i < height; i += 15) {
            for (int j = 0; j < width; j += 15) {
                int argb = bi.getRGB(j, i);
                int alpha = (argb >> 24) & 0xff;
                int red = (argb >> 16) & 0xff;
                int green = (argb >> 8) & 0xff;
                int blue = (argb) & 0xff;
                if((red + green + blue)!=0){
                System.out.println("bi"+" "+red +" "+ green +" "+ blue + " " + alpha);}
////                System.out.println();
                int argb1 = frag[i][j];
                int alpha1 = (argb1 >> 24) & 0xff;
                int red1 = (argb1 >> 16) & 0xff;
                int green1 = (argb1 >> 8) & 0xff;
                int blue1 = (argb1) & 0xff;
//                System.out.println(red1 +" "+ green1 +" "+ blue1);
//                System.out.println();
//                (red + green + blue) == (red1 + green1 + blue1)
                if (y + j < bi.getWidth() && i + x < bi.getHeight() && (red + green + blue) == (red1 + green1 + blue1) && bi.getRGB(j, i) != new Color(0, 0, 0).getRGB() && bi.getRGB(j, i) != new Color(0, 0, 45).getRGB()) {
//                    System.out.println("yes");
                    buffMatch++;
                }
            }
        }
//        System.out.println(buffMatch);
        if (buffMatch >= 1) {
            match.add(buffMatch);
            xy.add(new Integer[]{x, y});
            System.out.println(match);
        }
    }


    public static void main(String[] args) throws IOException {
        MatchFrag mf = new MatchFrag(new File("frag13.png"));
//        MatchFrag mf = new MatchFrag();
//        mf.findMatch(new File("grad_map_image2.png"));
//        mf.findMatchHOG(new File("general_map_image1.png"));
        mf.findMatchHOG(new File("fragHOG.png"));
        System.out.println("end");
//        System.out.println(mf.x + " " + mf.y);

    }

}
