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
                int grayRGB = (red * 77 + green * 150 + blue * 29 + 128) / 256;
                green = blue = red;
                bi.setRGB(j, i, (red << 16) | (green << 8) | blue);
            }
        }
        File file = new File("gray_image2.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);
    }

    public void gradientMap() throws IOException {
        BufferedImage biN = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
        System.out.println(bi.getHeight() + " " + bi.getWidth());
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                setDirection(i, j, biN);
            }
        }

//        setDirection(61, 172);

        File file = new File("grad_map_image1.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(biN, "png", file);
    }

    private void setDirection(int i, int j, BufferedImage biN) {
        int argb1 = bi.getRGB(j, i);
        int grad = (argb1 >> 16) & 0xff;
//        System.out.println(grad);
//        System.out.println();
        int buffI = 0;
        int buffJ = 0;
        for (int d = i + 1; d > 0 && d > i - 2; d--) {

            if (j + 1 >= bi.getWidth() && i + 1 >= bi.getHeight()) {
                d--;
                for (int k = j; k > j - 2 && k > 0; k--) {
                    int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                    int red = (argb >> 16) & 0xff;
                    System.out.println();
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                    if (red < grad) {
//                        System.out.println(red + " " + grad + " " + i + " " + j);
                        grad = red;
                        buffI = d;
                        buffJ = k;
                    }
                }
                continue;
            }


            if (j + 1 >= bi.getWidth()) {
                for (int k = j; k > j - 2 && k > 0; k--) {
                    int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                    int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                    if (red < grad) {
//                        System.out.println(red + " " + grad + " " + i + " " + j);
                        grad = red;
                        buffI = d;
                        buffJ = k;
                    }
                }
                continue;
            }


            if (i + 1 >= bi.getHeight()) {
                d--;
                for (int k = j + 1; k > j - 2 && k > 0; k--) {
                    int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                    int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                    if (red < grad) {
//                        System.out.println(red + " " + grad + " " + i + " " + j);
                        grad = red;
                        buffI = d;
                        buffJ = k;
                    }
                }
                continue;
            }

            for (int k = j + 1; k > j - 2 && k > 0; k--) {
                int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
//                System.out.println(red + " " + grad + " " + i + " " + j);
                if (red < grad) {
//                    System.out.println(red + " " + grad);

                    grad = red;
                    buffI = d;
                    buffJ = k;
                }
            }
        }
        int a = (argb1 >> 16) & 0xff;

        if (i == 61 && j == 172) {
            System.out.println(buffI + " " + buffJ);
        }

        if (buffI == i && buffJ == j) {
            biN.setRGB(j, i, new Color(255, 255, 255).getRGB());
//            bi.setRGB(j, i, 0xffffff);
            System.out.println("--");
            return;
        }

        if (buffI == i + 1 && buffJ == j + 1) {
            biN.setRGB(j, i, new Color(0, 0, 135).getRGB());
            System.out.println(135);
            return;
        }
        if (buffI == i && buffJ == j + 1) {
            biN.setRGB(j, i, new Color(0, 0, 90).getRGB());
//            bi.setRGB(j, i, -16776090);
            System.out.println(90);
            return;
        }
        if (buffI == i - 1 && buffJ == j + 1) {
            biN.setRGB(j, i, new Color(0, 0, 45).getRGB());
//            bi.setRGB(j, i, -16776045);
            System.out.println(45);
            return;
        }
        if (buffI == i - 1 && buffJ == j) {
            biN.setRGB(j, i, new Color(0, 0, 0).getRGB());
//            bi.setRGB(j, i, -16776000);
            System.out.println(0);
            return;
        }
        if (buffI == i - 1 && buffJ == j - 1) {
            biN.setRGB(j, i, new Color(0, 60, 255).getRGB());
//            bi.setRGB(j, i, -16776315);
            System.out.println(315);
            return;
        }
        if (buffI == i && buffJ == j - 1) {
            biN.setRGB(j, i, new Color(0, 15, 255).getRGB());
//            bi.setRGB(j, i, -16776270);
            System.out.println(270);
            return;
        }
        if (buffI == i + 1 && buffJ == j - 1) {
            biN.setRGB(j, i, new Color(0, 0, 225).getRGB());
//            bi.setRGB(j, i, -16776225);
            System.out.println(225);
            return;
        }
        if (buffI == i + 1 && buffJ == j) {
            biN.setRGB(j, i, new Color(0, 0, 180).getRGB());
//            bi.setRGB(j, i, -16776180);
            System.out.println(180);
            return;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedImage bi = ImageIO.read(new File("1_Right.png"));
        Grad grad = new Grad(bi);
//        grad.gradGray();
        grad.gradientMap();
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
