package JavaFX;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PaletteInterface extends Application {

    private Slider radiusSlider = new Slider();
    private ColorPicker circleColor = new ColorPicker();
    private ColorPicker backgroundColor = new ColorPicker();
    private Label radiusLabel = new Label("Radius of circle");
    private Label circleLabel = new Label("Choose circle color");
    private Label backgroundLabel = new Label("Choose background color");
    private Circle circle = new Circle(100);
    private Pane rightPane;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Palette");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction(); //в этом методе будет описываться поведение элементов интерфейса

        primaryStage.show();
    }

    private void initInteraction() {

        circleColor.setOnAction(
                prop -> circle.setFill(circleColor.getValue())
        );

        circle.radiusProperty().bind(
                radiusSlider.valueProperty()
        );

        backgroundColor.setOnAction(
                prop -> rightPane.setStyle("-fx-background-color: #" + Integer.toHexString(
                        backgroundColor.getValue().hashCode()))
        );

        rightPane.widthProperty().addListener(
                prop -> circle.setCenterX(rightPane.getWidth() / 2)
        );

        rightPane.heightProperty().addListener(
                prop -> circle.setCenterY(rightPane.getHeight() / 2)
        );
    }

    private Parent initInterface() {
        HBox root = new HBox();


        VBox leftPane = new VBox(radiusLabel, radiusSlider, circleLabel, circleColor, backgroundLabel, backgroundColor);
        rightPane = new Pane(circle);

        leftPane.setPrefWidth(300);
        rightPane.setStyle("-fx-background-color: #" + Integer.toHexString(
                backgroundColor.getValue().hashCode()));

        HBox.setHgrow(rightPane, Priority.ALWAYS);
        VBox.setVgrow(rightPane, Priority.ALWAYS);

        radiusSlider.setValue(50);
        root.getChildren().addAll(leftPane, rightPane);


        return root;
    }
}
