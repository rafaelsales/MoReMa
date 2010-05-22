package morema.business;

import java.util.Vector;

import morema.model.Survey;
import morema.persistence.SurveyDAO;
import morema.util.Constantes;
import morema.util.MoremaException;
import morema.util.Util;

public class SurveyBS {
	
	private SurveyBS() {
	}

	public static void createSurvey(Survey survey) throws MoremaException {
		survey.title = survey.title.trim();
		SurveyDAO surveyDAO = new SurveyDAO();
		if (Util.isEmpty(survey.title)) {
			throw new MoremaException(Constantes.MSG_ERRO_SURVEY_TITULO_INVALIDO);
		} else if (surveyDAO.getByName(survey.title) != null) {
			throw new MoremaException(Constantes.MSG_ERRO_SURVEY_TITULO_EXISTENTE);
		}
		surveyDAO.addRecord(survey);
	}

	public static void removeSurvey(Survey survey) {
		
	}
	
	public static Vector list() throws MoremaException {
		SurveyDAO surveyDAO = new SurveyDAO();
		return surveyDAO.getRecords();
	}
}
