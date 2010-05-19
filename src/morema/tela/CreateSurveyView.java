package morema.tela;

import morema.business.SurveyBS;
import morema.model.Survey;

public class CreateSurveyView {
	
	private Survey survey;
	
	private void createSurvey() {
		survey = SurveyBS.createSurvey(survey);
	}
	
	public void prepareAddQuestion() {
		
	}
}
