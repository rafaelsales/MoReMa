package morema.business;

import morema.model.Answer;
import morema.persistence.AnswerDAO;
import morema.util.MoremaException;

public class AnswerBS {

	private AnswerBS() {
	}
	
	public static Answer answerTrueFalse(Answer answer) {
		return answer;
	}

	public static Answer answerMultipleChoiceMultipleAnswer(Answer answer) {
		return answer;
	}

	public static Answer answerMultipleChoiceOneAnswerQuestion(Answer answer) {
		return answer;
	}

	public static Answer answerIntegerNumber(Answer answer) {
		return answer;
	}

	public static void answerFloatNumber(Answer answer) {

	}

	public static void answerOpen(Answer answer) {

	}

	public static void answer(Answer answer) throws MoremaException {
		new AnswerDAO(answer.surveyId).saveRecord(answer);
	}
}
