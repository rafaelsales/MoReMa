package morema.model;


public class IntegerNumberQuestion extends Question {
	
	public IntegerNumberQuestion(Integer lowerBound, Integer upperBound) {
		super(QuestionType.IntegerNumber);
	}

	public Integer lowerBound;
	public Integer upperBound;
	
}