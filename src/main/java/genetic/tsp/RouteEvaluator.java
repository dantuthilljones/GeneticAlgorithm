package genetic.tsp;

import genetic.FitnessEvaluator;

public class RouteEvaluator implements FitnessEvaluator<Route> {

    private final double[][] _distances;

    public RouteEvaluator(Country country) {
        Town[] towns = country.getTowns();
        _distances = new double[towns.length][towns.length];

        for(int i = 0; i < towns.length; i++){
            for(int j = i+1; j < towns.length; j++) {
                Town from = towns[i];
                Town to = towns[j];

                double dx = to.getX() - from.getX();
                double dy = to.getY() - from.getY();

                _distances[i][j] = Math.sqrt(dx*dx + dy*dy);
                _distances[j][i] = _distances[i][j];
            }
        }

    }

    @Override
    public double evaluate(Route route) {
        double total = 0;

        int[] order = route.getOrder();
        for(int i = 0; i < order.length -1; i++) {
            total += _distances[ order[i] ][ order[i+1] ];
        }

        total += _distances[ order[0] ][ order[order.length -1] ];
        return total;
    }
}
