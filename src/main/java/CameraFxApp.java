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
import javafx.embed.swt.SWTFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    ContextMenu сontextMenu;
    private Rectangle area;
    final FileChooser fileChooser = new FileChooser();
    Image image;
    BufferedImage bi;
    CameraStage right;
    CameraStage left;


    @Override
    public void start(final Stage primaryStage) throws Exception {
        right = new CameraStage("Right", this);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            left = new CameraStage("Left", CameraFxApp.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                System.out.println("yes");
                Thread.sleep(5);
                return null;
            }
        };
        Thread thread =  new Thread(task);
        thread.setDaemon(true);
        thread.start();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
//        bi = ImageIO.read(new File("10_right.png"));
////        image = new Image(new File("10_right.png").toURI().toURL().toString());
//        image = SwingFXUtils.toFXImage(bi, null);
//
//        root = new BorderPane();
//        webCamPane = new BorderPane();
////        imgWebCamCapturedImage = new ImageView();
//        imgWebCamCapturedImage = new ImageView(image);
//        webCamPane.getChildren().add(imgWebCamCapturedImage);
//        root.setCenter(webCamPane);
//
//
//        сontextMenu = new ContextMenu();
//        MenuItem makeFrag = new MenuItem("Вырезать фрагмент");
//        makeFrag.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                File file = fileChooser.showOpenDialog(primaryStage);
//                Frag frag = new Frag();
//                try {
//                    frag.makeFrag(bi, (int) area.getX(), (int) area.getY(), (int) area.getWidth(), (int) area.getHeight(), file);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        сontextMenu.getItems().add(makeFrag);
//
//
//        EventHandler handler = new EventHandler() {
//
//            double prevX = 0;
//            double prevY = 0;
//
//            @Override
//            public void handle(Event event) {
//                MouseEvent eventM = (MouseEvent) event;
//                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("dragged");
//                    if (area != null) {
//                        System.out.println(eventM.getX() + " " + prevX + " " + eventM.getY() + " " + prevY);
////                        double xSpeed=
//                        area.setHeight((int) (area.getHeight() + (eventM.getY() - prevY)));
//                        area.setWidth((int) (area.getWidth() + (eventM.getX() - prevX)));
//                        prevX = eventM.getX();
//                        prevY = eventM.getY();
//                    }
//                }
//                if (event.getEventType() == MouseEvent.MOUSE_ENTERED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("entered");
//                }
//                if (event.getEventType() == MouseEvent.ANY && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("entered");
//                }
//                if (event.getEventType() == MouseEvent.MOUSE_RELEASED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("realesed");
//                }
//                if (event.getEventType() == MouseEvent.MOUSE_PRESSED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("pressed");
//                    System.out.println(eventM.getX() + " " + eventM.getY());
//                    prevX = eventM.getX();
//                    prevY = eventM.getY();
//                    if (area != null) {
//                        int a = webCamPane.getChildren().indexOf(area);
//                        webCamPane.getChildren().remove(a);
//                    }
//                    area = new Rectangle(eventM.getX(), eventM.getY(), 1, 1);
//                    area.setFill(Color.web("rgba(255,255,255,0.3)"));
//                    webCamPane.getChildren().add(area);
//                    System.out.println("pressed");
//                }
//                if (event.getEventType() == MouseEvent.DRAG_DETECTED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("dragDetect");
//                }
//                if (event.getEventType() == MouseEvent.MOUSE_MOVED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("moved");
//                }
//                if (event.getEventType() == MouseEvent.MOUSE_CLICKED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("clicked");
//
////                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
////                    alert.setTitle("Координаты");
////                    alert.setHeaderText("1");
////                    alert.setContentText("x: "+event.getX()+" y: "+event.getY());
////                    alert.showAndWait();
//                }
//                if (eventM.getEventType() == MouseEvent.MOUSE_PRESSED && eventM.getButton() == MouseButton.SECONDARY) {
//                    сontextMenu.show(webCamPane, eventM.getScreenX(), eventM.getScreenY());
//                }
//            }
//        };
//
//
//        webCamPane.addEventHandler(MouseEvent.ANY, handler);
//
//
////        initializeWebCam(1);
//
//
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setHeight(720);
//        primaryStage.setWidth(1280);
//        primaryStage.centerOnScreen();
//        primaryStage.show();
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
