package JavaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageLibrary extends Application {

    private ListView<File> imageList;
    private ImageView imageView;
    private DirectoryChooser directoryChooser;
    private Button browse;
    private TextField pathToDirectory;
    private File directory;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Library");
        Parent root = initInterface();

        initInteraction();
        initData();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initData() {
        ObservableList<File> images = FXCollections.observableArrayList();
        for (File f : directory.listFiles())
            images.add(new File(f.getPath()));
        imageList.setItems(images);
    }

    private void initInteraction() {

        imageList.getSelectionModel().selectedItemProperty().addListener(
                prop -> loadImage(imageView,
                        new Image("file:\\" + imageList.getSelectionModel().getSelectedItem().getPath()),
                        200, 200)
        );

        browse.setOnAction(
                a -> {
                    directory = directoryChooser.showDialog(new Stage());
                    pathToDirectory.setText(directory.getPath());
                    System.out.println(directory);
                    initData();
                }
        );

    }

    private Parent initInterface() {

        imageList = new ListView<>();

        imageList.setCellFactory(
                lv -> new ListCell<File>() {
                    @Override
                    protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText("");
                        } else {
                            setGraphic(loadImage(new ImageView(),
                                    new Image("file:\\" + item.toPath()),
                                    64, 64));
                            setText(item.getName());
                        }
                    }
                }
        );

        directoryChooser = new DirectoryChooser();
        directory = new File("C:\\Users\\braid\\Pictures\\iCloud Photos\\Downloads");

        browse = new Button("Обзор");
        pathToDirectory = new TextField(directory.getPath());
        pathToDirectory.setEditable(false);
        HBox hb1 = new HBox(pathToDirectory, browse);
        imageView = new ImageView();
        VBox vb1 = new VBox(hb1, imageView);

        return new SplitPane(imageList, vb1);
    }

    private ImageView loadImage(ImageView iv, Image img, int w, int h) {
        iv.setImage(img);
        iv.setFitHeight(h);
        iv.setFitWidth(w);
        iv.setPreserveRatio(true);

        return iv;
    }
}
