package morema.model;

import java.util.Vector;


public class MultipleChoiceOneAnswerQuestion extends Question {
	
	public MultipleChoiceOneAnswerQuestion(Vector choices) {
		super(QuestionType.MultipleChoiceOneAnswer);
	}

	public Vector choices;
	
}
