package morema.model;

import java.util.Vector;

public class MultipleChoiceOneAnswerQuestion extends Question {

	public MultipleChoiceOneAnswerQuestion(String title, Vector choices) {
		super(title, QUESTION_TYPE_MultipleChoiceOneAnswer);
		this.choices = choices;
	}

	public Vector choices;

}
