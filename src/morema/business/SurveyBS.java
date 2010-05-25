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
		SurveyDAO dao = new SurveyDAO();
		if (Util.isEmpty(survey.title)) {
			throw new MoremaException(Constantes.MSG_ERRO_DADOS_INVALIDOS);
		} else {
			Survey existentSurvey = dao.getByName(survey.title);
			if (existentSurvey != null && !survey.id.equals(existentSurvey.id)) {
				throw new MoremaException(Constantes.MSG_ERRO_SURVEY_TITULO_EXISTENTE);
			}
		}
		dao.saveRecord(survey);
	}

	public static void removeSurvey(Survey survey) throws MoremaException {
	}
	
	public static Vector list() throws MoremaException {
		SurveyDAO dao = new SurveyDAO();
		return dao.getRecords();
	}
}
