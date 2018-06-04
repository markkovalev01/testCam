import java.awt.image.BufferedImage;

public class SVM {
    public int[] vectorize(BufferedImage bi){
        int n = (bi.getHeight()/16)*(bi.getWidth()/16);
        int[] arr = new int[n];
        int k = 0;
        for(int i=0; i< bi.getHeight();i+=16){
            for(int j=0; j<bi.getWidth();j+=16){
                arr[k]=bi.getRGB(j,i);
                k++;
            }
        }
        return arr;
    }
}
