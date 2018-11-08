package JavaFX;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PaletteInterface extends Application {

    private Slider radiusSlider = new Slider();
    private ColorPicker circleColor = new ColorPicker();
    private ColorPicker backgroundColor = new ColorPicker();
    private Label radiusLabel = new Label("Radius of circle");
    private Label circleLabel = new Label("Choose circle color");
    private Label backgroundLabel = new Label("Choose background color");
    private Circle circle = new Circle();
    private Pane rightPane;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Palette");
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction(); //в этом методе будет описываться поведение элементов интерфейса

        primaryStage.show();

        radiusSlider.setValue((radiusSlider.getMin() + radiusSlider.getMax()) / 2);
    }

    private void initInteraction() {

        circle.fillProperty().bind(
                circleColor.valueProperty()
        );

        circle.radiusProperty().bind(
                radiusSlider.valueProperty()
        );

        rightPane.backgroundProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Background(
                                new BackgroundFill(
                                        backgroundColor.getValue(), CornerRadii.EMPTY, Insets.EMPTY
                                )
                        ), backgroundColor.valueProperty()
                )
        );

        circle.centerXProperty().bind(
                Bindings.createDoubleBinding(
                        () -> rightPane.getWidth() / 2,
                        rightPane.widthProperty()
                )
        );

        circle.centerYProperty().bind(
                Bindings.divide(rightPane.heightProperty(), 2)
        );

        radiusSlider.maxProperty().bind(
                Bindings.createDoubleBinding(
                        () -> rightPane.getHeight() <= rightPane.getWidth() ?
                                rightPane.getHeight() / 2 : rightPane.getWidth() / 2,
                        rightPane.heightProperty(),
                        rightPane.widthProperty()
                )
        );
    }

    private Parent initInterface() {
        HBox root = new HBox();

        radiusSlider.setMajorTickUnit(50);
        radiusSlider.setMinorTickCount(5);
        radiusSlider.setShowTickMarks(true);
        radiusSlider.setShowTickLabels(true);

        VBox leftPane = new VBox(radiusLabel, radiusSlider, circleLabel, circleColor, backgroundLabel, backgroundColor);
        rightPane = new Pane(circle);

        leftPane.setPrefWidth(300);
        rightPane.setPrefSize(300, 300);
        circleColor.setValue(Color.BLACK);

        HBox.setHgrow(rightPane, Priority.ALWAYS);
        VBox.setVgrow(rightPane, Priority.ALWAYS);

        root.getChildren().addAll(leftPane, rightPane);

        return root;
    }
}
