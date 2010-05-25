package morema.model;

import java.util.Vector;

public class MultipleChoiceOneAnswerQuestion extends Question {

	public MultipleChoiceOneAnswerQuestion(String question, Vector choices) {
		super(question, QUESTION_TYPE_MultipleChoiceOneAnswer);
		this.choices = choices;
	}

	public Vector choices;

}
