package morema.view;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import morema.business.QuestionBS;
import morema.model.IntegerNumberQuestion;
import morema.model.Survey;
import morema.util.Constantes;
import morema.util.MoremaException;
import morema.util.Util;

public class CreateIntegerNumberQuestionView extends AbstractCreateQuestionView {
	
	private TextField tfLowerBound;
	private TextField tfUpperBound;
	
	public CreateIntegerNumberQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
		
		tfLowerBound = new TextField("Limite inferior", null, getWidth(), TextField.NUMERIC);
		tfUpperBound = new TextField("Limite superior", null, getWidth(), TextField.NUMERIC);
		
		append(tfLowerBound);
		append(tfUpperBound);
	}
	
	protected void addQuestion() {
		Integer lowerBound = null;
		Integer upperBound = null;
		if (!Util.isEmpty(tfLowerBound.getString())) {
			lowerBound = Integer.valueOf(tfLowerBound.getString());
		}
		if (!Util.isEmpty(tfUpperBound.getString())) {
			upperBound = Integer.valueOf(tfUpperBound.getString());
		}
		IntegerNumberQuestion question = new IntegerNumberQuestion(tfTitle.getString(), lowerBound, upperBound);
		question.surveyId = survey.id;
		try {
			QuestionBS.addIntegerNumberQuestion(question);
			MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, parentForm);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
		}
	}
}
