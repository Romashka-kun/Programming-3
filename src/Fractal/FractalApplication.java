package Fractal;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FractalApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Circle Fractal");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Parent initInterface() {
        int width = 400;
        int height = 400;
        final double dx = 6.0 / 400;

//        Fractal fractal = new GradientCircleFractal();
        Palette palette = new GreyPalette();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double N = 1000;
        int R = 1000000000;

        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                double x = -3 + i * dx;
                double y = 3 - j * dx;
                double zRe = 0;
                double zIm = 0;
                for (int k = 0; k < N; k++) {
                    double z = zRe * zIm;
                    zRe = zRe * zRe - zIm * zIm + x;
                    zIm = 2 * z + y;
                    if (Math.sqrt(zRe * zRe + zIm * zIm) > R) {
                        Color color = palette.getColor(k / N);
                        pixelWriter.setColor(i, j, color);
                        break;
                    }
                }
//                double colorIndex = fractal.getColor(x, y);
            }
        }
        ImageView imageView = new ImageView(writableImage);
        return new Pane(imageView);
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
