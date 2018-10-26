package JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppTask extends Application {

    private Button b1;
    private TextArea ta;
    private TextField tf;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();
        primaryStage.show();

    }

    private void initInteraction() {
        b1.addEventHandler(
                ActionEvent.ACTION,
                a -> ta.appendText(tf.getText() + "\n")
        );
    }

    private Parent initInterface() {
        HBox root = new HBox();

        ta = new TextArea();
        tf = new TextField();
        b1 = new Button("Send");
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


