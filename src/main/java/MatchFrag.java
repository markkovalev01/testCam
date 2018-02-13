import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MatchFrag {
    int[][] frag;
    int match = 0;
    int x;
    int y;

    MatchFrag(File file) throws IOException {
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
    }


    public void findMatch(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        int buffMatch = 0;
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                compareFrag(j, i, bi);
            }
        }
    }

    private void compareFrag(int y, int x, BufferedImage bi) {
        int buffMatch = 0;
        int width = frag[0].length;
        int height = frag.length;
        if ((x + frag.length) > bi.getHeight()) {
            height = x + frag.length - bi.getHeight();
        }
        if ((y + frag[0].length) > bi.getWidth()) {
            height = y + frag[0].length - bi.getWidth();
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (frag[i][j] == bi.getRGB(j + y, i + x)) {
                    buffMatch++;
                }
            }
        }
        if(buffMatch>match){
            match=buffMatch;
            this.x=x;
            this.y=y;
        }
    }


    public static void main(String[] args) throws IOException {
        MatchFrag mf = new MatchFrag(new File("frag.png"));
        mf.findMatch(new File("frag.png"));

    }

}
