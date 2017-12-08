import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    static {
        Webcam.setDriver(new IpCamDriver());
    }

    public static void main(String[] args) throws Exception {
        IpCamDeviceRegistry.register(new IpCamDevice("Lignano", "http://192.168.202.115:800/", IpCamMode.PUSH));
//        IpCamDeviceRegistry.register(new IpCamDevice("Lignano1", "http://192.168.202.115:801/", IpCamMode.PUSH));

        Frame frame = new Frame(Webcam.getDefault());

//        new Thread(new Runnable() {
//            boolean work = true;
//            int i = 0;
//
//            public void run() {while (i<100){
//                BufferedImage bi = Webcam.getDefault().getImage();
//                System.out.println(bi);
//                File file = new File("D:\\examples\\right" + i + ".png");
//                if (!file.exists()) {
//                    try {
//                        file.createNewFile();
//                        ImageIO.write(bi, "PNG", file);
//                        i++;
//                        Thread.sleep(100);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            }
//        }).start();

    }
}
