import at.imagelibrary.ImageToolException;
import at.imagelibrary.StereoImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Stereo {
    public static void main(String[] args) throws IOException, ImageToolException {
        BufferedImage left = ImageIO.read(new File("2_Left.png"));
        BufferedImage right = ImageIO.read(new File("2_Right.png"));



        StereoImage stereoImage = new StereoImage(left, right);
        BufferedImage anaglyph = stereoImage.getResultImage(StereoImage.ANAGLYPH_YELLOW_BLUE_GRAY);
        File file = new File("result_2.png");
        if(!file.exists()){
            file.createNewFile();
        }
        ImageIO.write(anaglyph,"PNG", file);
    }
}
