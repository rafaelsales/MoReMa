package morema.view;

import javax.microedition.lcdui.Displayable;

import morema.business.QuestionBS;
import morema.model.FloatNumberQuestion;
import morema.model.Survey;
import morema.util.Constantes;
import morema.util.MoremaException;

public class CreateFloatNumberQuestionView extends AbstractCreateQuestionView {
	
	public CreateFloatNumberQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
	}
	
	protected void addQuestion() {
		FloatNumberQuestion question = new FloatNumberQuestion(tfTitle.getString());
		question.surveyId = survey.id;
		try {
			QuestionBS.addFloatNumberQuestion(question);
			MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, parentForm, false);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
		}
	}
}
