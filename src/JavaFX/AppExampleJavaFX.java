package JavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AppExampleJavaFX extends Application {

    /*
    * Реализуем метод Start, который объясняет, как запускается приложение. В качестве аргумента в этот метод
    * передается Stage - ссылка на окно приложения. У окна есть заголовок, кнопки закрытия окна, бордюр для изменения
    * размеров окна и т.д.
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //установить заголовок окна
        primaryStage.setTitle("Hello World!");

        Parent root = initInterface();
        //задаем сцену, она содержит информацию о Parent, т.е. то, что показывать на экране. И информацию о том, как
        //показывать. Это размер окна.
        primaryStage.setScene(new Scene(root/*, width, height*/));

        //отобразить окно на экране
        primaryStage.show();
    }

    private Parent initInterface() {
        //в конструкторе можно сразу указать детей, т.е. все подузлы. И ещё можно сразу указать внешний отступ
        //(padding на CSS)

        HBox root = new HBox();
        root.setSpacing(10); //можно указать отступ позже

        Button b1 = new Button("Hello!");
        Label l1 = new Label("Just a text");
        TextField tf1 = new TextField("enter something");

        //чтобы добавить детей, обращаемся к списку детей и пользуемся методом, который позволяет добавить в список
        //сразу несколько элементов. Можно использовать метод add, но он добавляет только один элемент.
        //В JavaFX чуть-чуть другие коллекции, не такие, как мы привыкли. Коллекции в JavaFX позволяют следить за
        //своим содержимым, т.е. root сразу поймет, что ему добавили детей и перерисуется.

        //можно указать детей при создании HBox
        //HBox root = new HBox(b1, l1, tf1);

        root.getChildren().addAll(b1, l1, tf1);

        //HBox - означает, что constraint понимает все HBox'ы
        //Hgrow - название constraint, это тот, который говорит, рястягивать ли элемент, ALWAYS - всегда.
        HBox.setHgrow(tf1, Priority.ALWAYS);
        HBox.setMargin(b1, new Insets(8));

        //а это не constraint, это просто свойство нашего HBox, все элементы должны распологаться снизу по центру
        root.setAlignment(Pos.BOTTOM_CENTER);

        return root;
    }
}
