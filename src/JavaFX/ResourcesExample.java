package JavaFX;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
* Ресурсы - файлы, которые распространяются с программойю Они "пакуются" внутрь запускаемого файла. В Java есурсы нужно
* хранить в каталоге с исходниками. Ну или в отдельном специальном каталоге, который можно отельно настроить для хранения
* ресурсов.
* В Java ресурсы, как и классы, принадлежат пакетам, поэтому важно, в какое место внутри каталога src их положили.
* Обычно их кладут в тот же пакет, что и у класса, который их использует.*/

public class ResourcesExample extends Application {

    private ImageView picture;
    private Label title;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Пример про работу с ресурсами");

        Parent root = initInterface();

        primaryStage.setScene(new Scene(root));

        primaryStage.show();

        loadText();
        loadImage();

    }


    private Parent initInterface() {
        //у нас будет два компонента: ImageView для отображения картинки и Label под ним для отображения текст.
        //Картинка и текст будут взяты из ресурсов.
        picture = new ImageView();
        title = new Label();

        return new VBox(picture, title);
    }

    private void loadText() {
        //как получить доступ к ресурсам?
        // 1) к ресурсу можно получить доступ через PrintStream
        try (
                InputStream text = ResourcesExample
                .class //это доступ к самому классу (см. рефлексия)
                .getResourceAsStream("title_jp.txt") //ресурс из того же пакета, что и сам класс
        ) {
            //рассчитываем, что хватит 1024 байт для чтения текста
            byte[] bytesFromInputStream = new byte[1024];
            //возвратит, сколько прочитано
            int read = text.read(bytesFromInputStream);
            //превращаем набор байт в текст, указываем, сколько байт брать из массива
            String titleText = new String(bytesFromInputStream, 0, read, StandardCharsets.UTF_8);
            title.setFont(new Font(20));
            //неудобно, что при чтении ресурса мы получаем InputStream, но так исторически сложилось, пользуемся тем,
            //что есть. В других ситуациях будут вспомогательные методы для чтения InputStream

            title.setText(titleText);
        } catch (IOException e) {
            title.setText("Не удалось загрузить текст");
        }
    }

    private void loadImage() {
        //загружаем картинку (javafx.scene.Image)
        try (
                InputStream image = ResourcesExample
                        .class //это доступ к самому классу (см. рефлексия)
                        .getResourceAsStream("ron.jpg") //ресурс из того же пакета, что и сам класс
        ) {
            Image img = new Image(image); //картинку можно создать напрямую из InputStream
            picture.setImage(img);
            picture.setFitHeight(400);
            picture.setFitWidth(400);
            picture.setPreserveRatio(true);
        } catch (IOException e) {
            //ничего не делаем, просто не будем загружать и всё
        }

        // 2)
        //Доступ к ресурсу можно получить ещё и через URL
        URL picURL = ResourcesExample.class.getResource("ron.jpg");
        System.out.println("url: " + picURL);
        new Image(picURL.toExternalForm());
    }
}
