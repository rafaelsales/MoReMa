package morema.business;

import morema.model.Survey;
import morema.persistence.AnswerDAO;
import morema.persistence.QuestionDAO;
import morema.persistence.SurveyDAO;
import morema.util.Constants;
import morema.util.MoremaException;
import morema.util.Util;

public class SurveyBS {
	
	private SurveyBS() {
	}

	public static void save(Survey survey) throws MoremaException {
		survey.title = survey.title.trim();
		SurveyDAO dao = new SurveyDAO();
		if (Util.isEmpty(survey.title)) {
			throw new MoremaException(Constants.MSG_ERROR_INVALID_DATA);
		} else {
			Survey existentSurvey = dao.getByName(survey.title);
			if (existentSurvey != null && !existentSurvey.id.equals(survey.id)) {
				throw new MoremaException(Constants.MSG_ERROR_SURVEY_EXISTING_TITLE);
			}
		}
		dao.saveRecord(survey);
	}

	public static void remove(Survey survey) throws MoremaException {
		new AnswerDAO(survey.id).removeRecordStore();
		new QuestionDAO(survey.id).removeRecordStore();
		new SurveyDAO().removeRecord(survey.id.intValue());
	}
	
	public static Object[] list() throws MoremaException {
		SurveyDAO dao = new SurveyDAO();
		return dao.getRecords();
	}
	
	
}
