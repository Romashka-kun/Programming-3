package Fractal;

public class Mandelbrot implements Fractal {
    @Override
    public double getColor(double x, double y) {
        int N = 1000;
        int R = 1000000000;
        double zRe = 0;
        double zIm = 0;
        int i;
        for (i = 0; i < N; i++) {
            double z = zRe * zIm;
            zRe = zRe * zRe - zIm * zIm + x;
            zIm = 2 * z + y;
            if (Math.sqrt(zRe * zRe + zIm * zIm) > R)
                break;
        }

        return (double) i / N;
    }
}
