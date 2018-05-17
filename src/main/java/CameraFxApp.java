import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class CameraFxApp extends Application {

    static {
        Webcam.setDriver(new IpCamDriver());
        try {
            IpCamDeviceRegistry.register(new IpCamDevice("Right", "http://192.168.202.115:800/", IpCamMode.PUSH));
            IpCamDeviceRegistry.register(new IpCamDevice("Left", "http://192.168.202.115:801/", IpCamMode.PUSH));
//            IpCamDeviceRegistry.register(new IpCamDevice("Left", "https://www.earthcam.com/ts/?cam=tsrobo3", IpCamMode.PUSH));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private BorderPane root;
    private Webcam webcam = null;
    private BorderPane webCamPane;
    private ImageView imgWebCamCapturedImage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new BorderPane();
        webCamPane = new BorderPane();
        imgWebCamCapturedImage = new ImageView();
        webCamPane.setCenter(imgWebCamCapturedImage);
        root.setCenter(webCamPane);


        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(900);
        primaryStage.setWidth(1600);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
