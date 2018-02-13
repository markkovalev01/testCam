import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProc {
    public static void main(String[] args) throws IOException {
        BufferedImage bi = ImageIO.read(new File("result_2.png"));
//        System.out.println("RGB 0 0 : "+ bi.getRGB(0, 0));
//        System.out.println("RGB 286 467 : "+ bi.getRGB(286, 467));
//        System.out.println("RGB 0 100 : "+ bi.getRGB(0, 100));
        File file = new File("res_proc3.png");
        if (!file.exists()) {
            file.createNewFile();
        }


        int argbPrev = bi.getRGB(0, 0);
        int alphaPrev = (argbPrev >> 24) & 0xff;
        int redPrev = (argbPrev >> 16) & 0xff;
        int greenPrev = (argbPrev >>  8) & 0xff;
        int bluePrev = (argbPrev ) & 0xff;
        int sumRgbPrev = redPrev + greenPrev + bluePrev;

//        BufferedImage bi1 = new BufferedImage(file);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {

                int argb = bi.getRGB(i,j);
                int alpha = (argb >> 24) & 0xff;
                int red = (argb >> 16) & 0xff;
                int green = (argb >>  8) & 0xff;
                int blue = (argb ) & 0xff;
                int sumRgb = red + green + blue;
                if(i==258 && j == 268){
                    System.out.println(sumRgb + "  " + sumRgbPrev);
                }





                if(i != 0 && Math.abs(bi.getRGB(i, j)) - Math.abs(bi.getRGB(i - 1, j)) > 4000){
//                if (Math.abs(sumRgb - sumRgbPrev) > 20) {
//                if (i != 0 && (Math.abs(bi.getRGB(i, j)) - Math.abs(bi.getRGB(i - 1, j))) > 100) {
                    bi.setRGB(i, j, 0x008000);
                    sumRgbPrev = sumRgb;
                    continue;
                }

                if(i != 0 && Math.abs(bi.getRGB(i, j)) - Math.abs(bi.getRGB(i - 1, j)) > 4){
//                if (Math.abs(sumRgb - sumRgbPrev) > 9){
                    bi.setRGB(i, j, 0xff0000);
                    sumRgbPrev = sumRgb;
                    continue;
                }

                sumRgbPrev = sumRgb;


            }
        }
        System.out.println( "red " + bi.getRGB(20, 29));
        ImageIO.write(bi, "png", file);
//        bi = ImageIO.read(file);
        ImageProc imageProc = new ImageProc();
        int i = 0x008000;
        System.out.println(i);
//        imageProc.green_Proc();
        imageProc.green_red_Proc();

    }

    public void green_Proc() throws IOException {
        BufferedImage bi = ImageIO.read(new File("res_proc3.png"));
        System.out.println(bi.getRGB(40, 407));
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                if (bi.getRGB(i, j) != -16744448) {
                    bi.setRGB(i, j, 0xffffff);
//                    System.out.println("yes");
                }
//                System.out.println("no");
            }
        }
        File file = new File("red_proc.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);

    }

    public void green_red_Proc() throws IOException {
        BufferedImage bi = ImageIO.read(new File("res_proc3.png"));
        System.out.println(bi.getRGB(40, 407));
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                if (bi.getRGB(i, j) != -16744448 && bi.getRGB(i, j) != -65536) {
                    bi.setRGB(i, j, 0xffffff);
//                    System.out.println("yes");
                }
//                System.out.println("no");
            }
        }
        File file = new File("green_red_proc.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "png", file);

    }
}
