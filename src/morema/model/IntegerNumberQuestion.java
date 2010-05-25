package morema.model;


public class IntegerNumberQuestion extends Question {
	
	public IntegerNumberQuestion(String question, Integer lowerBound, Integer upperBound) {
		super(question, QUESTION_TYPE_IntegerNumber);
	}

	public Integer lowerBound;
	public Integer upperBound;
	
}