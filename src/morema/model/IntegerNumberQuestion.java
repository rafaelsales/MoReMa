package morema.model;


public class IntegerNumberQuestion extends Question {
	
	public IntegerNumberQuestion(String title, Integer lowerBound, Integer upperBound) {
		super(title, QUESTION_TYPE_IntegerNumber);
	}

	public Integer lowerBound;
	public Integer upperBound;
	
}