package morema.model;

import java.util.Vector;

public class MultipleChoice extends Question {
	
	
	public MultipleChoice(String question, Vector choices, boolean multipleAnswer) {
		super(question, (multipleAnswer ? QUESTION_TYPE_MultipleChoiceMultipleAnswer : QUESTION_TYPE_MultipleChoiceOneAnswer));
		this.choices = choices;
		this.multipleAnswer = multipleAnswer;
	}

	public Vector choices;
	
	public transient boolean multipleAnswer;
	
}
