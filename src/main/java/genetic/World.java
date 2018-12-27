package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class World<T> {

	public static final boolean LOWER_IS_FITTER = false;
	public static final boolean HIGHER_IS_FITTER = true;
	private final CreatureReproducer<T> _reproducer;
	private final FitnessEvaluator<T> _evaluator;
	private List<CreatureContainer<T>> _creatures;
	private double _mean;
	private int _generation;
	private final boolean _fitnessDirection;
	
	public World(int number, CreatureFactory<T> factory, CreatureReproducer<T> reproducer, FitnessEvaluator<T> evaluator, boolean fitnessDirection) {
		_generation = 0;
		_reproducer = reproducer;
		_evaluator = evaluator;
		_fitnessDirection = fitnessDirection;
		
		_creatures = new ArrayList(number);
		for(int i = 0; i < number; i++) {
			T creature = factory.createCreature();
			_creatures.add(new CreatureContainer(creature, evaluator.evaluate(creature)));
		}
		
		Collections.sort(_creatures);
		
		double sum = 0;
		for(CreatureContainer<T> c : _creatures) {
			sum += evaluator.evaluate(c._creature);
		}
		_mean = sum / _creatures.size();
	}

	public void newGeneration() {
		List<CreatureContainer<T>> nextGeneration = new ArrayList(_creatures.size());
		
		for(int i = 0; i < _creatures.size()/2; i++) {
			CreatureContainer<T> parentContainer = _creatures.get(i);
			T child = _reproducer.reproduce(parentContainer._creature);

			CreatureContainer childContainer = new CreatureContainer(child, _evaluator.evaluate(child));

			nextGeneration.add(parentContainer);
			nextGeneration.add(childContainer);
		}

		/* If the population has a non-even size then the new generation will be smaller by 1.
		 * Add new children of the best creature until the size is correct */
		while(nextGeneration.size() < _creatures.size()) {
				T child = _reproducer.reproduce(_creatures.get(0)._creature);
				CreatureContainer childContainer = new CreatureContainer(child, _evaluator.evaluate(child));
				nextGeneration.add(childContainer);
		}
		
		//sort by fitness
		Collections.sort(nextGeneration);
		
		double sum = 0;
		for(CreatureContainer c : nextGeneration) {
			sum += c._fitness;
		}
		_mean = sum / nextGeneration.size();
		
		_creatures = nextGeneration;
		_generation++;
	}
	
	public List<T> getCreatures() {
		return _creatures.stream().map(container -> container._creature).collect(Collectors.toList());
	}
	
	public String state() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("World of %d creatures:" + System.lineSeparator(), _creatures.size()));
		builder.append(String.format("\tGeneration: %d" + System.lineSeparator(), _generation));
		builder.append(String.format("\tMean: %f" + System.lineSeparator(), _mean));
		builder.append(String.format("\tBest (%f): %s" + System.lineSeparator(), _evaluator.evaluate(getBest()), getBest()));
		builder.append(String.format("\tMedian (%f): %s" + System.lineSeparator(), _evaluator.evaluate(getMedian()), getMedian()));
		builder.append(String.format("\tWorst (%f): %s" + System.lineSeparator(), _evaluator.evaluate(getWorst()), getWorst()));
		return builder.toString();
	}

	public T getBest() {
		return _creatures.get(0)._creature;
	}

	public int getGeneration() {
		return _generation;
	}

	public T getMedian() {
		return _creatures.get(_creatures.size()/2)._creature;
	}

	public T getWorst() {
		return _creatures.get(_creatures.size()-1)._creature;
	}

	private class CreatureContainer<T> implements Comparable<CreatureContainer> {
		public final T _creature;
		public final double _fitness;

		private CreatureContainer(T creature, double fitness) {
			_creature = creature;
			_fitness = fitness;
		}

		@Override
		public int compareTo(CreatureContainer o) {
			return (_fitnessDirection ? Double.compare(o._fitness, _fitness) : Double.compare(_fitness, o._fitness));
		}
	}
}
