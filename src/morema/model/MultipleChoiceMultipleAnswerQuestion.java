package morema.model;

import java.util.Vector;

public class MultipleChoiceMultipleAnswerQuestion extends Question {
	
	public MultipleChoiceMultipleAnswerQuestion(String title, Vector choices) {
		super(title, QuestionType.MultipleChoiceMultipleAnswer);
		this.choices = choices;
	}

	public Vector choices;
	
}
