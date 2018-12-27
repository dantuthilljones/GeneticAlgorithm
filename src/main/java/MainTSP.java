import genetic.CreatureFactory;
import genetic.CreatureReproducer;
import genetic.FitnessEvaluator;
import genetic.World;
import genetic.tsp.*;

public class MainTSP {

    public static void main(String args[]) throws InterruptedException {
        int numTowns = 20;
        int width = 1000;
        int height = 500;
        int border = 10;

        int numRoutes = 1000;
        double instability = 0.1;

        Country country = new Country(numTowns, width, height, border);
        FitnessEvaluator<Route> evaluator = new RouteEvaluator(country);
        CreatureFactory<Route> factory = new RouteFactory(numTowns);
        CreatureReproducer<Route> reproducer = new RouteShiftReproducer(instability);

        World<Route> world= new World(numRoutes, factory, reproducer, evaluator, World.LOWER_IS_FITTER);

        TSPWindow window = new TSPWindow(country, world);
        System.out.println(window.getState());
        Route bestRoute = world.getBest();
        Route medianRoute = world.getMedian();
        Route worstRoute = world.getWorst();
        window.setBest(bestRoute);
        window.setMedian(medianRoute);
        window.setWorst(worstRoute);

        while(true) {
            world.newGeneration();

            if(world.getGeneration() % 10 == 0) {

                bestRoute = world.getBest();
                medianRoute = world.getMedian();
                worstRoute = world.getWorst();

                window.setBest(bestRoute);
                window.setMedian(medianRoute);
                window.setWorst(worstRoute);

                System.out.println(world.state());
                Thread.sleep(1000);

            }
        }

    }
}
