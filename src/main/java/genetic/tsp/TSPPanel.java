package genetic.tsp;

import javax.swing.*;
import java.awt.*;

public class TSPPanel extends JPanel {

    private final Country _country;
    private Route _route;

    public TSPPanel(Country country) {
        _country = country;
        _route = null;
    }

    public void setRoute(Route route) {
        _route = route;
        repaint();
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());


        if(_route != null) {
            _route.draw(g, _country.getTowns());
        }

        for(Town town : _country.getTowns()) {
            town.draw(g);
        }
    }
}
