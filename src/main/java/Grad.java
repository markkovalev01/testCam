import javax.imageio.ImageIO;
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
        File file = new File("gray_image.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);
    }

    public void gradientMap() throws IOException {
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                setDirection(i, j);

            }
        }

        File file = new File("grad_map_image.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);
    }

    private void setDirection(int i, int j) {
        int grad = 255;
        int buffI = 0;
        int buffJ = 0;
        for (int d = i + 1; d > 0 && d > i - 1; d--) {

            if (j + 1 >= bi.getWidth() && i + 1 >= bi.getHeight()) {
                d--;
                for (int k = j; k > 0; k--) {
                    int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                    int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                    if (red < grad) {
                        grad = red;
                        buffI = d;
                        buffJ = k;
                    }
                }
                continue;
            }


            if (j + 1 >= bi.getWidth()) {
                for (int k = j; k > 0; k--) {
                    int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                    int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                    if (red < grad) {
                        grad = red;
                        buffI = d;
                        buffJ = k;
                    }
                }
                continue;
            }


            if (i + 1 >= bi.getHeight()) {
                d--;
                for (int k = j + 1; k > 0; k--) {
                    int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                    int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                    if (red < grad) {
                        grad = red;
                        buffI = d;
                        buffJ = k;
                    }
                }
                continue;
            }

            for (int k = j + 1; k > 0; k--) {
                int argb = bi.getRGB(k, d);
//            int alpha = (argb >> 24) & 0xff;
                int red = (argb >> 16) & 0xff;
//            int green = (argb >> 8) & 0xff;
//            int blue = (argb) & 0xff;
                if (red < grad) {
                    grad = red;
                    buffI = d;
                    buffJ = k;
                }
            }
        }

        if (buffI == i + 1 && buffJ == j + 1) {
            bi.setRGB(j, i, -16776135);
//            System.out.println(135);
            return;
        }
        if (buffI == i && buffJ == j + 1) {
            bi.setRGB(j, i, -16776090);
//            System.out.println(90);
            return;
        }
        if (buffI == i - 1 && buffJ == j + 1) {
            bi.setRGB(j, i, -16776045);
//            System.out.println(45);
            return;
        }
        if (buffI == i - 1 && buffJ == j) {
            bi.setRGB(j, i, -16776000);
//            System.out.println(0);
            return;
        }
        if (buffI == i - 1 && buffJ == j - 1) {
            bi.setRGB(j, i, -16776315);
//            System.out.println(315);
            return;
        }
        if (buffI == i && buffJ == j - 1) {
            bi.setRGB(j, i, -16776270);
//            System.out.println(270);
            return;
        }
        if (buffI == i + 1 && buffJ == j - 1) {
            bi.setRGB(j, i, -16776225);
//            System.out.println(225);
            return;
        }
        if (buffI == i + 1 && buffJ == j) {
            bi.setRGB(j, i, -16776180);
//            System.out.println(180);
            return;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedImage bi = ImageIO.read(new File("1_left.png"));
        Grad grad = new Grad(bi);
        grad.gradGray();
        grad.gradientMap();
        BufferedImage bi1 = ImageIO.read(new File("grad_map_image.png"));
        for (int i = 0; i < bi1.getHeight(); i++) {
            for (int j = 0; j < bi1.getWidth(); j++) {
                if (bi1.getRGB(j, i) % 1000 == -270)
                    bi1.setRGB(j,i, -1);
//                int a = bi.getRGB(j, i);
                    System.out.println(bi1.getRGB(j, i) % 1000 + "  ");
            }
            System.out.println();
        }

//        File file = new File("grad_line.png");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        ImageIO.write(bi1, "png", file);

    }
}
