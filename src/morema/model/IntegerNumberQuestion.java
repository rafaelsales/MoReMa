package morema.model;


public class IntegerNumberQuestion extends Question {
	
	public IntegerNumberQuestion(String title, Integer lowerBound, Integer upperBound) {
		super(title, QuestionType.IntegerNumber);
	}

	public Integer lowerBound;
	public Integer upperBound;
	
}