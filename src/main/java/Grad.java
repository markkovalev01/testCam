import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grad {
    BufferedImage bi;

    Grad(BufferedImage bi) {
        this.bi = bi;
    }

    public void gradGray() throws IOException {
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                int argb = bi.getRGB(j, i);
                int alpha = (argb >> 24) & 0xff;
                int red = (argb >> 16) & 0xff;
                int green = (argb >> 8) & 0xff;
                int blue = (argb) & 0xff;
//                int grayRGB = (red * 77 + green * 150 + blue * 29 + 128) / 256;
                green = blue = red;
                bi.setRGB(j, i, (red << 16) | (green << 8) | blue);
            }
        }
        File file = new File("gray_image5.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);
    }

    public void gradientMap() throws IOException {
        BufferedImage biN = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        System.out.println(bi.getHeight() + " " + bi.getWidth());
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                setDirection(i, j, biN);
            }
        }

//        setDirection(61, 172);

        File file = new File("grad_map_image5.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(biN, "png", file);
        bi = biN;
    }

    private void setDirection(int x, int y, BufferedImage biN) {
//        int argb1 =
        boolean flag = false;
        if ((y - 1) >= 0 && (bi.getRGB(y - 1, x) & 0xff) == 90) {
            flag = true;
        }
        int grad = bi.getRGB(y, x) & 0xff;
////        System.out.println(grad);
////        System.out.println();
        int buffI = 0;
        int buffJ = 0;

        for (int i = x - 1; i < x + 1; i++) {
            for (int j = y - 1; j < y + 1; j++) {
                if (i > bi.getHeight() || j > bi.getWidth()) {
                    continue;
                }
                if (i < 0 || j < 0) {
                    continue;
                }
                if (i == x && j == y) {
                    continue;
                }
                if (j != y + 1 && flag) {
                    continue;
                }
                if ((bi.getRGB(j, i) & 0xff) < grad) {
                    buffI = i;
                    buffJ = j;
                    grad = bi.getRGB(j, i) & 0xff;
                }
            }
        }


//        for (int d = i + 1; d > 0 && d > i - 2; d--) {
//
//            if (j + 1 >= bi.getWidth() && i + 1 >= bi.getHeight()) {
//                d--;
//                for (int k = j; k > j - 2 && k > 0; k--) {
//                    int argb = bi.getRGB(k, d);
//
////            int alpha = (argb >> 24) & 0xff;
//                    int red = (argb >> 16) & 0xff;
////                    System.out.println();
////            int green = (argb >> 8) & 0xff;
////            int blue = (argb) & 0xff;
//                    if (red < grad) {
////                        System.out.println(red + " " + grad + " " + i + " " + j);
//                        grad = red;
//                        buffI = d;
//                        buffJ = k;
//                    }
//                }
//                continue;
//            }
//
//
//            if (j + 1 >= bi.getWidth()) {
//                for (int k = j; k > j - 2 && k > 0; k--) {
//                    int argb = bi.getRGB(k, d);
////            int alpha = (argb >> 24) & 0xff;
//                    int red = (argb >> 16) & 0xff;
////            int green = (argb >> 8) & 0xff;
////            int blue = (argb) & 0xff;
//                    if (red < grad) {
////                        System.out.println(red + " " + grad + " " + i + " " + j);
//                        grad = red;
//                        buffI = d;
//                        buffJ = k;
//                    }
//                }
//                continue;
//            }
//
//
//            if (i + 1 >= bi.getHeight()) {
//                d--;
//                for (int k = j + 1; k > j - 2 && k > 0; k--) {
//                    int argb = bi.getRGB(k, d);
////            int alpha = (argb >> 24) & 0xff;
//                    int red = (argb >> 16) & 0xff;
////            int green = (argb >> 8) & 0xff;
////            int blue = (argb) & 0xff;
//                    if (red < grad) {
////                        System.out.println(red + " " + grad + " " + i + " " + j);
//                        grad = red;
//                        buffI = d;
//                        buffJ = k;
//                    }
//                }
//                continue;
//            }
//
//            for (int k = j + 1; k > j - 2 && k > 0; k--) {
//                int argb = bi.getRGB(k, d);
//                if (k == (j - 1) && d==i && flag) {
//                    System.out.println("yes");
//                    continue;
//                }
////            int alpha = (argb >> 24) & 0xff;
//                int red = (argb >> 16) & 0xff;
////            int green = (argb >> 8) & 0xff;
////            int blue = (argb) & 0xff;
////                System.out.println(red + " " + grad + " " + i + " " + j);
//                if (red < grad) {
////                    System.out.println(red + " " + grad);
//
//                    grad = red;
//                    buffI = d;
//                    buffJ = k;
//                }
//            }
//        }
//        int a = (argb1 >> 16) & 0xff;

//        if (x == 61 && y == 172) {
////            System.out.println(buffI + " " + buffJ);
//        }

        if (buffI == x && buffJ == y) {
            biN.setRGB(y, x, new Color(255, 255, 255).getRGB());
//            bi.setRGB(j, i, 0xffffff);
//            System.out.println("--");
            return;
        }

        if (buffI == x + 1 && buffJ == y + 1) {
            biN.setRGB(y, x, new Color(0, 0, 135).getRGB());
//            System.out.println(135);
            return;
        }
        if (buffI == x && buffJ == y + 1) {
            biN.setRGB(y, x, new Color(0, 0, 90).getRGB());
//            bi.setRGB(j, i, -16776090);
//            System.out.println(90);
            return;
        }
        if (buffI == x - 1 && buffJ == y + 1) {
            biN.setRGB(y, x, new Color(0, 0, 45).getRGB());
//            bi.setRGB(j, i, -16776045);
//            System.out.println(45);
            return;
        }
        if (buffI == x - 1 && buffJ == y) {
            biN.setRGB(y, x, new Color(0, 0, 0).getRGB());
//            bi.setRGB(j, i, -16776000);
//            System.out.println(0);
            return;
        }
        if (buffI == x - 1 && buffJ == y - 1) {
            biN.setRGB(y, x, new Color(0, 60, 255).getRGB());
//            bi.setRGB(j, i, -16776315);
//            System.out.println(315);
            return;
        }
        if (buffI == x && buffJ == y - 1) {
            biN.setRGB(y, x, new Color(0, 15, 255).getRGB());
//            bi.setRGB(j, i, -16776270);
//            System.out.println(270);
            return;
        }
        if (buffI == x + 1 && buffJ == y - 1) {
            biN.setRGB(y, x, new Color(0, 0, 225).getRGB());
//            bi.setRGB(j, i, -16776225);
//            System.out.println(225);
            return;
        }
        if (buffI == x + 1 && buffJ == y) {
            biN.setRGB(y, x, new Color(0, 0, 180).getRGB());
//            bi.setRGB(j, i, -16776180);
//            System.out.println(180);
            return;
        }

    }


    public void generalMap() throws IOException {
        for (int i = 0; i < bi.getHeight(); i += 16) {
            for (int j = 0; j < bi.getWidth(); j += 16) {
                mainDirection(i, j);
            }
        }
        File file = new File("general_map_image4.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);
    }

    private void mainDirection(int i, int j) {
        int[] direct = new int[9];

        for (int k = i; k < i + 16; k++) {
            for (int c = j; c < j + 16; c++) {
                int argb = bi.getRGB(c, k);
                int alpha = (argb >> 24) & 0xff;
                int red = (argb >> 16) & 0xff;
                int green = (argb >> 8) & 0xff;
                int blue = (argb) & 0xff;
                int rgb = red + green + blue;
//                System.out.println(rgb);
                if (rgb == 765) {
                    direct[0]++;
                }
                if (rgb == 0) {
                    direct[1]++;
                }
                if (rgb == 45) {
                    direct[2]++;
                }
                if (rgb == 90) {
                    direct[3]++;
                }
                if (rgb == 135) {
                    direct[4]++;
                }
                if (rgb == 180) {
                    direct[5]++;
                }
                if (rgb == 225) {
                    direct[6]++;
                }
                if (rgb == 270) {
                    direct[7]++;
                }
                if (rgb == 315) {
                    direct[8]++;
                }
            }
        }

        int max = 0;
        int kk = 0;
        for (int k = 0; k < direct.length; k++) {
//            System.out.println(direct[k]);
            if (direct[k] > max) {
                max = direct[k];
                kk = k;
            }
        }

        for (int k = i; k < i + 16; k++) {
            for (int c = j; c < j + 16; c++) {
//                System.out.println(max);
//                if (max == 765) {
//                    bi.setRGB(c, k, new Color(255, 255, 255).getRGB());
//                    continue;
//                }
//                if (max > 255) {
//                    bi.setRGB(c, k, new Color(0, max - 255, 255).getRGB());
//                    continue;
//                }
//                bi.setRGB(c, k, new Color(0, 0, max).getRGB());

                if (kk == 0) {
                    bi.setRGB(c, k, new Color(255, 255, 255).getRGB());
                }
                if (kk == 1) {
                    bi.setRGB(c, k, new Color(0, 0, 0).getRGB());
                }
                if (kk == 2) {
                    bi.setRGB(c, k, new Color(0, 0, 45).getRGB());
                }
                if (kk == 3) {
                    bi.setRGB(c, k, new Color(0, 0, 90).getRGB());
                }
                if (kk == 4) {
                    bi.setRGB(c, k, new Color(0, 0, 135).getRGB());
                }
                if (kk == 5) {
                    bi.setRGB(c, k, new Color(0, 0, 180).getRGB());
                }
                if (kk == 6) {
                    bi.setRGB(c, k, new Color(0, 0, 225).getRGB());
                }
                if (kk == 7) {
                    bi.setRGB(c, k, new Color(0, 15, 255).getRGB());
                }
                if (kk == 8) {
                    bi.setRGB(c, k, new Color(0, 60, 255).getRGB());
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Start");
        BufferedImage bi = ImageIO.read(new File("6_left.png"));
        Grad grad = new Grad(bi);
        grad.gradGray();
        grad.gradientMap();
        grad.generalMap();
        System.out.println(System.currentTimeMillis() - startTime + " End");
//        BufferedImage bi1 = ImageIO.read(new File("grad_map_image1.png"));
//        for (int i = 0; i < bi1.getHeight(); i++) {
//            for (int j = 0; j < bi1.getWidth(); j++) {
//                int argb = bi.getRGB(j, i);
//                int alpha = (argb >> 24) & 0xff;
//                int red = (argb >> 16) & 0xff;
//                int green = (argb >> 8) & 0xff;
//                int blue = (argb) & 0xff;
//                if (red + green + blue == 270) {
//                    bi1.setRGB(j, i, new Color(255, 0, 0).getRGB());
//                    continue;
//                }
////                if (bi1.getRGB(j, i) % 1000 == -135) {
////                    bi1.setRGB(j, i, 0x000000);
////                    continue;
////                }
////                if (bi1.getRGB(j, i) % 1000 == -90) {
////                    bi1.setRGB(j, i, 0xff0000);
////                    continue;
////                }
////                if (bi1.getRGB(j, i) % 1000 == -45) {
////                    bi1.setRGB(j, i, 0xffff00);
////                    continue;
////                }
////                if (bi1.getRGB(j, i) % 1000 == 0) {
////                    bi1.setRGB(j, i, 0x008000);
////                    continue;
////                }
////                if (bi1.getRGB(j, i) % 1000 == 315) {
////                    bi1.setRGB(j, i, 0x8b00ff);
////                    continue;
////                }
////                if (bi1.getRGB(j, i) % 1000 == 225) {
////                    bi1.setRGB(j, i, 0xffa500);
////                    continue;
////                }
////                if (bi1.getRGB(j, i) % 1000 == 180) {
////                    bi1.setRGB(j, i, 0x42aaff);
////                    continue;
////                }
////                int a = bi.getRGB(j, i);
////                    System.out.println(bi1.getRGB(j, i) % 1000 + "  ");
//            }
////            System.out.println();
//        }
//
//        File file = new File("grad_line1.png");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        ImageIO.write(bi1, "png", file);
//
    }
}
