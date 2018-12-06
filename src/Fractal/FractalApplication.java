package Fractal;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FractalApplication extends Application {

    private Button moveRight;
    private Button moveLeft;
    private double dx;
    private double xo;
    private double yo;
    private int width;
    private int height;
    private ImageView imageView;
    private Button moveDown;
    private Button moveUp;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Circle Fractal");
        Parent root = initInterface();

        initInteraction();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initInteraction() {
        moveRight.setOnAction(
                a -> {
                    xo += dx * width / 4;
                    imageView.setImage(drawFractal());
                }
        );

        moveLeft.setOnAction(
                a -> {
                    xo -= dx * width / 4;
                    imageView.setImage(drawFractal());
                }
        );

        moveDown.setOnAction(
                a -> {
                    yo -= dx * height / 4;
                    imageView.setImage(drawFractal());
                }
        );

        moveUp.setOnAction(
                a -> {
                    yo += dx * height / 4;
                    imageView.setImage(drawFractal());
                }
        );

        imageView.addEventHandler(
                MouseEvent.MOUSE_DRAGGED,
                a -> {
                    xo += a.getSceneX() / 600;
                    yo += a.getSceneY() / 600;
                    imageView.setImage(drawFractal());
                }
        );
    }

    private WritableImage drawFractal() {
        Fractal fractal = new Mandelbrot();
        Palette palette = new HSBPalette();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                double x = xo + i * dx;
                double y = yo - j * dx;
                double colorIndex = fractal.getColor(x, y);
                Color color = palette.getColor(colorIndex);
                pixelWriter.setColor(i, j, color);
            }
        }
        return writableImage;
    }

    private Parent initInterface() {
        width = 600;
        height = 600;
        dx = 0.1 / 600;
        xo = -0.3;
        yo = 0.8;


        moveRight = new Button("→");
        moveLeft = new Button("←");
        moveUp = new Button("↑");
        moveDown = new Button("↓");
        imageView = new ImageView(drawFractal());
        return new Pane(new VBox(imageView, new HBox(moveLeft, moveRight, moveUp, moveDown)));
    }

    /*
    * WritableImage - это Image (подходит для ImageView), у которого можно менять отедльные пиксели
    * WI wi = new WI (ширина, высота)
    * PixelWriter pw = wi.getPixelWriter();
    * pw.setColor(x, y, color)
    *
    *
    * см. фотографию (x0 = -3, y0 = 3, а не как там написано -200 и 200)
    *
    *
    * как рисовать фрактал
    * перебрать все пиксели
    * for x| от 0 до ширины - 1
    *   for y| от 0 до высоты - 1
    *       x = x0 + x| * dx;
    *       y = y0 - y| * dx;
    *       colorInd = fractal.getColorInd(x, y);
    *       color = palette.getColor(colorInd);
    *       pw.write(x|, y| color)*
    *
    *
    *
    * Дано С - комплексное
    * Процесс:
    * z = 0
    * z -> z^2 + c
    * повторять бесконечное число раз
    * при с = 1 уходит на бесконечность
    * при с = -1 ограничена
    * при с = i ограничена
    * Мандельброт состоит из тех комплексных точек, которые ограничены
    *
    * Алгоритм:
    * Дано с (т.е. точка на плоскости с = x + iy)
    * N = 1000;
    * R = 10^9;
    * z = 0;
    * for i = 0 до N
    *   z -> z^2 + c;       zRe, zIm - две переменные, abs(z) = abs(a + bi) = sqrt(a * a + b * b)
    *   if abs(z) > R then break;
    * цвет = i/N;
    */
}
/*
---------------------------------








---------------------------------
 */