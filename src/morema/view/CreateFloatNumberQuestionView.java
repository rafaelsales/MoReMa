package morema.view;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import morema.business.QuestionBS;
import morema.model.FloatNumberQuestion;
import morema.model.Survey;
import morema.util.Constantes;
import morema.util.MoremaException;
import morema.util.Util;

public class CreateFloatNumberQuestionView extends AbstractCreateQuestionView {
	
	private TextField tfLowerBound;
	private TextField tfUpperBound;
	
	public CreateFloatNumberQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
		
		tfLowerBound = new TextField("Limite inferior", null, getWidth(), TextField.DECIMAL);
		tfUpperBound = new TextField("Limite superior", null, getWidth(), TextField.DECIMAL);
		
		append(tfLowerBound);
		append(tfUpperBound);
	}
	
	protected void addQuestion() {
		Float lowerBound = null;
		Float upperBound = null;
		if (!Util.isEmpty(tfLowerBound.getString())) {
			lowerBound = Float.valueOf(tfLowerBound.getString());
		}
		if (!Util.isEmpty(tfUpperBound.getString())) {
			upperBound = Float.valueOf(tfUpperBound.getString());
		}
		FloatNumberQuestion question = new FloatNumberQuestion(tfTitle.getString(), lowerBound, upperBound);
		question.surveyId = survey.id;
		try {
			QuestionBS.addFloatNumberQuestion(question);
			MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, parentForm);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
		}
	}
}
