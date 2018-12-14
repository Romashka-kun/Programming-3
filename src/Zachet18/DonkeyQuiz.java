package Zachet18;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DonkeyQuiz extends Application {

    private ImageView imageView;
    private List<Integer> answers = new ArrayList<>();
    private List<String> questions = new ArrayList<>();
    private Label question;
    private int questNo = 0;
    private int scores = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Donkey Quiz");
        Parent root = initInterface();

        loadImage();
        loadText();

        initInteraction();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initInteraction() {

        imageView.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                a -> {
                    double px = a.getX();
                    double py = a.getY();

                    int x0 = answers.get(questNo * 4);
                    int y0 = answers.get(questNo * 4 + 1);
                    int x1 = answers.get(questNo * 4 + 2);
                    int y1 = answers.get(questNo * 4 + 3);
                    if (px >= x0 && py >= y0 && px <= x1 && py <= y1)
                        scores++;

                    questNo++;

                    if (questNo > 4) {
                        new Alert(Alert.AlertType.INFORMATION,
                                "Количество правильный ответов: " + scores + " из " + 5).showAndWait();
                        questNo = 0;
                        scores = 0;
                    }

                    question.setText(questions.get(questNo));
                }
        );
    }


    private Parent initInterface() {

        question = new Label();
        question.setFont(new Font(24));
        imageView = new ImageView();

        return new VBox(question, imageView);
    }

    private void loadText() {
        try (
                Scanner in = new Scanner(
                        DonkeyQuiz.class.getResourceAsStream("questions.txt"), "UTF-8"
                )
        ) {
            while (in.hasNext()) {
                answers.add(in.nextInt());
                answers.add(in.nextInt());
                answers.add(in.nextInt());
                answers.add(in.nextInt());
                questions.add(in.nextLine());
            }
        }
        question.setText(questions.get(0));
    }

    private void loadImage() {
        try (
                InputStream image = DonkeyQuiz.class.getResourceAsStream("donkey.jpg")
        ) {
            imageView.setImage(new Image(image));
            imageView.setFitHeight(500);
            imageView.setFitWidth(500);
            imageView.setPreserveRatio(true);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }
}
