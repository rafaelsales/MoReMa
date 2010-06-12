package morema.business;

import morema.model.FloatNumberQuestion;
import morema.model.IntegerNumberQuestion;
import morema.model.MultipleChoice;
import morema.model.OpenQuestion;
import morema.model.Question;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.persistence.QuestionDAO;
import morema.util.Constantes;
import morema.util.MoremaException;
import morema.util.Util;

public class QuestionBS {
	
	private QuestionBS() {
	}
	
	public static void addTrueFalseQuestion(TrueFalseQuestion question) throws MoremaException {
		addQuestion(question);
	}

	public static void addMultipleChoiceQuestion(MultipleChoice question) throws MoremaException {
		if (question.choices.isEmpty()) {
			throw new MoremaException(Constantes.MSG_ERRO_DADOS_INVALIDOS);
		}
		addQuestion(question);
	}

	public static void addIntegerNumberQuestion(IntegerNumberQuestion question) throws MoremaException {
		addQuestion(question);
	}

	public static void addFloatNumberQuestion(FloatNumberQuestion question) throws MoremaException {
		addQuestion(question);
	}

	public static void addOpenQuestion(OpenQuestion question) throws MoremaException {
		addQuestion(question);
	}

	public static void removeQuestion(Question question) throws MoremaException {
	}
	
	public static Object[] getQuestions(Survey survey) throws MoremaException {
		QuestionDAO questionDAO = new QuestionDAO(survey.id);
		return questionDAO.getRecords();
	}
	
	private static void addQuestion(Question question) throws MoremaException {
		if (Util.isEmpty(question.question) || question.typeId == null) {
			throw new MoremaException(Constantes.MSG_ERRO_DADOS_INVALIDOS);
		}
		QuestionDAO dao = new QuestionDAO(question.surveyId);
		dao.saveRecord(question);
	}
	
}
