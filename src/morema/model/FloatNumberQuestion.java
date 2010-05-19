package morema.model;

public class FloatNumberQuestion extends Question {
	
	public FloatNumberQuestion(String title, Float lowerBound, Float upperBound) {
		super(title, QuestionType.FloatNumber);
	}

	public Float lowerBound;
	public Float upperBound;
	
}
