package genetic.phrase;

public class Phrase {

	private final char[] _chars;

//	public PhraseCreature(int chars, FitnessEvaluator<PhraseCreature> evaluator) {
//		_evaluator = evaluator;
//
//		_chars = new char[chars];
//		for(int i = 0; i < chars; i++) {
//			getChars()[i] = ALLOWED_CHARS[rand.nextInt(ALLOWED_CHARS.length)];
//		}
//
//		_fitness = _evaluator.evaluate(this);
//	}
	
	public Phrase(char[] chars) {
		_chars = chars;
	}

	public char[] getChars() {
		return _chars;
	}
	
	public String toString() {
		return new String(_chars);
	}
}
