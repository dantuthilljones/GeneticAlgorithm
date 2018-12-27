package genetic.tsp;

import genetic.CreatureReproducer;

import java.util.Random;

public class RouteShiftReproducer implements CreatureReproducer<Route> {

    private final double _instability;
    private final Random rand = new Random();

    public RouteShiftReproducer(double instability){
        _instability = instability;
    }

    @Override
    public Route reproduce(Route parent) {
        int[] order = parent.getOrder().clone();

        //randomly mutate
        for (int i = 0; i < order.length; i++) {
            if(_instability > rand.nextDouble()) {
                shiftSwap(order, i, rand.nextInt(order.length));
            }
        }
        return new Route(order);
    }


    public static void shiftSwap(int[] array, int i1, int i2) {
        int valueAtOldIndex = array[i1];
        int movingIndex = i1;
        int nextIndex = (i1+1) % array.length;
        while(movingIndex != i2) {
            array[movingIndex] = array[nextIndex];
            movingIndex = nextIndex;
            nextIndex = (nextIndex+1) % array.length;
        }
        array[i2] = valueAtOldIndex;
    }
}
