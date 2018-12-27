package genetic.tsp;

import java.awt.*;

public class Town {

    private final int _number;
    private final int _x;
    private final int _y;
    private final Color _colour;

    public Town(int num, Color colour, int x, int y) {
        _number = num;
        _colour = colour;
        _x = x;
        _y = y;
    }

    public void draw(Graphics2D g) {
        g.setColor(_colour);
        g.fillOval(_x -5, _y-5, 10, 10);
        g.drawString(Integer.toString(_number), _x + 10, _y);
    }

    public int getX() {
        return _x;
    }

    public int getY(){
        return _y;
    }
}
