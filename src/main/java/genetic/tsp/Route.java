package genetic.tsp;

import java.awt.*;

public class Route {

    private final int[] _order;

    public Route(int[] order) {
        _order = order;
    }

    public int[] getOrder() {
        return _order;
    }

    public void draw(Graphics2D g, Town[] towns) {
        g.setColor(Color.BLACK);

        for(int i = 0; i < _order.length -1; i++){
            Town from = towns[ _order[i] ];
            Town to = towns[ _order[i+1] ];
            g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
        }

        Town from = towns[ _order[0] ];
        Town to = towns[ _order[_order.length -1] ];
        g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
    }



    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append('[');
        builder.append(_order[0]);
        for(int i = 0; i < _order.length; i++) {
            builder.append(", " + _order[i]);
        }
        builder.append("]");

        return builder.toString();
    }


}
