package JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PropertiesListenersAndBindings extends Application {

    private TextField tf1;
    private Button b1;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Properties, Listeners and Bindings");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction(); //в этом методе будет описываться поведение элементов интерфейса

        primaryStage.show();
    }

    private void initInteraction() {
        //как заставить кнопку что-то делать?

        //1) универсальный, но сложный способ
        //В JavaFX реализована работа с событиями. Разные объекты генерируют события
        //можно добавлять слушателей (обработчиков), которые реагируют на интересующие их события.
        //Кнопка генерирует событие типа ActionEvent.ACTION, это происходит в момент нажатия на кнопку. Нажатие может
        //быть разным: мышкой, кнопкой Enter, пробелом, касанием экрана и т.п.
        //В принципе, все Node, ключая кнопку, генерирует ещё и события мыши, т.е. можно реагировать на событие, что на
        //кнопку  наведена/нажата/отпущена мышь. Но в этой программе нам это не интересно.

        b1.addEventHandler(
                ActionEvent.ACTION, //какое именно событие кнопки нас интересует
                a -> System.out.println("pressed" + a)
                //второе - это действие в виде лямбда-выражения. a - информация о событии. В этой информации всегда
                //можно найти источник события. Если бы у нас было событие от мыши, мы бы дополнительно могли получить
                //информацию о положении курсора.
        );
    }

    private Parent initInterface() {
        FlowPane root = new FlowPane();

        VBox example1 = new VBox();
        tf1 = new TextField();
        b1 = new Button("Example");
        example1.getChildren().addAll(tf1, b1);

        //раскрасим панель example
        example1.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#FF0000"),
                                new CornerRadii(0),
                                Insets.EMPTY //пустой padding
                        )
                )
        );
        example1.setStyle("-fx-background-color: #00FF00");

        return example1;
    }
}
