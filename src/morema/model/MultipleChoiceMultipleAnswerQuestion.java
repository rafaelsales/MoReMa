package morema.model;

import java.util.Vector;

public class MultipleChoiceMultipleAnswerQuestion extends Question {
	
	public MultipleChoiceMultipleAnswerQuestion(String question, Vector choices) {
		super(question, QUESTION_TYPE_MultipleChoiceMultipleAnswer);
		this.choices = choices;
	}

	public Vector choices;
	
}
