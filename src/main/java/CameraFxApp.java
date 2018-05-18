import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import com.sun.prism.Graphics;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicReference;

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
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image(new File("10_right.png").toURI().toURL().toString());

        root = new BorderPane();
        webCamPane = new BorderPane();
//        imgWebCamCapturedImage = new ImageView();
        imgWebCamCapturedImage = new ImageView(image);
        webCamPane.getChildren().add(imgWebCamCapturedImage);
        root.setCenter(webCamPane);

        EventHandler handler = new EventHandler() {
            Rectangle rect;
            double prevX = 0;
            double prevY = 0;

            @Override
            public void handle(Event event) {


                MouseEvent eventM = (MouseEvent) event;
//                System.out.println(event);

//                    prevX = eventM.getX();
//                    prevY = eventM.getY();

                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("dragged");
                    if (rect != null) {
                        System.out.println(eventM.getX() + " " + prevX + " " + eventM.getY() + " " + prevY);
//                        double xSpeed=
                        rect.setHeight((int) (rect.getHeight() + (eventM.getY() - prevY)));
                        rect.setWidth((int) (rect.getWidth() + (eventM.getX() - prevX)));
                        prevX = eventM.getX();
                        prevY = eventM.getY();
                    }
                }
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("entered");
                }
                if (event.getEventType() == MouseEvent.ANY && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("entered");
                }
                if (event.getEventType() == MouseEvent.MOUSE_RELEASED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("realesed");
                }
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("pressed");
                    System.out.println(eventM.getX() + " " + eventM.getY());
                    prevX = eventM.getX();
                    prevY = eventM.getY();
                    if (rect != null) {
                        int a = webCamPane.getChildren().indexOf(rect);
                        webCamPane.getChildren().remove(a);
                    }
                    rect = new Rectangle(eventM.getX(), eventM.getY(), 1, 1);
                    rect.setFill(Color.web("rgba(255,255,255,0.3)"));
                    webCamPane.getChildren().add(rect);
                    System.out.println("pressed");
                }
                if (event.getEventType() == MouseEvent.DRAG_DETECTED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("dragDetect");
                }
                if (event.getEventType() == MouseEvent.MOUSE_MOVED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("moved");
                }
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("clicked");

//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Координаты");
//                    alert.setHeaderText("1");
//                    alert.setContentText("x: "+event.getX()+" y: "+event.getY());
//                    alert.showAndWait();
                }
            }
        };


        imgWebCamCapturedImage.addEventHandler(MouseEvent.ANY, handler);


//        initializeWebCam(1);


        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(900);
        primaryStage.setWidth(1600);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


    protected void initializeWebCam(final int webCamIndex) {

        Task<Void> webCamTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                webcam = Webcam.getWebcams().get(webCamIndex);
                webcam.open();

                startWebCamStream();

                return null;
            }
        };

        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();
    }

    protected void startWebCamStream() {


        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                final AtomicReference<WritableImage> ref = new AtomicReference<>();
                BufferedImage img = null;

                while (true) {
                    try {
                        if ((img = webcam.getImage()) != null) {

                            ref.set(SwingFXUtils.toFXImage(img, ref.get()));
                            img.flush();

                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    imageProperty.set(ref.get());
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

//                return null;
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);

    }
}
