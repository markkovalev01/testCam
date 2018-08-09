package realease;

import java.awt.image.BufferedImage;

public class Grad {

    Grad() {
    }

    BufferedImage gradGray(BufferedImage source) {
        for (int i = 0; i < source.getHeight(); i++) {
            for (int j = 0; j < source.getWidth(); j++) {
                int argb = source.getRGB(j, i);
                int red = (argb >> 16) & 0xff;
                int green = (argb >> 8) & 0xff;
                int blue = (argb) & 0xff;
                green = blue = red;
                source.setRGB(j, i, (red << 16) | (green << 8) | blue);
            }
        }
        return source;
    }
}
