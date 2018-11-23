package Fractal;

public class CircleFractal implements Fractal {

    public double getColor(double x, double y) {
        if (x * x + y * y < 1)
            return 1;
        else
            return 0;
    }

    /*
    * WriteableImage - это Image (подходит для ImageView), у которого можно менять отедльные пиксели
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
    *       pw.write(x|, y| color)*/
}
