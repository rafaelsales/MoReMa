package morema.model;

public class FloatNumberQuestion extends Question {
	
	public FloatNumberQuestion(String question, Float lowerBound, Float upperBound) {
		super(question, QUESTION_TYPE_FloatNumber);
	}

	public Float lowerBound;
	public Float upperBound;
	
}
