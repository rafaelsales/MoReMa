package morema.model;


public class FloatNumberQuestion extends Question {
	
	public FloatNumberQuestion(Float lowerBound, Float upperBound) {
		super(QuestionType.FloatNumber);
	}

	public Float lowerBound;
	public Float upperBound;
	
}
