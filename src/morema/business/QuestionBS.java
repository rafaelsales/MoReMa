package morema.business;

import morema.model.FloatNumberQuestion;
import morema.model.MultipleChoiceQuestion;
import morema.model.OpenQuestion;
import morema.model.Question;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.persistence.QuestionDAO;
import morema.util.Constants;
import morema.util.MoremaException;
import morema.util.Util;

public class QuestionBS {
	
	private QuestionBS() {
	}
	
	public static void addTrueFalseQuestion(TrueFalseQuestion question) throws MoremaException {
		addQuestion(question);
	}

	public static void addMultipleChoiceQuestion(MultipleChoiceQuestion question) throws MoremaException {
		if (question.choices.isEmpty()) {
			throw new MoremaException(Constants.MSG_ERROR_INVALID_DATA);
		}
		addQuestion(question);
	}

	public static void addFloatNumberQuestion(FloatNumberQuestion question) throws MoremaException {
		addQuestion(question);
	}

	public static void addOpenQuestion(OpenQuestion question) throws MoremaException {
		addQuestion(question);
	}
	
	public static Object[] list(Survey survey) throws MoremaException {
		QuestionDAO questionDAO = new QuestionDAO(survey.id);
		return questionDAO.getRecords();
	}
	
	private static void addQuestion(Question question) throws MoremaException {
		if (Util.isEmpty(question.question) || question.typeId == null) {
			throw new MoremaException(Constants.MSG_ERROR_INVALID_DATA);
		}
		QuestionDAO dao = new QuestionDAO(question.surveyId);
		dao.saveRecord(question);
	}
	
}
