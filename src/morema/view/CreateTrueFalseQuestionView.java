package morema.view;

import javax.microedition.lcdui.Displayable;

import morema.business.QuestionBS;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.util.Constantes;
import morema.util.MoremaException;

public class CreateTrueFalseQuestionView extends AbstractCreateQuestionView {
	
	public CreateTrueFalseQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
	}
	
	protected void addQuestion() {
		TrueFalseQuestion question = new TrueFalseQuestion(tfTitle.getString());
		question.surveyId = survey.id;
		try {
			QuestionBS.addTrueFalseQuestion(question);
			MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, parentForm);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
		}
	}
}
