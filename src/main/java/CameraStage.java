import com.github.sarxos.webcam.Webcam;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CameraStage extends Stage {

    private CameraFxApp parent;

    String name;
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
    private TextArea distanceLable = null;


    CameraStage(String name, File file, final CameraFxApp parent) throws IOException {
        System.out.println("run");
        this.parent = parent;
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        bi = ImageIO.read(file);
//        image = new Image(new File("10_right.png").toURI().toURL().toString());
//        image = SwingFXUtils.toFXImage(bi, null);

        root = new BorderPane();
        webCamPane = new BorderPane();
        imgWebCamCapturedImage = new ImageView();
//        imgWebCamCapturedImage = new ImageView(image);
        webCamPane.getChildren().add(imgWebCamCapturedImage);
        root.setCenter(webCamPane);

        BufferedImage bi1 = ImageIO.read(file);
        for (int i = 0; i < bi1.getHeight(); i += 17) {
            for (int j = 0; j < bi1.getWidth(); j += 17) {
                bi1.setRGB(j, i, 0);
            }
        }

        imgWebCamCapturedImage.setImage(SwingFXUtils.toFXImage(bi1, null));

//        BufferedImage bi1 = ImageIO.read(file);
//
//        for(int i = 0; i<bi1.getHeight(); i+=16){
//            for (int j = 0; j < bi1.getWidth(); j+=16) {
//
//                bi1.setRGB(j,i, 0);
//            }
//        }
//        imgWebCamCapturedImage.setImage(SwingFXUtils.toFXImage(bi1, null));


        сontextMenu = new ContextMenu();
        MenuItem makeFrag = new MenuItem("Вырезать фрагмент");
        makeFrag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(CameraStage.this);
                Frag frag = new Frag();
                try {
                    frag.makeFrag(bi, (int) area.getX(), (int) area.getY(), (int) area.getWidth(), (int) area.getHeight(), file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        MenuItem findFrag = new MenuItem("Найти объект");
        findFrag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Frag frag = new Frag();
                Grad grad = new Grad(bi);
                try {
                    grad.gradGray();
                    grad.gradientMap();
//                    BufferedImage bi1 = grad.generalMapImage();
//                    System.out.println(area.getX() + " " + area.getY());
                    BufferedImage bi2 = frag.makeFrag(grad.bi, (int) area.getX(), (int) area.getY(), (int) area.getWidth(), (int) area.getHeight());
//                    imgWebCamCapturedImage.setImage(SwingFXUtils.toFXImage(bi1, null));
                    File file = new File("frag17.png");
                    ImageIO.write(bi2, "png", file);
                    parent.left.findObject(bi2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        final MenuItem findDistance = new MenuItem("Найти расстояние");
        findDistance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    findDistance();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        сontextMenu.getItems().addAll(makeFrag, findFrag, findDistance);


        EventHandler handlerMouse = new EventHandler() {

            double prevX = 0;
            double prevY = 0;

            @Override
            public void handle(Event event) {
                MouseEvent eventM = (MouseEvent) event;
                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("dragged");
                    if (area != null) {
//                        System.out.println(eventM.getX() + " " + prevX + " " + eventM.getY() + " " + prevY);
//                        double xSpeed=
                        area.setHeight((int) (area.getHeight() + (eventM.getY() - prevY)));
                        area.setWidth((int) (area.getWidth() + (eventM.getX() - prevX)));
                        prevX = eventM.getX();
                        prevY = eventM.getY();
                    }
                }
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("entered");
                }
                if (event.getEventType() == MouseEvent.ANY && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("entered");
                }
                if (event.getEventType() == MouseEvent.MOUSE_RELEASED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("realesed");
                }
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("pressed");
//                    System.out.println(eventM.getX() + " " + eventM.getY());
                    prevX = eventM.getX();
                    prevY = eventM.getY();
                    if (area != null) {
                        int a = webCamPane.getChildren().indexOf(area);
                        webCamPane.getChildren().remove(a);
                    }
                    area = new Rectangle(eventM.getX(), eventM.getY(), 1, 1);
                    area.setFill(Color.web("rgba(255,255,255,0.3)"));
                    webCamPane.getChildren().add(area);
//                    System.out.println("pressed");
                }
                if (event.getEventType() == MouseEvent.DRAG_DETECTED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("dragDetect");
                }
                if (event.getEventType() == MouseEvent.MOUSE_MOVED && eventM.getButton() == MouseButton.PRIMARY) {
//                    System.out.println("moved");
                }
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED && eventM.getButton() == MouseButton.PRIMARY) {
                    System.out.println("ev" + eventM.getX() + " " + eventM.getY());
//                    System.out.println("clicked");
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Координаты");
//                    alert.setHeaderText("1");
//                    alert.setContentText("x: "+event.getX()+" y: "+event.getY());
//                    alert.showAndWait();
                }
                if (eventM.getEventType() == MouseEvent.MOUSE_PRESSED && eventM.getButton() == MouseButton.SECONDARY) {
                    сontextMenu.show(webCamPane, eventM.getScreenX(), eventM.getScreenY());
                }
            }
        };


        webCamPane.addEventHandler(MouseEvent.ANY, handlerMouse);
//        for(int i = 0; i<webCamPane.getHeight(); i+=16){
//            for (int j = 0; j < webCamPane.getWidth(); j+=16) {
//                Rectangle rec = new Rectangle(i,j,1,1);
//                rec.setFill(new Color(0,0,0,1));
//                webCamPane.getChildren().add(rec);
//            }
//        }



        setTitle(name);
        setScene(new Scene(root));
        setHeight(720);
        setWidth(1280);
        centerOnScreen();
        show();
    }

    public void findObject(BufferedImage bi1) throws IOException {
        SVM svm = new SVM();
//        imgWebCamCapturedImage.setImage(SwingFXUtils.toFXImage(bi1, null));
        Frag frag = new Frag();

        Grad grad = new Grad(bi);
        try {
            BufferedImage bi2 = grad.gradGray(bi);
            bi2 = grad.gradientMap(bi2);
//            bi2 = grad.generalMapImage(bi2);
            MatchFrag matchFrag = new MatchFrag(bi1);
            bi2 = matchFrag.findMatchHOG(bi2);
//            bi.setRGB(matchFrag.prefX, matchFrag.prefY, new java.awt.Color(250, 250, 0).getRGB());
            area = new Rectangle(matchFrag.prefX, matchFrag.prefY, parent.right.area.getWidth(), parent.right.area.getHeight());
            area.setFill(Color.web("rgba(255,255,255,0.3)"));
            webCamPane.getChildren().add(area);
            imgWebCamCapturedImage.setImage(SwingFXUtils.toFXImage(bi, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findDistance() throws IOException {
        Distance distance = new Distance(5.26, 2.96, 60, 10.08);
        double r = distance.calcDistance(parent.left.area.getX(), area.getX());
//        double d = parent.left.area.getX() - area.getX();
//        double a = 6.05 * Math.sqrt(3) / 2;
//        double b = Math.sqrt(3 * (5.26 * 5.26 + 2.96 * 2.96)) / 2;
//        double c = (a + b) / 2;
//        double r = 10.08 * c / d;
        System.out.println("r " + r);
//        if (distanceLable != null) {
//            int i = webCamPane.getChildren().indexOf(distanceLable);
//            webCamPane.getChildren().remove(a);
//            distanceLable = null;
//        }
        distanceLable = new TextArea("Расстояние до объекта: ");
        distanceLable.setWrapText(true);
        distanceLable.setEditable(false);


//        distanceLable.setText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Определение расстояния");
        alert.setHeaderText("Полученное расстояние: ");
        alert.setContentText(r+"");
        alert.show();

//        webCamPane.getChildren().;
//        distanceLable.setVisible(true);
//        Grad grad = new Grad(bi);
//        BufferedImage bi2 = grad.gradGray(bi);
//        bi2 = grad.gradientMap(bi2);
//        int arr[] = new int[3];
//        arr = parent.left.getDistanceCoordinates();
//        parent.left.getDistanceCoordinates();
    }

    public void getDistanceCoordinates() throws IOException {
        Grad grad = new Grad(bi);
        BufferedImage bi2 = grad.gradGray(bi);
        bi2 = grad.gradientMap(bi2);
        bi2 = grad.generalMapImage(bi2);
        int k = 0;
        System.out.println("go" + area.getWidth());
        for (int i = (int) area.getX(); i < area.getWidth(); i += area.getWidth() / 2) {
            for (int j = (int) area.getY(); j < area.getWidth(); j++) {
                if (bi2.getRGB(i, j) != bi2.getRGB((int) area.getY(), (int) area.getX())) {
                    bi2.setRGB(i, j, 0xffffff);
                    break;
//                  arr[]
                }
                System.out.println("yes");
                bi2.setRGB(j, i, 0xffffff);
            }
        }
        imgWebCamCapturedImage.setImage(SwingFXUtils.toFXImage(bi2, null));
    }

}
