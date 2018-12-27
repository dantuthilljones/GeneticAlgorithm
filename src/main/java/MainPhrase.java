import genetic.CreatureFactory;
import genetic.CreatureReproducer;
import genetic.FitnessEvaluator;
import genetic.World;
import genetic.phrase.Phrase;

import java.io.IOException;
import java.util.Random;

public class MainPhrase {

	private static final char[] ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
	private static final char[] PHRASE = "to be or not to be".toCharArray();
	private static final double INSTABILITY = 0.2;

	private static final Random rand = new Random();
	
	public static void main(String args[]) throws IOException {

		FitnessEvaluator<Phrase> evaluator = (Phrase creature) -> {
			char[] chars = creature.getChars();
			double score = 0;
			for(int i = 0; i < chars.length; i++) {
				if(PHRASE[i] == chars[i]) {
					score += 1d;
				}
			}
			return score;
		};

		CreatureReproducer<Phrase> reproducer = (Phrase parent) -> {
			char[] newPhrase = parent.getChars().clone();

			for(int i = 0; i < newPhrase.length; i++) {
				if(rand.nextDouble() < INSTABILITY) {
					newPhrase[i] = ALLOWED_CHARS[rand.nextInt(ALLOWED_CHARS.length)];
				}
			}
			return new Phrase(newPhrase);
		};

		CreatureFactory<Phrase> factory = () -> {
			char[] newPhrase = new char[PHRASE.length];
			for(int i = 0; i < newPhrase.length; i++) {
				newPhrase[i] = ALLOWED_CHARS[rand.nextInt(ALLOWED_CHARS.length)];
			}
			return new Phrase(newPhrase);
		};


		World<Phrase> world = new World(100, factory, reproducer, evaluator, World.HIGHER_IS_FITTER);


		while (true) {
			System.out.println(world.state());
			System.out.println("Press return to proceed to the next generation...");
			System.in.read();

			world.newGeneration();
		}
	}
}
