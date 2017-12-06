import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test {

    static {
        Webcam.setDriver(new IpCamDriver());
    }

    public static void main(String[] args) throws Exception {
        IpCamDeviceRegistry.register(new IpCamDevice("Lignano", "http://192.168.202.115:800/", IpCamMode.PUSH));
//        IpCamDeviceRegistry.register(new IpCamDevice("Lignano1", "http://192.168.202.115:801/", IpCamMode.PUSH));

        Frame frame = new Frame(Webcam.getDefault());
        BufferedImage bi = Webcam.getDefault().getImage();
        System.out.println(bi);
        File file = new File("right1.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(bi, "PNG", file);

    }
}
