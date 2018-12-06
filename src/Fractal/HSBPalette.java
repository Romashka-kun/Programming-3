package Fractal;

import javafx.scene.paint.Color;

public class HSBPalette implements Palette {
    @Override
    public Color getColor(double index) {
        return Color.hsb(index * 360, 0.8, 1);
    }
}
