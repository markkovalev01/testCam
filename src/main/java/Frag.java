import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frag {
//    public static void main(String[] args) throws IOException {
//        BufferedImage bi = ImageIO.read(new File("general_map_image2.png"));
////        BufferedImage bi = ImageIO.read(new File("general_map_image1.png"));
//        File file = new File("frag13.png");
//        Frag frag = new Frag();
//        frag.makeFrag(bi, 768, 399, 224, 112, file);
//        BufferedImage bi1 = ImageIO.read(new File("frag.png"));
////        frag.clear(bi1);
//    }


    public void makeFrag(BufferedImage bi, int x, int y, int width, int height, File out) throws IOException {
        int[][] arr = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (bi.getRGB(x + i, y + j) == -16744448) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(" " + " ");
                }
                arr[i][j] = bi.getRGB(x + i, y + j);
            }
            System.out.println();
        }

        BufferedImage bi1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bi1.setRGB(i, j, arr[i][j]);
            }
        }

//        File file = new File("frag13.png");
        if (!out.exists()) {
            out.createNewFile();
        }
        ImageIO.write(bi1, "png", out);
    }

    public BufferedImage makeFrag(BufferedImage bi, int x, int y, int width, int height) throws IOException {
        int[][] arr = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
//                if (bi.getRGB(x + i, y + j) == -16744448) {
//                    System.out.print(1 + " ");
//                } else {
//                    System.out.print(" " + " ");
//                }
                arr[i][j] = bi.getRGB(x + i, y + j);
            }
//            System.out.println();
        }

        BufferedImage bi1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bi1.setRGB(i, j, arr[i][j]);
//                System.out.println(bi1.getRGB(i,j) + "    ");
            }
//            System.out.println();
        }

        return  bi1;

//        File file = new File("frag13.png");
//        if (!out.exists()) {
//            out.createNewFile();
//        }
//        ImageIO.write(bi1, "png", out);
    }


    public void clear(BufferedImage bi) throws IOException {
        bi.setRGB(0, 1, -65536);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                if (j < (bi.getHeight() - 2) && (bi.getRGB(i, j + 1) == -16744448 || (bi.getRGB(i, j + 2) == -16744448))) {
                    System.out.println("yes");
                    bi.setRGB(i, j, -1);
                }
            }
        }
        File file = new File("clear_frag.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);
    }
}
