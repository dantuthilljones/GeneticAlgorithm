package genetic.tsp;

import java.awt.*;
import java.util.Random;

public class Country {

    private static final Random rand = new Random();

    private final Town[] _towns;
    private final int _width;
    private final int _height;

    public Country(int towns, int width, int height, int border) {
        _towns = new Town[towns];
        _height = height;
        _width = width;

        for(int i = 0; i < towns; i++) {
            Color colour = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            int x = rand.nextInt(width - border*2) + border;
            int y = rand.nextInt(height - border*2) + border;

            //TODO make sure it's not too close to other towns
            _towns[i] = new Town(i+1, colour, x, y);
        }
    }

    public void draw(Graphics2D g) {
        for(Town town: _towns) {
            town.draw(g);
        }
    }

    public Town[] getTowns() {
        return _towns;
    }

    public int getHeight() {
        return _height;
    }

    public int getWidth() {
        return _width;
    }
}
