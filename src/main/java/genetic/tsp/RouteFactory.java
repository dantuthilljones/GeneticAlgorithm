package genetic.tsp;

import genetic.CreatureFactory;

import java.util.Random;

public class RouteFactory implements CreatureFactory<Route> {

    private static final Random rand = new Random();

    private final int[] _towns;

    public RouteFactory(int numberOfTowns) {
        _towns = new int[numberOfTowns];
        for (int i = 0; i < numberOfTowns; i++) {
            _towns[i] = i;
        }
    }

    @Override
    public Route createCreature() {
        int[] order = _towns.clone();
        shuffle(order);
        return new Route(order);
    }

    private static void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
