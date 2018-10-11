package JavaFX;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppTask extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    private Parent initInterface() {
        HBox root = new HBox();

        TextArea ta = new TextArea();
        TextField tf = new TextField();
        Button b1 = new Button("Send");
        HBox hb1 = new HBox(tf, b1);
        VBox vb1 = new VBox(ta, hb1);

        Label l1 = new Label("Contacts");
        ListView lv1 = new ListView();

        VBox vb2 = new VBox(l1, lv1);

        root.getChildren().addAll(vb1, vb2);

        HBox.setHgrow(tf, Priority.ALWAYS);
        HBox.setHgrow(ta, Priority.ALWAYS);
        HBox.setHgrow(vb1, Priority.ALWAYS);

        VBox.setVgrow(ta, Priority.ALWAYS);
        VBox.setVgrow(lv1, Priority.ALWAYS);

        vb2.setMaxWidth(Region.USE_PREF_SIZE);

        return root;
    }
}


