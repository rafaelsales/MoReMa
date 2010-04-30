package morema.model;

import java.util.Vector;

public class MultipleChoiceMultipleAnswerQuestion extends Question {
	
	public MultipleChoiceMultipleAnswerQuestion(Vector choices) {
		super(QuestionType.MultipleChoiceMultipleAnswer);
	}

	public Vector choices;
	
}
