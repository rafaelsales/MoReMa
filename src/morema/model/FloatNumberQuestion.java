package morema.model;

public class FloatNumberQuestion extends Question {
	
	public FloatNumberQuestion(String title, Float lowerBound, Float upperBound) {
		super(title, QUESTION_TYPE_FloatNumber);
	}

	public Float lowerBound;
	public Float upperBound;
	
}
