package JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppTask extends Application {

    private Button button;
    private TextArea textArea;
    private TextField textField;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        initInteraction();
        primaryStage.show();

    }

    private void initInteraction() {
        textField.requestFocus();

        EventHandler<ActionEvent> sendAction = prop -> {
            String text = textField.getText();

            if (text.trim().isEmpty())
                return;

            textArea.appendText(text + "\n");
            textField.clear();
            textField.requestFocus();
        };

        button.setOnAction(sendAction);
        textField.setOnAction(sendAction);
    }

    private Parent initInterface() {
        HBox root = new HBox();

        textArea = new TextArea();
        textField = new TextField();
        button = new Button("Send");
        HBox hb1 = new HBox(textField, button);
        VBox vb1 = new VBox(textArea, hb1);

        Label l1 = new Label("Contacts");
        ListView lv1 = new ListView();

        VBox vb2 = new VBox(l1, lv1);

        root.getChildren().addAll(vb1, vb2);

        textArea.setEditable(false);

        HBox.setHgrow(textField, Priority.ALWAYS);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        HBox.setHgrow(vb1, Priority.ALWAYS);

        VBox.setVgrow(textArea, Priority.ALWAYS);
        VBox.setVgrow(lv1, Priority.ALWAYS);

        vb2.setMaxWidth(Region.USE_PREF_SIZE);

        return root;
    }
}


