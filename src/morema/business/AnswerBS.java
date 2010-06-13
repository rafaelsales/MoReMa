package morema.business;

import morema.model.Answer;
import morema.model.Question;
import morema.persistence.AnswerDAO;
import morema.util.MoremaException;

public class AnswerBS {

	private AnswerBS() {
	}

	public static void save(Answer answer, Question question) throws MoremaException {
		new AnswerDAO(answer.surveyId).saveRecord(answer);
	}
}
