package JavaFX;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GridPaneExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GridPane");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Parent initInterface() {
        GridPane root = new GridPane();

        TextArea ta = new TextArea();
        TextField tf = new TextField();
        Button b1 = new Button("Send");
        Label l1 = new Label("Contacts");
        ListView<String> lv1 = new ListView<>();

        GridPane.setColumnIndex(ta, 0);
        GridPane.setRowIndex(ta, 0);
        GridPane.setColumnSpan(ta, 2);
        GridPane.setRowSpan(ta, 2);
        root.getChildren().add(ta);

        //можно делать проще
        root.add(tf, 0, 2);
        root.add(b1, 1, 2);
        root.add(l1, 2, 0);
        root.add(lv1, 2, 1, 1, 2);

        ColumnConstraints cc0 = new ColumnConstraints();
        cc0.setHgrow(Priority.ALWAYS);
        //Hgrow аналогичен методу Hgrow для HBox. Есть много других constraints в cc0

        RowConstraints rc0 = new RowConstraints();
        RowConstraints rc1 = new RowConstraints();
        rc1.setVgrow(Priority.ALWAYS);

        //column/row constraints - изначально пустые списки
        root.getColumnConstraints().add(cc0);
        root.getRowConstraints().addAll(rc0, rc1);

        lv1.setPrefSize(200, 0);

        return root;
    }
}
