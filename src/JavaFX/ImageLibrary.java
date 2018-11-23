package JavaFX;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageLibrary extends Application {

    private ListView<File> imageList;
    private ImageView imageView;
    private DirectoryChooser directoryChooser;
    private Button browse;
    private TextField pathToDirectory;
    private Label label;
    private ObservableList<File> images;
    private StackPane stackPane;

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
        images = FXCollections.observableArrayList();
        File[] files = directoryChooser.getInitialDirectory().listFiles();
        if (files == null) {
//            System.out.println("Failed to find files in a directory");
            //TODO show message dialog or show info inside the list
            return;
        }

        for (File f : files) {
            try {
                String contentType = Files.probeContentType(f.toPath());
                if (contentType != null && contentType.startsWith("image"))
                    images.add(f);
            } catch (IOException e) {
                //do nothing
            }
        }
        imageList.setItems(images);
    }

    private void initInteraction() {

        imageList.getSelectionModel().selectedItemProperty().addListener(
                prop -> {
                    if (imageList.getSelectionModel().isEmpty()) {
                        imageView.setImage(null);
                        label.setVisible(true);
                    } else {
                        loadImage(imageView,
                                new Image("file:\\" + imageList.getSelectionModel().getSelectedItem().getPath()),
                                200, 200);
                        label.setVisible(false);
                    }
                }
        );

        browse.setOnAction(
                a -> {
                    directoryChooser.setInitialDirectory(directoryChooser.showDialog(new Stage()));
                    pathToDirectory.setText(directoryChooser.getInitialDirectory().getPath());
                    initData();
                    if (images.isEmpty())
                        imageList.setPlaceholder(new Label("нет изображений"));
                }
        );

        stackPane.prefHeightProperty().bind(
                Bindings.createDoubleBinding(
                        () -> imageList.getHeight(),
                        imageList.heightProperty()
                )
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
        directoryChooser.setInitialDirectory(new File("C:\\Users\\braid\\Pictures\\iCloud Photos\\Downloads"));

        browse = new Button("Обзор");
        pathToDirectory = new TextField(directoryChooser.getInitialDirectory().getPath());
        pathToDirectory.setEditable(false);
        HBox.setHgrow(pathToDirectory, Priority.ALWAYS);

        HBox hb1 = new HBox(pathToDirectory, browse);

        imageView = new ImageView();
        label = new Label("Выберите изображение");

        stackPane = new StackPane(imageView, label);

        VBox vb1 = new VBox(hb1, stackPane);

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
