package genetic.tsp;

import genetic.CreatureReproducer;

import java.util.Random;

public class RouteSwapReproducer implements CreatureReproducer<Route> {

    private final double _instability;
    private final Random rand = new Random();

    public RouteSwapReproducer(double instability){
        _instability = instability;
    }

    @Override
    public Route reproduce(Route parent) {
        int[] order = parent.getOrder().clone();

        //randomly mutate
        for (int i = 0; i < order.length; i++) {
            if(_instability > rand.nextDouble()) {
                int swapIndex = rand.nextInt(order.length);
                int temp = order[swapIndex];
                order[swapIndex] = order[i];
                order[i] = temp;
            }
        }
        return new Route(order);
    }
}
