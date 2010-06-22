package morema.view;

import javax.microedition.lcdui.Displayable;

import morema.business.QuestionBS;
import morema.model.FloatNumberQuestion;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;

public class CreateFloatNumberQuestionView extends AbstractCreateQuestionView {
	
	public CreateFloatNumberQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm, Constants.QUESTION_TYPE_FLOAT_NUMBER);
	}
	
	protected void addQuestion() throws MoremaException {
		FloatNumberQuestion question = new FloatNumberQuestion(tfTitle.getString());
		question.surveyId = survey.id;
		QuestionBS.addFloatNumberQuestion(question);
	}
}
