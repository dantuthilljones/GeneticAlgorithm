package genetic.tsp;

import genetic.World;

import javax.swing.*;

public class TSPWindow extends JFrame {

    private final Country _country;
    private final World<Route> _world;
    private final TSPPanel _bestPanel;
    private final TSPPanel _medianPanel;
    private final TSPPanel _worstPanel;

    public TSPWindow(Country country, World<Route> world) {
        _country = country;
        _world = world;

        _bestPanel = new TSPPanel(_country);
        _medianPanel = new TSPPanel(_country);
        _worstPanel = new TSPPanel(_country);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Best", _bestPanel);
        tabbedPane.add("Median", _medianPanel);
        tabbedPane.add("Worst", _worstPanel);

        setContentPane(tabbedPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(country.getWidth() + 100, country.getHeight() + 100);
        setVisible(true);
    }

    public void setBest(Route route) {
        _bestPanel.setRoute(route);
    }

    public void setMedian(Route route) {
        _medianPanel.setRoute(route);
    }

    public void setWorst(Route route) {
        _worstPanel.setRoute(route);
    }
}
